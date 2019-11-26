package Bullet;

import Enemy.Enemy;
import Game.GameEntity;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public abstract class Bullet implements GameEntity {
    private boolean destroyed;

    public boolean isDestroyed() {
        return destroyed;
    }


    protected double speed ;
    protected int range;
    protected double power;
    protected Vec2d towerPos;
    public boolean isMoving;
    protected GraphicsContext gc;
    protected Vec2d position;
    protected Image image;
    Enemy targetEnemy;

    public Bullet(Enemy targetEnemy) {
        this.targetEnemy = targetEnemy;
    }

    public Vec2d getPosition() {
        return position;
    }

    String path = "src/Resources/Audio/shot5.mp3";

    //Instantiating Media class
    Media shoot = new Media(new File(path).toURI().toString());


    public void update() {
        if (targetEnemy!= null) {
            double d  = Vec2d.distance(position.x,position.y,targetEnemy.getPosition().x,targetEnemy.getPosition().y);
            double dx = targetEnemy.getPosition().x - position.x;
            double dy = targetEnemy.getPosition().y - position.y;
            double vx;
            double vy;
            if(d<speed){
                vx=dx;
                vy=dy;
                position.x+=vx;
                position.y+=vy;
                isMoving = false;
                targetEnemy.takeDamage(power);
                destroyed = true;
            }
            else{
                vx=speed*dx/d;
                vy=speed*dy/d;
                position.x += vx;
                position.y += vy;
                isMoving = true;
            }
        } else {
            destroyed = true;
        }
    }

    public void shootAudio(){
        //Instantiating MediaPlayer class
        MediaPlayer shootPlayer = new MediaPlayer(shoot);
        shootPlayer.play();
//        mediaPlayer.autoPlayProperty();
//        shootPlayer.setCycleCount(MediaPlayer.INDEFINITE);
//        shootPlayer.setStartTime(Duration.seconds(0));
//        shootPlayer.setStopTime(Duration.seconds(0.5));
    }

    public void draw() {
        if (Vec2d.distance(towerPos.x, towerPos.y, position.x, position.y) >= 16) {
            gc.drawImage(image, position.x, position.y,32,32);
        }
    }
}
