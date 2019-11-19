package Game;

import Bullet.Bullet;
import Enemy.Enemy;
import Enemy.EnemyGenerator;
import Initialization.Background;
import Tower.Tower;
import com.sun.javafx.geom.Vec2d;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import Tower.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GameField extends AnimationTimer {
    protected GraphicsContext gc;
    private final int startingLevel = 1;
    protected AnchorPane root = new AnchorPane();

    public GameField(GraphicsContext gc, AnchorPane root) {
        this.gc = gc;
        this.root = root;
        road = new Background(gc);
        restartGame();
    }

    private Background road;
    private static Queue<Enemy> enemyQueue = new LinkedList<Enemy>();
    public static Queue<Enemy> activeEnemyQueue = new LinkedList<Enemy>();
    private ArrayList<Enemy> enemyList;
    private static ArrayList<Tower> towerList = new ArrayList<Tower>();
    private static ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    private final int enemySpawnDelay = 50;
    private static boolean waveIsInProgress = true;
    static long tickCount = 0;
    public static boolean gameOver = false;
    private static int currentLevel = 0;
    private String level;
    private String lives;
    private String cash;
    EnemyGenerator generator;

    Font font = Font.font("Verdana", FontWeight.NORMAL, 20);
    private Text textLevel = new Text("");
    private Text textLives = new Text("");
    private Text textCash = new Text("");
    private Text textGameOver = new Text("GAME OVER");

    public void addEnemiesToActiveEnemyQueue() {
        tickCount++;
        if (tickCount > enemySpawnDelay) {
            activeEnemyQueue.add(enemyQueue.poll());
            tickCount = 0;
        }
    }


    public void restartGame() {
        currentLevel = startingLevel;
        Player.getPlayer().reset();
        waveIsInProgress = true;
        enemyQueue = new LinkedList<Enemy>();
        activeEnemyQueue = new LinkedList<Enemy>();
        createEnemyQueueForLevel();
        gameOver = false;
        towerList.add(new NormalTower(gc,new Vec2d(32,32),activeEnemyQueue));
        towerList.add(new NormalTower(gc,new Vec2d(7*32,8*32),activeEnemyQueue));
        towerList.add(new NormalTower(gc, new Vec2d(4*32,4*32),activeEnemyQueue));
    }


    public void createEnemyQueueForLevel() {
        generator = new EnemyGenerator(currentLevel - 1);
        generator.createEnemyQueue(gc);
//        generator.RandomizeEnemyQueue();
        enemyQueue = generator.getCritterQueue();
        activeEnemyQueue = new LinkedList<Enemy>();
        activeEnemyQueue.add(enemyQueue.poll());
    }

    public void updateEnemies() {
        ArrayList<Enemy> enemiesToRemove = new ArrayList<Enemy>();
        //for each enemy list, update their movement if they are alive
        for (Enemy s : activeEnemyQueue) {
            //only living enemies can move!
            if (s.isAlive())
                s.update(road.getMap());
            else {
                Player.getPlayer().addCredits(s.getPrize());
                enemiesToRemove.add(s);
            }

            if (s.isAtEndPoint()) {
                Player.getPlayer().decreaseLife();
                root.getChildren().remove(s.healthBar());
                enemiesToRemove.add(s);
            }
        }

        //remove all the dead critters and the critters that have arrived at the exit
        for (Enemy s : enemiesToRemove) {
            activeEnemyQueue.remove(s);
        }

        if (enemyQueue.size() == 0 && activeEnemyQueue.size() == 0) {
//            waveIsInProgress = false;
            currentLevel++;
            createEnemyQueueForLevel();
        }
    }

    public void update() {

        if (waveIsInProgress) {
            if (enemyQueue.size() != 0) {
                addEnemiesToActiveEnemyQueue();
            } else if (enemyQueue.size() == 0 && activeEnemyQueue.size() == 0) {
                currentLevel++;
            }
            if (!gameOver) {
                updateEnemies();
              //  targetEnemies();
            }
            for(int i = 0;i <towerList.size();i++) {
                towerList.get(i).update();
            }
        }

        if (Player.getPlayer().getLives() <= 0) {
            System.out.println(Player.getPlayer().getLives() + " het mau cmnr");
            gameOver = true;
        }

    }

    public void drawText(Text text, int size, double x, double y) {
        Font font = Font.font("Verdana", FontWeight.NORMAL, size);
        text.setFill(Color.BLACK);
        text.setFont(font);
        text.setX(x);
        text.setY(y);
        root.getChildren().remove(text);
        root.getChildren().add(text);
    }

    public void targetEnemies() {
        for(Tower t: towerList) {
            t.setTargetEnemy(null);
            //
            //  ENEMY THIEU HAM ISVISIBLE
            //
            for(Enemy e : activeEnemyQueue) {
                if(e.isAlive() ) {
                    // Calculate distance
                    double xDist = Math.abs(e.getPosition().x - t.getPosition().x);
                    double yDist = Math.abs(e.getPosition().x - t.getPosition().y);
                    double dist = Math.sqrt((xDist*xDist) + (yDist*yDist));
                    // ENEMY THIEU HAM getEnemyTravelDistanceMaximum
                    if(dist < t.getRange()) {
                        t.setTargetEnemy(e);
                    }
                }
            }
            t.setEnemyTravelDistanceMaximum(0);
        }
    }
  /*  public void attackEnemies() {
        for (Tower t : towerList) {
            if (t.getTargetEnemy() != null && t.canAttack()) {
                attackEnemy(t);
                t.setTimeOfLastAttack(System.currentTimeMillis());
            }
        }
    }*/

    /*public void attackEnemy(Tower source) {
        Bullet bullet = new Bullet(gc, new Vec2d(source.getPosition().x,source.getPosition().y), new Vec2d(source.getTargetEnemy().getPosition().x,source.getTargetEnemy().getPosition().y),
                                    source.getPower(),source.getTargetEnemy());
        bulletList.add(bullet);
    }*/

    public void reloadTowers() {
        for(Tower t : towerList) {
            t.setTimeOfLastAttack(0);
        }
    }

    public Tower getNearestTower( int x , int y ) {
        double distanceApproximation = 100;
        Tower neareastTower = null;
        for ( Tower t : towerList) {
            if ( Math.abs(t.getPosition().x - x) + Math.abs(t.getPosition().y -y) < distanceApproximation ) {
                neareastTower = t;
                distanceApproximation = Math.abs(t.getPosition().x - x) + Math.abs(t.getPosition().y - y) ;
            }
        }
        return neareastTower;
    }

    public void draw() {
        road.draw();
        if (waveIsInProgress) {
            for (Enemy s : activeEnemyQueue) {
                s.draw();
                s.drawEnemyHealth(root);
            }
            for (Tower t : towerList) {
                t.draw();
            }
        }

        lives = "LIVES: " + Player.getPlayer().getLives();
        cash = "CASH: " + Player.getPlayer().getCredits();
        level = "LEVEL: " + currentLevel;
        textLives.setText(lives);
        textCash.setText(cash);
        textLevel.setText(level);
        drawText(textLives, 20, 100, 300);
        drawText(textCash, 20, 300, 300);
        drawText(textLevel, 20, 500, 300);

        if (gameOver) {
            root.getChildren().remove(textCash);
            root.getChildren().remove(textLevel);
            root.getChildren().remove(textLives);
            drawText(textGameOver, 40, 200, 300);
        }
    }


    @Override
    public void handle(long now) {
        update();
        draw();
    }
}