package Initialization;

import Game.GameTile;
import com.sun.javafx.geom.Vec2d;

public class Spawner implements GameTile {
    Vec2d spawner = new Vec2d(Config.START_POINT_X, Config.START_POINT_Y);

    @Override
    public Vec2d getPosition() {
        return spawner;
    }
}
