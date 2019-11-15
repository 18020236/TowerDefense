package Game;


import Enemy.Enemy;
import Enemy.EnemyGenerator;
import Initialization.Background;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//import java.awt.*;

public class GameField extends AnimationTimer {

    protected GraphicsContext gc;

    private final int startingLevel = 1;
    protected AnchorPane root = new AnchorPane();

    public GameField(GraphicsContext gc, AnchorPane root) {
        this.gc = gc;
        this.root = root;
        road = new Background(gc);

        restartGame();

//        root.getChildren().add(GameField.text("GAME OVER", 200, 300));

//        enemyQ = new LinkedList<>();
//
//        for (int j = 0; j < 100; j++) {
//            if (Math.round(Math.random()) == 0) {
//                enemyQ.add(new NormalEnemy(gc));
//            } else {
//                if (Math.round(Math.random()) == 0){
//                    enemyQ.add(new TankerEnemy(gc));
//                } else enemyQ.add(new GunShip(gc));
//            }
//        }

//        for (int j = 0; j < 10; j++) {
//            enemyQ.add(new TankerEnemy(gc));
//        }
    }

    private Background road;
    private static Queue<Enemy> enemyQueue = new LinkedList<Enemy>();
    public  static Queue<Enemy> activeEnemyQueue = new LinkedList<Enemy>();
    private ArrayList<Enemy> enemyList;
    private final int enemySpawnDelay = 20;
    private static boolean waveIsInProgress = true;
    static long tickCount = 0;
    public static boolean gameOver = false;
    private static int currentLevel = 0;

    EnemyGenerator generator;

    public void addEnemiesToActiveEnemyQueue(){
        tickCount++;
        if(tickCount > enemySpawnDelay){
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
//        towerList =  new ArrayList<Tower>();
        createEnemyQueueForLevel();
        gameOver = false;

    }


    public void createEnemyQueueForLevel(){
        generator = new EnemyGenerator(currentLevel - 1);
        generator.createEnemyQueue(gc);
        generator.RandomizeEnemyQueue();
        enemyQueue = generator.getCritterQueue();
        activeEnemyQueue = new LinkedList<Enemy>();
        activeEnemyQueue.add(enemyQueue.poll());
    }

    public void updateEnemies(){
        ArrayList<Enemy> enemiesToRemove = new ArrayList<Enemy>();
        //for each enemy list, update their movement if they are alive
        for(Enemy s : activeEnemyQueue){
            //only living enemies can move!
            if(s.isAlive())
                s.update(road.getMap());
            else{
                Player.getPlayer().addCredits(s.getPrize());
                enemiesToRemove.add(s);
            }

            if(s.isAtEndPoint()){
                Player.getPlayer().decreaseLife();
                System.out.println(Player.getPlayer().getLives());
                enemiesToRemove.add(s);
            }
        }

        //remove all the dead critters and the critters that have arrived at the exit
        for(Enemy s : enemiesToRemove){
            activeEnemyQueue.remove(s);
        }

        if(enemyQueue.size()==0 && activeEnemyQueue.size() == 0){
            waveIsInProgress = false;
            currentLevel++;
        }
    }

    public void update() {

        if(waveIsInProgress){
            if(enemyQueue.size()!=0){
                addEnemiesToActiveEnemyQueue();
            }
            if(!gameOver){
                updateEnemies();
//                targetEnemies();
//                attackEnemies();
            }
        }

//        if(Mouse.isButtonDown(0)){
//            mouseClicked(Mouse.getX(), container.getHeight() - Mouse.getY(), sbg, container);
//        }

        if(Player.getPlayer().getLives() <= 0){
            System.out.println(Player.getPlayer().getLives() + " het mau cmnr");
            gameOver = true;
        }

//        for (int i = 0; i < enemyList.size(); i++) {
//            enemyList.get(i).update(road.getMap());
//        }
    }
    public void drawText(String str, int size, double x,  double y){
        TextFlow text_flow = new TextFlow();
        // create a font
        Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, size);
        // create text
        Text text = new Text(str);
        // set the text color
        text.setFill(Color.BLACK);
        // set font of the text
        text.setFont(font);
        text.setX(x);
        text.setY(y);
        root.getChildren().add(text);
    }


    public void draw() {
        road.draw();
        if(waveIsInProgress){
            for (Enemy s : activeEnemyQueue) {
                s.draw();
//                s.drawEnemyHealth(root);
            }
        }
        //draw health, wave,
//        root.getChildren().add(GameField.text("GAME OVER", 200, 300));
        drawText("LIVES: " + Player.getPlayer().getLives(),20, 100, 300);
        drawText("CASH: " + Player.getPlayer().getCredits(),20, 300, 300);
        drawText("LEVEL: " + currentLevel,20, 500, 300);

        if(gameOver){
            drawText("GAME OVER",40, 200, 300);
        }
//        for (Enemy s : activeEnemyQueue) {
//            s.draw();
//        }
    }

    @Override
    public void handle(long now) {
        update();
        draw();
    }

}
