package Tower;

import Bullet.Bullet;
import Enemy.Enemy;
import Game.GameEntity;
import Game.Player;
import com.sun.javafx.geom.Vec2d;

public class Tower extends GameEntity {
    public enum type{
        NORMAL, SNIPER, MACHINEGUN;
    }
    protected int buyingCost;
    protected int refundValue;
    protected double range;
    protected double power;
    protected Bullet bullet;
    protected Enemy targetEnemy;
    protected double reloadTime;
    private long lastAttackTime;
    private double angleOfRotation;
    protected type towerType;
    private double bulletTravelDistanceMaximum;
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

    public Enemy getTargetEnemy() {
        return targetEnemy;
    }

    public double getBulletTravelDistanceMaximum() {
        return bulletTravelDistanceMaximum;
    }

    public void setBulletTravelDistanceMaximum(double bulletTravelDistanceMaximum) {
        this.bulletTravelDistanceMaximum = bulletTravelDistanceMaximum;
    }

    public double getRotationAngleInDegrees() {
        if(targetEnemy != null) {
            // REVIEW getPosition of Enemy
            angleOfRotation = (180/Math.PI)*Math.atan2(targetEnemy.getPosition().y - position.y, targetEnemy.getPosition().x - position.x);
        }
        return angleOfRotation;
    }

    public double getRange() {
        return range;
    }

    public double getPower() {
        return power;
    }

    public void setTimeOfLastAttack(long time) {
        lastAttackTime = time;
    }
}
