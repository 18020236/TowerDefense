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
<<<<<<< HEAD
        health = 2;
        maxHealth = 2;
        speed = (float) 1.6;
//        speed = (float) 3.2;
=======
        health = 3;
        maxHealth = 3;
        speed = (float) 1.2;
>>>>>>> e0d75801e34e51451ff34c50f6e1468508dffb4d
        armor = 1;
        prize = 10;
        image = ImageProcessing.splits(18,11);
        for(int i = 0; i < 4; i++)
        {
            gunShipImages.add(ImageProcessing.rotate(image,i*90));
        }
    }
}
