package Enemy;

import Game.GameEntity;
import Initialization.Config;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Enemy extends GameEntity {
    protected boolean alive = true;
    protected double health;
    protected float speed;
    protected int armor;
    protected int prize;
    protected double dangerousLevel;
    protected ArrayList<Image> images = new ArrayList<>();
    protected ArrayList<Image> gunShipImages = new ArrayList<>();

    public double getDangerousLevel()
    {
        return dangerousLevel;
    }

    protected enum Direction{
        LEFT,RIGHT,UP,DOWN;
    }

    //status begin
    protected Direction direction = Direction.RIGHT;


    protected String check = new String("TURN_RIGHT");
    protected void check(int map[][])
    {
        int yLocation1 =  (int)((Math.round(pos.y)+48)/ Config.LENGTH_IMAGE);
        int yLocation2 =  (int)((Math.round(pos.y)+47)/ Config.LENGTH_IMAGE);
        int xLocation1 =  (int)((Math.round(pos.x)+48)/ Config.LENGTH_IMAGE);
        int xLocation2 =  (int)((Math.round(pos.x)+47)/ Config.LENGTH_IMAGE);

        //checkRight
        if(direction==Direction.RIGHT)
        {
            if(map[yLocation1][xLocation1]==60)
            {
                check= "TURN_UP";
            }

            if(map[ yLocation2][xLocation1]==52)
            {
                check= "TURN_DOWN";
            }
        }


        if(direction==Direction.LEFT)
        {
            if(map[yLocation1][xLocation2] == 59)
            {
                check= "TURN_UP";
            }

            if(map[ yLocation2][xLocation2] == 51)
            {
                check= "TURN_DOWN";
            }
        }


        if(direction==Direction.UP)
        {
            if(map[ yLocation2][xLocation2] == 51)
            {
                check= "TURN_RIGHT";
            }

            if(map[ yLocation2][xLocation1] == 52)
            {
                check= "TURN_LEFT";
            }
        }


        if(direction==Direction.DOWN)
        {
            if(map[yLocation1][xLocation2]==59)
            {
                check= "TURN_RIGHT";
            }

            if(map[yLocation1][xLocation1]==60)
            {
                check= "TURN_LEFT";
            }
        }
    }


    public void move(int map[][])
    {
        check(map);

        if(check.equals("TURN_RIGHT"))
        {
            pos.x=pos.x+speed;
            direction=Direction.RIGHT;
        }

        if(check.equals("TURN_LEFT"))
        {
            pos.x=pos.x-speed;
            direction=Direction.LEFT;
        }

        if(check.equals("TURN_UP"))
        {
            pos.y=pos.y-speed;
            direction=Direction.UP;
        }

        if(check.equals("TURN_DOWN"))
        {
            pos.y=pos.y+speed;
            direction=Direction.DOWN;
        }
    }

    public void takeDamage(double damage){
        health =  health - damage/armor;
        if(health <= 0){
            alive = false;
        }
    }

    public boolean isAtEndPoint() {
        if (pos.x >= Config.TILE_SIZE * Config.HORIZONTAL) return true;
        return false;
    }

    public double getHealth() {
        return health;
    }

    public float getSpeed() {
        return speed;
    }

    public int getArmor() {
        return armor;
    }

    public int getPrize() {
        return prize;
    }

    public void update(int map[][])
    {
        move(map);
        dangerousLevel += speed;
    }


    public void drawByEnemyType(ArrayList<Image> images){
        if(check.equals("TURN_RIGHT"))
        {
            gc.drawImage(images.get(0),pos.x,pos.y,96,96);
        }
        else if(check.equals("TURN_LEFT"))
        {
            gc.drawImage(images.get(2),pos.x,pos.y,96,96);
        }
        else if(check.equals("TURN_UP"))
        {
            gc.drawImage(images.get(3),pos.x,pos.y,96,96);
        }
        else if(check.equals("TURN_DOWN"))
        {
            gc.drawImage(images.get(1),pos.x,pos.y,96,96);
        }
    }


    public void draw()
    {
        if (this instanceof GunShip == false){
            drawByEnemyType(images);
        }
        if (this instanceof GunShip == true){
            drawByEnemyType(gunShipImages);
        }
    }

}
