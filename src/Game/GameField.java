package Game;


import Enemy.Enemy;
import Enemy.GunShip;
import Enemy.NormalEnemy;
import Enemy.TankerEnemy;
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
        enemyQ = new LinkedList<>();

        for (int j = 0; j < 100; j++) {
            if (Math.round(Math.random()) == 0) {
                enemyQ.add(new NormalEnemy(gc));
            } else {
                if (Math.round(Math.random()) == 0){
                    enemyQ.add(new TankerEnemy(gc));
                } else enemyQ.add(new GunShip(gc));
            }
        }

//        for (int j = 0; j < 10; j++) {
//            enemyQ.add(new TankerEnemy(gc));
//        }

        enemyList = new ArrayList<>();

    }

    private Background road;
    private static Queue<Enemy> enemyQ;
    private ArrayList<Enemy> enemyList;

    public void update() {
        if (!enemyQ.isEmpty()) {
            if (round % 30 == 0) {
                enemyList.add(enemyQ.poll());
            }
        }
        round++;


        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).update(road.getMap());
        }

    }


    public void draw() {

        road.draw();
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).draw();
        }

    }

    @Override
    public void handle(long now) {
        update();
        draw();
    }
}
