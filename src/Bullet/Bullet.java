package Bullet;

import Enemy.Enemy;
import Game.GameEntity;
import com.sun.javafx.geom.Vec2d;

public abstract class Bullet extends GameEntity {
    protected double speed ;
    protected int range;
    protected double power;
    protected Vec2d towerPos;
    public boolean isMoving;

    // REVIEW
    public void shoot(Enemy dangerousEnemy) {
        if (dangerousEnemy!= null) {
            double d  = Vec2d.distance(position.x,position.y,dangerousEnemy.getPosition().x,dangerousEnemy.getPosition().y);
            double dx = dangerousEnemy.getPosition().x - position.x;
            double dy = dangerousEnemy.getPosition().y - position.y;
            double vx;
            double vy;
            if(d<speed){
                vx=dx;
                vy=dy;
                position.x+=vx;
                position.y+=vy;
                isMoving = false;
                dangerousEnemy.takeDamage(power);
                position=new Vec2d(towerPos);
            }
            else{
                vx=speed*dx/d;
                vy=speed*dy/d;
                position.x += vx;
                position.y += vy;
                isMoving = true;
            }

        }
    }

    public void draw() {
        if (Vec2d.distance(towerPos.x, towerPos.y, position.x, position.y) >= 16) {
            gc.drawImage(image, position.x, position.y,32,32);
        }
    }
}
