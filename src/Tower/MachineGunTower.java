package Tower;

import Bullet.MachineGunBullet;
import Enemy.Enemy;
import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

import java.util.Queue;

public class MachineGunTower extends Tower {
    public MachineGunTower(GraphicsContext gc, Vec2d position, Queue<Enemy> activeEnemyList) {
        super(position);
        this.gc = gc;
        this.buyingCost =newBuyingCost;
        this.refundValue = newRefundValue;
        this.reloadTime = newReloadTime;
        this.range = newRange;
        this.power = newPower;
        this.speed = 5;
        this.activeEnemyList = activeEnemyList;
        this.image = ImageProcessing.splits(19,10);
    }

    private static int newBuyingCost = 200;
    private static int newRefundValue = 180;
    private static double newReloadTime = 0.25;
    private static int newRange = 80;
    private static double newPower = 9;

    @Override
    public void attack(Enemy targetEnemy) {
        bullets.add(new MachineGunBullet(this, gc, targetEnemy));
    }
}
