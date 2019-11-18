package Tower;


import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class NormalTower extends Tower {
    public NormalTower(GraphicsContext gc, Vec2d position) {
        super(position);
        this.gc = gc;
        this.buyingCost = newBuyingCost;
        this.refundValue = newRefundValue;
        this.reloadTime = newReloadTime;
        this.range = newRange;
        this.power = newPower;
        this.image = ImageProcessing.splits(19,8);
    }

    private static int newBuyingCost = 100;
    private static int newRefundValue = 90;
    private static double newReloadTime = 0.3;
    private static double newRange = 64;
    private static double newPower = 0.6;
}
