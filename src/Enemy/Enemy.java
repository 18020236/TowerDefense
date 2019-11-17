package Enemy;

import Game.GameEntity;
import Initialization.Config;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class Enemy extends GameEntity {
    protected boolean alive = true;
    protected double health;
    protected float speed;
    protected int armor;
    protected int prize;
    protected double dangerousLevel;
    protected String name = "DEFAULT";
    protected ArrayList<Image> images = new ArrayList<>();
    protected ArrayList<Image> gunShipImages = new ArrayList<>();
    HealthBar healthBar = new HealthBar();

    public HealthBar healthBar(){
        return this.healthBar;
    }

    Rectangle blood = new Rectangle(10*Config.BLOOD_UNIT, 10);

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
        int yLocation1 =  (int)((Math.round(position.y)+24)/ Config.LENGTH_IMAGE);
        int yLocation2 =  (int)((Math.round(position.y)+23)/ Config.LENGTH_IMAGE);
        int xLocation1 =  (int)((Math.round(position.x)+24)/ Config.LENGTH_IMAGE);
        int xLocation2 =  (int)((Math.round(position.x)+23)/ Config.LENGTH_IMAGE);

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
            position.x= position.x+speed;
            direction=Direction.RIGHT;
        }

        if(check.equals("TURN_LEFT"))
        {
            position.x= position.x-speed;
            direction=Direction.LEFT;
        }

        if(check.equals("TURN_UP"))
        {
            position.y= position.y-speed;
            direction=Direction.UP;
        }

        if(check.equals("TURN_DOWN"))
        {
            position.y= position.y+speed;
            direction=Direction.DOWN;
        }
    }


    public boolean isAtEndPoint() {
        if (position.x >= Config.TILE_SIZE * Config.HORIZONTAL - 30) return true;
        return false;
    }

    public boolean isAlive() {
        return alive;
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

    public String getName(){
        return name;
    }

    public void update(int map[][])
    {
        move(map);
        dangerousLevel += speed;
//        healthBar.setValue((double)this.getHealth()/health, position.x, position.y);
        healthBar.setValue(0.65, position.x, position.y);
    }

    public void takeDamage(double damage){
        health = health - damage/armor;
        if(health <= 0){
            alive = false;
        }
    }

    public void drawByEnemyType(ArrayList<Image> images){
        if(check.equals("TURN_RIGHT"))
        {
            gc.drawImage(images.get(0), position.x, position.y,48, 48);
        }
        else if(check.equals("TURN_LEFT"))
        {
            gc.drawImage(images.get(2), position.x, position.y,48,48);
        }
        else if(check.equals("TURN_UP"))
        {
            gc.drawImage(images.get(3), position.x, position.y,48,48);
        }
        else if(check.equals("TURN_DOWN"))
        {
            gc.drawImage(images.get(1), position.x, position.y,48,48);
        }
    }

    public void drawEnemyHealth(AnchorPane root){
        root.getChildren().remove(healthBar);
        root.getChildren().add(healthBar);
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
