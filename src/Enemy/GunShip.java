package Enemy;

import Initialization.Config;
import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class GunShip extends Enemy{
    public GunShip( GraphicsContext gc)
    {
        name = "GunShip";
        this.gc=gc;
        position = new Vec2d(Config.START_POINT_X,Config.START_POINT_Y);
        health = 100;
        maxHealth = 100;
        speed = (float) 0.6;
        armor = 3;
        prize = 10;
        image = ImageProcessing.splits(18,11);
        for(int i = 0; i < 4; i++)
        {
            gunShipImages.add(ImageProcessing.rotate(image,i*90));
        }
    }
}
