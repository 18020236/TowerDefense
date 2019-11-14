package Enemy;

import Initialization.Config;
import Initialization.ImageProcessing;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;



public class TankerEnemy extends Enemy{
    public TankerEnemy( GraphicsContext gc)
    {
        this.gc=gc;
        name = "Tanker";
        position = new Vec2d(Config.START_POINT_X,Config.START_POINT_Y);
        health = 15;
        healthBar.setProgress(health/15);
        speed = (float) 0.3;
        armor = 4;
        prize = 80;
        image= ImageProcessing.splits(15,12);
        for(int i = 0; i < 4; i++)
        {
            images.add(ImageProcessing.rotate(image,i*90));
        }
    }
}
