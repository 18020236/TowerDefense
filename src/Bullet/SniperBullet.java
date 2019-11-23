package Bullet;

import Initialization.ImageProcessing;
import Tower.Tower;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class SniperBullet extends Bullet{
    public SniperBullet(Tower tower, GraphicsContext gc) {
        this.gc = gc;
        image = ImageProcessing.splits(19,12);
        speed = tower.getSpeed();
        range = tower.getRange();
        position = new Vec2d(tower.getPosition());
        towerPos = tower.getPosition();
        isMoving = false;
        power = tower.getPower();
    }
}
