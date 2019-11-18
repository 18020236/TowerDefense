package Tower;
import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
public class SniperTower extends Tower{
    public SniperTower(GraphicsContext gc, Vec2d pos) {
        super(pos);
        this.gc = gc;
        this.buyingCost = newBuyingCost;
        this.refundValue = newRefundValue;
        this.reloadTime = newReloadTime;
        this.range = newRange;
        this.power = newPower;
        this.image = ImageProcessing.splits(19,10);
    }
    private static int newBuyingCost = 300;
    private static int newRefundValue = 250;
    private static int newReloadTime = 7;
    private static double newRange = 64;
    private static int newPower = 20;
}
