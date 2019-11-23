package Game;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface GameTile extends GameEntity {
    Vec2d position = new Vec2d();
    Image image = null;
    GraphicsContext gc = null;
}
