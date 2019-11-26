package Tower;

import Bullet.Bullet;
import Enemy.Enemy;
import Game.GameTile;
import Initialization.ImageProcessing;
import Initialization.Rotate;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Tower implements GameTile {
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
    Queue<Bullet> bullets = new LinkedList<>();

    protected GraphicsContext gc;
    protected Vec2d position;
    protected Image image;

    public Vec2d getPosition() {
        return position;
    }
    public Tower(Vec2d pos) {
        this.position = pos;
        this.lastAttackTime = 0;
        angleOfRotation = 0;
        lastAttackTime = 0;
    }

    public void getTargetEnemy() {
         {
            for (Enemy e : activeEnemyList) {
                if (Vec2d.distance(e.getPosition().x, e.getPosition().y, position.x, position.y) <= range) {
                    dangerousEnemy = e;
                    return;
                }
            }
            dangerousEnemy = null;
        }
    }

    public boolean canAttack(){
        if((System.currentTimeMillis()-lastAttackTime)/1000.0 >= reloadTime){
            return true;
        }
        else
            return false;
    }

    public void setLastAttackTime(long time) {
        lastAttackTime = time;
    }

    public int  getRange() {
        return range;
    }

    public double getSpeed() {
        return speed;
    }

    public double getPower() {
        return power;
    }

    public abstract void attack(Enemy targetEnemy);

    public void update() {
        getTargetEnemy();
        if(dangerousEnemy!= null) {
            angleOfRotation = Rotate.setAngle(dangerousEnemy.getPosition(),position);
        }
        if(dangerousEnemy!= null && this.canAttack()) {
            attack(dangerousEnemy);
            this.setLastAttackTime(System.currentTimeMillis());

        }
        while (!bullets.isEmpty() && bullets.peek().isDestroyed()) bullets.remove();
        for (Bullet bullet : bullets) {
            if (!bullet.isDestroyed())
                bullet.update();
        }
    }

    public void draw() {
        gc.drawImage(ImageProcessing.rotate(image,angleOfRotation),position.x,position.y,32,32);
        for (Bullet bullet : bullets) {
            if (!bullet.isDestroyed())
                bullet.draw();
        }
        Color color = Color.RED;
        gc.setStroke(color);
        gc.strokeOval(position.x + 16-range , position.y + 16-range,range*2,range*2);
    }
}
