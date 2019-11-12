package Enemy;

import Game.GameEntity;
import Initialization.Config;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Enemy extends GameEntity {
    protected int health;
    protected float speed;
    protected int armor;
    protected int prize;
    protected double dangerousLevel;
    protected ArrayList<Image> images = new ArrayList<>();

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

    public void update(int map[][])
    {
        move(map);
        dangerousLevel += speed;
    }

    public void draw()
    {
        if(check.equals("TURN_RIGHT"))
        {
            gc.drawImage(images.get(0),pos.x,pos.y,96,96);
        }
        if(check.equals("TURN_LEFT"))
        {
            gc.drawImage(images.get(2),pos.x,pos.y,96,96);
        }
        if(check.equals("TURN_UP"))
        {
            gc.drawImage(images.get(3),pos.x,pos.y,96,96);
        }
        if(check.equals("TURN_DOWN"))
        {
            gc.drawImage(images.get(1),pos.x,pos.y,96,96);
        }
    }
}
