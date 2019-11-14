package Tower;


import Bullet.NormalBullet;
import Enemy.Enemy;
import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class NormalTower extends Tower {
    public NormalTower(GraphicsContext gc, ArrayList<Enemy> enemyList) {
        this.gc = gc;
        range = 64*4;
        position = new Vec2d(7*64,8*64);
        speed = 16;
        image = ImageProcessing.splits(20,10);
        enemyListPosition = enemyList;
        bullet = new NormalBullet(this,gc);
    }
}
