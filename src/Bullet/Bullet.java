package Bullet;

import Enemy.Enemy;
import Game.GameEntity;
import com.sun.javafx.geom.Vec2d;

public class Bullet extends GameEntity {
    protected double speed;
    protected double damage;
    protected double range;
    protected Vec2d towerPosition;
    public boolean isMoving;

    public void shoot(Enemy dangerousEnemy) {
        if(dangerousEnemy != null) {
            double d = Vec2d.distance(position.x,position.y,dangerousEnemy.getPosition().x,dangerousEnemy.getPosition().y);
          /*NEED TO REVIEW LATER*/
            double dx = dangerousEnemy.getPosition().x - position.x;
            double dy = dangerousEnemy.getPosition().y - position.y;
            double vx;
            double vy;
            if(d<speed) {
                vx = dx;
                vy = dy;
                position.x += vx;
                position.y += vy;
                isMoving = false;
                position = new Vec2d(towerPosition);
            } else {
                vx = speed*dx/d;
                vy = speed*dx/d;
                position.x += vx;
                position.y += vy;
                isMoving = true;
            }
        }
    }

    public void draw() {
        if(Vec2d.distance(towerPosition.x,towerPosition.y,position.x,position.y)>= 32) {
            gc.drawImage(image,position.x,position.y);
        }
    }
}
