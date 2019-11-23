package Initialization;

import Game.GameTile;
import com.sun.javafx.geom.Vec2d;

public class Road implements GameTile {
    int[] road = {48, 49, 50, 51, 52, 56, 58, 59, 60, 64, 65, 66, 67, 68};
    public boolean isRoad(int valueEntity){
        for (int i = 0; i < road.length; i++){
            if (valueEntity == road[i]) return true;
        }
        return false;
    }
    @Override
    public Vec2d getPosition() {
        return null;
    }
}
