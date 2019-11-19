package Bullet;

import Initialization.ImageProcessing;
import Tower.Tower;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class NormalBullet extends Bullet {
    public NormalBullet(Tower tower, GraphicsContext gc) {
        this.gc = gc;
        image = ImageProcessing.splits(19,11);
        speed = tower.getSpeed();
        range = tower.getRange();
        position = new Vec2d(tower.getPosition());
        towerPos = tower.getPosition();
        isMoving = false;
    }
}
