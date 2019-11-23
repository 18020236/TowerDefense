package Tower;
import Bullet.*;
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
        this.speed = 3;
        this.image = ImageProcessing.splits(19,10);
        this.activeEnemyList = activeEnemyList;
        bullet = new SniperBullet(this,gc);
    }
    private static int newBuyingCost = 300;
    private static int newRefundValue = 250;
    private static double newReloadTime = 0.0;
    private static int newRange = 96;
    private static double newPower = 0.4;
}
