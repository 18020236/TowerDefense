package Bullet;

import Initialization.ImageProcessing;
import Tower.Tower;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class MachineGunBullet extends Bullet{
    public MachineGunBullet(Tower tower, GraphicsContext gc) {
        this.gc = gc;
        image = ImageProcessing.splits(21,10);
        speed = tower.getSpeed();
        range = tower.getRange();
        position = new Vec2d(tower.getPosition());
        towerPos = tower.getPosition();
        isMoving = false;
        power = tower.getPower();
    }
}
