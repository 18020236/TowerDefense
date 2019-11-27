package Enemy;

import Initialization.Config;
import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;

public class SmallerEnemy extends Enemy {
    public SmallerEnemy( GraphicsContext gc)
    {
        this.gc = gc;
        name = "Smaller";
        position = new Vec2d(Config.START_POINT_X,Config.START_POINT_Y);
        health = 50;
        maxHealth = 50;
//        speed = (float) 1.6;
        speed = (float) 0.8;
        armor = 0;
        prize = 20;
        image = ImageProcessing.splits(17,10);
        for(int i = 0; i < 4; i++)
        {
            images.add(ImageProcessing.rotate(image,i*90));
        }
    }
}
