package Tower;

import Bullet.Bullet;
import Enemy.Enemy;
import Game.GameEntity;
import Initialization.ImageProcessing;
import Initialization.Rotate;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Tower extends GameEntity {
    protected int buyingCost;
    protected int refundValue;
    protected int range;
    protected double power;
    protected Enemy targetEnemy;
    protected double reloadTime;
    private long lastAttackTime;
    private double angleOfRotation = 0;
    protected double speed;
    protected Queue<Enemy> activeEnemyList = new LinkedList<>();
    protected Enemy dangerousEnemy;
    Bullet bullet;
    public Tower(Vec2d pos) {
        this.position = pos;
        this.lastAttackTime = 0;
        angleOfRotation = 0;
        lastAttackTime = 0;
    }

    public void getTargetEnemy() {
        if(!bullet.isMoving) {
            for (Enemy e : activeEnemyList) {
                if (Vec2d.distance(e.getPosition().x, e.getPosition().y, position.x, position.y) <= range) {
                    dangerousEnemy = e;
                    return;
                }
            }
            dangerousEnemy = null;
        }
    }

    public int  getRange() {
        return range;
    }

    public double getSpeed() {
        return speed;
    }

    public void update() {
        getTargetEnemy();
        if(dangerousEnemy!= null) {
            angleOfRotation = Rotate.setAngle(dangerousEnemy.getPosition(),position);
            bullet.shoot(dangerousEnemy);
            dangerousEnemy.takeDamage(power);
        }

    }

    public void draw() {
        gc.drawImage(ImageProcessing.rotate(image,angleOfRotation),position.x,position.y,32,32);
        bullet.draw();;
        Color color = Color.RED;
        gc.setStroke(color);
        gc.strokeOval(position.x + 16-range , position.y + 16-range,range*2,range*2);
        for (Enemy e : activeEnemyList) {
            if (Vec2d.distance(e.getPosition().x, e.getPosition().y, position.x, position.y) <= range) {
                gc.strokeLine(position.x + 16, position.y + 16, e.getPosition().x + 16, e.getPosition().y + 16);
                break;
            }
        }
    }
}
