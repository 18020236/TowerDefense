package Tower;
import Bullet.SniperBullet;
import Enemy.Enemy;
import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

import java.util.Queue;

public class SniperTower extends Tower{
    public SniperTower(GraphicsContext gc, Vec2d position, Queue<Enemy> activeEnemyList) {
        super(position);
        this.gc = gc;
        this.buyingCost = newBuyingCost;
        this.refundValue = newRefundValue;
        this.reloadTime = newReloadTime;
        this.range = newRange;
        this.power = newPower;
        this.speed = 4;
        this.image = ImageProcessing.splits(20,10);
        this.activeEnemyList = activeEnemyList;
    }
    private static int newBuyingCost = 300;
    private static int newRefundValue = 250;
    private static double newReloadTime = 3;
    private static int newRange = 160;
    private static double newPower = 2.4;

    @Override
    public void attack(Enemy targetEnemy) {
        bullets.add(new SniperBullet(this, gc, targetEnemy));
    }
}
