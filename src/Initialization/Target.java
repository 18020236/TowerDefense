package Initialization;

import Game.GameTile;
import com.sun.javafx.geom.Vec2d;

public class Target implements GameTile {
    final int TARGET_X = Config.TILE_SIZE * Config.HORIZONTAL - 30;
    final int TARGET_Y = Config.TILE_SIZE * Config.VERTICAL;
    Vec2d target = new Vec2d(TARGET_X, TARGET_Y);
    @Override
    public Vec2d getPosition() {
        return target;
    }
}
