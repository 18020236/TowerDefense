package Tower;


import com.sun.javafx.geom.Vec2d;

public class NormalTower extends Tower {
    public NormalTower(Vec2d position) {
        super(position);
        this.buyingCost = newBuyingCost;
        this.refundValue = newRefundValue;
        this.reloadTime = newReloadTime;
        this.range = newRange;
        this.power = newPower;
        // this.towerType
    }

    private static int newBuyingCost = 100;
    private static int newRefundValue = 90;
    private static double newReloadTime = 0.3;
    private static double newRange = 100;
    private static double newPower = 0.6;
}
