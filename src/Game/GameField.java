package Game;

import Enemy.Enemy;
import Enemy.EnemyGenerator;
import Initialization.Background;
import Initialization.Config;
import Tower.*;
import com.sun.javafx.geom.Vec2d;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
    Audio bgAudio = new Audio("src/Resources/Audio/Brirfing_theme.mp3");

//    String path = "src/Resources/Audio/Brirfing_theme.mp3";
//
//    //Instantiating Media class
//    Media bg_audio = new Media(new File(path).toURI().toString());
//
//    //Instantiating MediaPlayer class
//    MediaPlayer bg_player = new MediaPlayer(bg_audio);

    //by setting this property to true, the audio will be player
    private static Queue<Enemy> enemyQueue = new LinkedList<Enemy>();
    public static Queue<Enemy> activeEnemyQueue = new LinkedList<Enemy>();
    private static ArrayList<Tower> towerList = new ArrayList<Tower>();
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
        towerList.add(new SniperTower(gc,new Vec2d(15*32,8*32),activeEnemyQueue));
        towerList.add(new MachineGunTower(gc,new Vec2d(8*32,8*32),activeEnemyQueue));
        towerList.add(new NormalTower(gc, new Vec2d(4*32,4*32),activeEnemyQueue));
        towerList.add(new MachineGunTower(gc,new Vec2d(13*32,4*32),activeEnemyQueue));
        towerList.add(new NormalTower(gc,new Vec2d(3*32,11*32),activeEnemyQueue));

        bgAudio.playCycle(36);
    }


    public void createEnemyQueueForLevel() {
        generator = new EnemyGenerator(currentLevel - 1);
        generator.createEnemyQueue(gc);
        enemyQueue = generator.getCritterQueue();
//        activeEnemyQueue = new LinkedList<Enemy>();
      //  activeEnemyQueue = new LinkedList<Enemy>();  HUHU
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

        //remove all the dead enemies and the enemies that have arrived at the exit
        for (Enemy s : enemiesToRemove) {
            root.getChildren().remove(s.healthBar());
            activeEnemyQueue.remove(s);
        }

        if (enemyQueue.size() == 0 && activeEnemyQueue.size() == 0) {
//            waveIsInProgress = false;
            currentLevel++;
            createEnemyQueueForLevel();
        }
    }

    public void reloadTowers() {
        for(Tower t : towerList) {
            t.setLastAttackTime(0);
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
                for(int i = 0;i <towerList.size();i++) {
                    towerList.get(i).update();
                }
            }
        }

        if (Player.getPlayer().getLives() <= 0) {
            System.out.println(Player.getPlayer().getLives() + " het mau cmnr");
            gameOver = true;
        }
    }

    public void drawText(Text text, int size, double x, double y, Paint value) {
        Font font = Font.font("Verdana", FontWeight.NORMAL, size);
        text.setFill(value);
        text.setFont(font);
        text.setX(x);
        text.setY(y);
        root.getChildren().remove(text);
        root.getChildren().add(text);
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

        lives = "LIVES " + Player.getPlayer().getLives();
        cash = "CASH " + Player.getPlayer().getCredits();
        level = "LEVEL " + currentLevel;
        textLives.setText(lives);
        textCash.setText(cash);
        textLevel.setText(level);
        drawText(textLives, 20, 100, Config.HEIGHT + Config.PLAYER_BAR_HEIGHT - 15, Color.RED);
        drawText(textCash, 20, 300, Config.HEIGHT + Config.PLAYER_BAR_HEIGHT - 15, Color.GREEN);
        drawText(textLevel, 20, 500, Config.HEIGHT + Config.PLAYER_BAR_HEIGHT - 15, Color.BROWN);

        if (gameOver) {
            root.getChildren().remove(textCash);
            root.getChildren().remove(textLevel);
            root.getChildren().remove(textLives);
            drawText(textGameOver, 40, 200, 300, Color.RED);
        }
    }


    @Override
    public void handle(long now) {
        update();
        draw();
    }
}