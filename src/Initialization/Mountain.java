package Initialization;

import Game.GameTile;
import com.sun.javafx.geom.Vec2d;

public class Mountain implements GameTile {
    int[] mountain = {57, 53, 54, 55, 61, 62, 63, 69, 70, 71};
    public boolean isMountain(int valueEntity){
        for (int i = 0; i < mountain.length; i++){
            if (valueEntity == mountain[i]) return true;
        }
        return false;
    }

    public Vec2d getPosition() {
        return position;
    }
}
