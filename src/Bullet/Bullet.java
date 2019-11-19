package Bullet;

import Enemy.Enemy;
import Game.GameEntity;
import com.sun.javafx.geom.Vec2d;

public abstract class Bullet extends GameEntity {
    private Vec2d Dest;
    private Vec2d Init;
    protected double speed ;
    protected double power;
    private boolean arrivedAtTarget = false;
    private Enemy targetEnemy;
    protected int range;
    protected int damage;
    protected Vec2d towerPos;
    public boolean isMoving;
 /*   public Bullet(GraphicsContext gc,Vec2d Init, Vec2d Dest, double power, Enemy targetEnemy) {
        this.gc = gc;
        this.Init = Init;
        this.Dest = Dest;
        this.power = power;
        this.targetEnemy = targetEnemy;
        this.image = ImageProcessing.splits(19,11);
        // REVIEW

        this.position.x = Init.x + 12*Math.cos(angleOfBulletInRadians());
        this.position.y = Init.y + 12*Math.sin(angleOfBulletInRadians());
        arrivedAtTarget = false;
    }*/

    public double angleOfBullletInDegrees() {
        return (180/Math.PI)*Math.atan2(Dest.y - Init.y, Dest.x - Init.x);
    }

    public double angleOfBulletInRadians() {
        return Math.atan2(Dest.y - Init.y, Dest.x - Init.x);
    }

    public void move() {
        if(Math.abs(position.x - Dest.x) < speed/2 || Math.abs(position.y - Dest.y) < speed /2 ) {
            arrivedAtTarget = true;
            targetEnemy.takeDamage(power);
        } else {
            position.x += speed*Math.cos(angleOfBulletInRadians());
            position.y += speed*Math.sin(angleOfBulletInRadians());
        }
    }
    // REVIEW
    public void shoot(Enemy dangerousEnemy) {
        if (dangerousEnemy!= null) {
            double d= Vec2d.distance(position.x,position.y,dangerousEnemy.getPosition().x,dangerousEnemy.getPosition().y);
            double dx= dangerousEnemy.getPosition().x - position.x;
            double dy= dangerousEnemy.getPosition().y - position.y;
            double vx;
            double vy;
            if(d<speed){
                vx=dx;
                vy=dy;
                position.x+=vx;
                position.y+=vy;
                isMoving = false;
                position=new Vec2d(towerPos);
            }
            else{
                vx=speed*dx/d;
                vy=speed*dy/d;
                position.x+=vx;
                position.y+=vy;
                isMoving = true;
            }
        }

    }

    public void draw() {
        if (Vec2d.distance(towerPos.x, towerPos.y, position.x, position.y) >= 32) {
            gc.drawImage(image, position.x, position.y);
        }
    }

    public boolean hasArrived() {
        return arrivedAtTarget;
    }
    public double getSpeed() {
        return this.speed;
    }
}
