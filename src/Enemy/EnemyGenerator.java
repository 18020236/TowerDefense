package Enemy;

import java.util.LinkedList;
import java.util.Queue;

public class EnemyGenerator {

    private Queue<Enemy> CritterQueue 	= new LinkedList<Enemy>();
    private int 				level;
    private int[][]				Locations;


    public EnemyGenerator(int level){
        this.level = level - 1;
    }
}
