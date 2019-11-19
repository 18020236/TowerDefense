package Tower;

import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class MachineGunTower extends Tower {
    public MachineGunTower(GraphicsContext gc, Vec2d pos) {
        super(pos);
        this.gc = gc;
        this.buyingCost =newBuyingCost;
        this.refundValue = newRefundValue;
        this.reloadTime = newReloadTime;
        this.range = newRange;
        this.power = newPower;
        this.image = ImageProcessing.splits(20,10);
    }

    private static int newBuyingCost = 200;
    private static int newRefundValue = 180;
    private static int newReloadTime = 4;
    private static int newPower = 0;
    private static int newRange = 64;
}
