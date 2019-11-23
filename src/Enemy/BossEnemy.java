package Enemy;

import Initialization.Config;
import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class BossEnemy extends Enemy {
    public BossEnemy( GraphicsContext gc)
    {
        this.gc = gc;
        name = "GunShip";
        this.position = new Vec2d(Config.START_POINT_X,Config.START_POINT_Y);
        health = 15;
        maxHealth = 15;
//        speed = (float) 0.25;
        speed = (float) 3.2;
        armor = 8;
        prize = 100;
        image = ImageProcessing.splits(15,11);
        for(int i = 0; i < 4; i++)
        {
            images.add(ImageProcessing.rotate(image,i*90));
        }
    }
}
