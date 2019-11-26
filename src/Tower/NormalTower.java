package Tower;
import Bullet.NormalBullet;
import Enemy.Enemy;
import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

import java.util.Queue;
public class NormalTower extends Tower {
    public NormalTower(GraphicsContext gc, Vec2d position, Queue<Enemy> activeEnemyList ) {
        super(position);
        this.gc = gc;
        this.buyingCost = newBuyingCost;
        this.refundValue = newRefundValue;
        this.reloadTime = newReloadTime;
        this.range = newRange;
        this.power = newPower;
        this.speed = 3;
        this.image = ImageProcessing.splits(19,8);
        this.activeEnemyList = activeEnemyList;
    }
    private static int newBuyingCost = 100;
    private static int newRefundValue = 90;
    private static double newReloadTime = 1.5;
    private static int newRange = 100;
    private static double newPower = 0.6;

    @Override
    public void attack(Enemy targetEnemy) {
        bullets.add(new NormalBullet(this, gc, targetEnemy));
    }
}
