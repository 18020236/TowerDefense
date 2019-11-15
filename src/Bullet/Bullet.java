package Bullet;

import Enemy.Enemy;
import Game.GameEntity;
import com.sun.javafx.geom.Vec2d;

public class Bullet extends GameEntity {
    public enum bulletType{
        NORMAL, SNIPER, MACHINEGUN ;
    }
    private Vec2d Dest;
    private Vec2d Init;
    protected double speed =20;
    protected double power;
    private bulletType bulletType;
    private boolean arrivedAtTarget = false;
    private Enemy targetEnemy;

    public Bullet(Vec2d Init, Vec2d Dest, double power, Enemy targetEnemy, bulletType Type) {
        this.Init = Init;
        this.Dest = Dest;
        this.power = power;
        this.targetEnemy = targetEnemy;
        this.bulletType = Type;

        // REVIEW

        this.position.x = Init.x + 12*Math.cos(angleOfBulletInRadians());
        this.position.y = Init.y + 12*Math.sin(angleOfBulletInRadians());
        arrivedAtTarget = false;
        if(Type == bulletType.SNIPER) {
            speed = 15;
        }
    }

    public double angleOfBullletInDegrees() {
        return (180/Math.PI)*Math.atan2(Dest.y - Init.y, Dest.x - Init.x);
    }

    public double angleOfBulletInRadians() {
        return Math.atan2(Dest.y - Init.y, Dest.x - Init.x);
    }

    public void move() {
        if(Math.abs(position.x - Dest.x) < speed/2 || Math.abs(position.y - Dest.y) < speed /2 ) {
            arrivedAtTarget = true;
            /* CHUNG CHUA VIET
                    .
                    .
                    .*/
            // targetEnemy.takeDamage(power);
        } else {
            position.x += speed*Math.cos(angleOfBulletInRadians());
            position.y += speed*Math.sin(angleOfBulletInRadians());
        }
    }

    public boolean hasArrived() {
        return arrivedAtTarget;
    }
    public double getSpeed() {
        return this.speed;
    }
    public bulletType getType() {
        return this.bulletType;
    }
}
