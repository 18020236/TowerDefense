package Game;


import Enemy.Enemy;
import Enemy.EnemyGenerator;
import Initialization.Background;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GameField extends AnimationTimer {

    protected GraphicsContext gc;
    protected Stage stage;
    private int round;

    private final int startingLevel = 1;

    public GameField(GraphicsContext gc) {
        this.gc = gc;
        this.stage = stage;
        round = 0;
        road = new Background(gc);

        createEnemyQueueForLevel();

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

        enemyList = new ArrayList<>();

    }

    private Background road;
    private static Queue<Enemy> enemyQueue = new LinkedList<Enemy>();
    public  static Queue<Enemy> activeEnemyQueue = new LinkedList<Enemy>();
    private ArrayList<Enemy> enemyList;
    private final int enemySpawnDelay = 20;
    private static boolean waveIsInProgress = true;
    static long tickCount = 0;
    private boolean gameOver = false;
    private static int currentLevel = 0;

    EnemyGenerator generator;


    public void addEnemiesToActiveEnemyQueue(){
        tickCount++;
        if(tickCount > enemySpawnDelay){
            activeEnemyQueue.add(enemyQueue.poll());
            tickCount = 0;
        }
    }



    public void updateEnemies(){
        ArrayList<Enemy> enemiesToRemove = new ArrayList<Enemy>();
        //for each enemy list, update their movement if they are alive
        for(Enemy s : activeEnemyQueue){
            //only living enemies can move!
            if(s.isAlive())
                s.update(road.getMap());
//            else{
//                Player.getPlayer().addCredits(s.getReward());
//                crittersToRemove.add(s);
//            }

            if(s.isAtEndPoint()){
//                Player.getPlayer().decreaseLife();
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

    public void createEnemyQueueForLevel(){

        generator = new EnemyGenerator(currentLevel);
        generator.createEnemyQueue(gc);
        generator.RandomizeEnemyQueue();
        enemyQueue = generator.getCritterQueue();
        activeEnemyQueue = new LinkedList<Enemy>();
        activeEnemyQueue.add(enemyQueue.poll());
    }

    public void update() {
//        if (!enemyQ.isEmpty()) {
//            if (round % 30 == 0) {
//                enemyList.add(enemyQ.poll());
//            }
//        }
//        round++;

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

//        for (int i = 0; i < enemyList.size(); i++) {
//            enemyList.get(i).update(road.getMap());
//        }

    }


    public void draw() {

        road.draw();
        if(waveIsInProgress){
            for (Enemy s : activeEnemyQueue) {
                s.draw();
            }
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
