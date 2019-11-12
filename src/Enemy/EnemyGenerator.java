package Enemy;

import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;
import java.util.Queue;

public class EnemyGenerator {

    private Queue<Enemy>        EnemyQueue 	= new LinkedList<Enemy>();
    private int 				level;
    private int[][]				Locations;


    public EnemyGenerator(int level){
        this.level = level - 1;
    }

    int[][] EnemyStream = {
            //{smaller, normal, gunship, tanker, boss}
            {2,1,0,0,0},
            {5,2,0,0,0},
            {6,2,1,0,0},
            {6,3,2,2,1},
            {6,3,3,3,1},
            {8,4,4,4,1},
            {9,5,5,5,2},
            {10,6,6,6,2},
            {12,6,6,7,2},
            {15,6,8,8,2},
            {16,6,9,9,3},
            {20,8,10,10,3},
    };
    //automate critter list generation for 100 levels after hardcoded stream
    public int[][] addEnemyList(int lvlStart){
        int[][] cListToAppend = new int[1000][5];
        for(int i=lvlStart;i<1000+lvlStart;i++){
            for(int j=0;j<5;j++){
                cListToAppend[i-lvlStart][j] = (i/3) + (4-j)*5;
            }
        }
        return cListToAppend;
    }

    public void createEnemyQueue(GraphicsContext gc){
        //for that level, create the enemy objects as per the values in the enemy stream and then randomize the queue
        int[][] enemyStreamToAppend = addEnemyList(EnemyStream.length);
        int[][] fullEnemyStream = new int[EnemyStream.length + enemyStreamToAppend.length][5];

        //concatenate two 2d arrays
        for(int i=0;i<fullEnemyStream.length;i++){
            if(i<EnemyStream.length){
                fullEnemyStream[i]=EnemyStream[i];
            }
            else{
                fullEnemyStream[i]=enemyStreamToAppend[i-EnemyStream.length];
            }
        }

        for(int x = 0; x < 5 ; x++)
        {
            for(int y = 0; y < fullEnemyStream[level][x] ; y++){

                Enemy c = addEnemy(gc, x);
                EnemyQueue.add(c);

            }
        }
    }

    private Enemy addEnemy(GraphicsContext gc, int x){
        if(x==0){
            Enemy c = new SmallerEnemy(gc);
            return c;
        }
        if(x==1){
            Enemy c = new GunShip(gc);
            return c;
        }
        if(x==2){
            Enemy c = new TankerEnemy(gc);
            return c;
        }
        if(x==3){
            Enemy c = new NormalEnemy(gc);
            return c;
        }
        if(x==4){
            Enemy c = new BossEnemy(gc);
            return c;
        }
        return null;
    }

    public void printEnemyQueue(){

        System.out.print("<- [ ");
        for(Enemy s : EnemyQueue){
            System.out.print(s.getName()+" ");
        }
        System.out.println("]");
    }


    public Queue<Enemy> getCritterQueue() {
        return EnemyQueue;
    }

}
