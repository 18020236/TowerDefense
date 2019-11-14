package Initialization;

import com.sun.javafx.geom.Vec2d;

public class Rotate {
    public static double setAngle(Vec2d posEnemy, Vec2d pos) {
        Vec2d v = new Vec2d(posEnemy.x - pos.x, posEnemy.y - pos.y);
        double lengthVector = Math.sqrt(Math.pow(v.x,2) + Math.pow(v.y,2));
        double cos = -v.y / lengthVector;
        double angle = (Math.acos(cos)/Math.PI)*180;
        if(posEnemy.x <= pos.x) {
            return -angle;
        }
        else {
            return angle;
        }
    }
}
