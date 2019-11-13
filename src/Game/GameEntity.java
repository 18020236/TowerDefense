package Game;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public abstract class GameEntity {
    protected Vec2d position;
    protected Image image = null;
    protected static GraphicsContext gc;
    public Vec2d getPosition() {
        return position;
    }
}
