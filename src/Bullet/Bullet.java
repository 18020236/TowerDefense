package Bullet;

import Enemy.Enemy;
import Game.GameEntity;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public abstract class Bullet extends GameEntity {
    protected double speed ;
    protected int range;
    protected int damage;
    protected Vec2d towerPos;
    public boolean isMoving;

    String path = "src/Resources/Audio/shot5.mp3";

    //Instantiating Media class
    Media shoot = new Media(new File(path).toURI().toString());

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
