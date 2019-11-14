package Tower;

import Bullet.Bullet;
import Enemy.Enemy;
import Game.GameEntity;
import Initialization.ImageProcessing;
import Initialization.Rotate;
import com.sun.javafx.geom.Vec2d;

import java.util.ArrayList;

public class Tower extends GameEntity {
    protected double speed;
    protected double range;
    protected double damage;
    private double angle = 0;
    protected ArrayList<Enemy> enemyListPosition = new ArrayList<>();
    protected Bullet bullet;
    protected Enemy dangerousEnemy;

    public double getSpeed() {
        return speed;
    }

    public double getRange() {
        return range;
    }

    public double getDamage() {
        return damage;
    }

    public void getTarget() {
        if(!bullet.isMoving) {
            for(int i = 0 ; i< enemyListPosition.size(); i++) {
                if(Vec2d.distance(enemyListPosition.get(i).getPosition().x,enemyListPosition.get(i).getPosition().y,position.x,position.y) <= range ) {
                    dangerousEnemy = enemyListPosition.get(i);
                    return;
                }
            }
            dangerousEnemy = null;
        }

    }
    public void update() {
        getTarget();
        if(dangerousEnemy!=null) {
            angle = Rotate.setAngle(dangerousEnemy.getPosition(),position);
        }
        bullet.shoot(dangerousEnemy);
    }

    public void draw() {
        gc.drawImage(ImageProcessing.rotate(image,angle),position.x,position.y);
        bullet.draw();
        gc.strokeOval(position.x+32-range,position.y+32-range,range*2,range*2);
        for(int i=0;i<enemyListPosition.size();i++) {
            if(Vec2d.distance(enemyListPosition.get(i).getPosition().x,enemyListPosition.get(i).getPosition().y,position.x,position.y )<= range) {
                // REVIEW
                gc.strokeLine(position.x+32,position.y+32,enemyListPosition.get(i).getPosition().x+32,enemyListPosition.get(i).getPosition().y+32);
                break;
            }
        }

    }
}
