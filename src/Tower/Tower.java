package Tower;

import Bullet.Bullet;
import Enemy.Enemy;
import Game.GameEntity;
import Game.Player;
import Initialization.ImageProcessing;
import Initialization.Rotate;
import com.sun.javafx.geom.Vec2d;

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
    private double enemyTravelDistanceMaximum = 0;
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
    // TIME PER SHOOTING
    public boolean canAttack() {
        if((System.currentTimeMillis() - lastAttackTime)/1000.0 >= reloadTime) {
            return true;
        } else {
            return false;
        }
    }
    /////////////////////
    public void refundTower() {
        Player.getPlayer().addCredits(refundValue);
    }

    public int getBuyingCost() {
        return buyingCost;
    }

    public void setBuyingCost(int buyingCost) {
        this.buyingCost = buyingCost;
    }

    public int getRefoundValue() {
        return refundValue;
    }

    public void setRefoundValue(int refoundValue) {
        this.refundValue = refoundValue;
    }

    public void setTargetEnemy(Enemy enemy) {
        targetEnemy = enemy;
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

    public double getEnemyTravelDistanceMaximum() {
        return enemyTravelDistanceMaximum;
    }

    public void setEnemyTravelDistanceMaximum(double enemyTravelDistanceMaximum) {
        this.enemyTravelDistanceMaximum = enemyTravelDistanceMaximum;
    }

   /* public double getRotationAngleInDegrees() {
        if(targetEnemy != null) {
            // REVIEW getPosition of Enemy
            angleOfRotation = (180/Math.PI)*Math.atan2(targetEnemy.getPosition().y - position.y, targetEnemy.getPosition().x - position.x);
        }
        return angleOfRotation;
    }*/

    public int  getRange() {
        return range;
    }

    public double getSpeed() {
        return speed;
    }

    public void setTimeOfLastAttack(long time) {
        lastAttackTime = time;
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
        gc.strokeOval(position.x + 16-range , position.y + 16-range,range*2,range*2);

    }
}
