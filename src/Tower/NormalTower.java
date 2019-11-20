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
        this.speed = 5;
        this.image = ImageProcessing.splits(19,8);
        this.activeEnemyList = activeEnemyList;
        bullet = new NormalBullet(this,gc);
    }
    private static int newBuyingCost = 100;
    private static int newRefundValue = 90;
    private static double newReloadTime = 0.3;
    private static int newRange = 64;
    private static double newPower = 10;
}
