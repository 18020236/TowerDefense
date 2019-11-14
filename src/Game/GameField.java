package Game;


import Enemy.Enemy;
import Enemy.EnemyGenerator;
import Initialization.Background;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//import java.awt.*;

public class GameField extends AnimationTimer {

    protected GraphicsContext gc;
    protected Stage stage;
    private int round;

    private final int startingLevel = 1;

    public GameField(GraphicsContext gc) {
        this.gc = gc;
        road = new Background(gc);

        restartGame();

//        enemyQ = new LinkedList<>();
//
//        for (int j = 0; j < 100; j++) {
//            if (Math.round(Math.random()) == 0) {
//                enemyQ.add(new NormalEnemy(gc));
//            } else {
//                if (Math.round(Math.random()) == 0){
//                    enemyQ.add(new TankerEnemy(gc));
//                } else enemyQ.add(new GunShip(gc));
//            }
//        }

//        for (int j = 0; j < 10; j++) {
//            enemyQ.add(new TankerEnemy(gc));
//        }
    }

    private Background road;
    private static Queue<Enemy> enemyQueue = new LinkedList<Enemy>();
    public  static Queue<Enemy> activeEnemyQueue = new LinkedList<Enemy>();
    private ArrayList<Enemy> enemyList;
    private final int enemySpawnDelay = 20;
    private static boolean waveIsInProgress = true;
    static long tickCount = 0;
    private boolean gameOver = false;
    private static int currentLevel = 0;

    EnemyGenerator generator;
    Font font ;
    Text noti = new Text();

    public void addEnemiesToActiveEnemyQueue(){
        tickCount++;
        if(tickCount > enemySpawnDelay){
            activeEnemyQueue.add(enemyQueue.poll());
            tickCount = 0;
        }
    }


    public void restartGame() {
        currentLevel = startingLevel;
        Player.getPlayer().reset();

        waveIsInProgress = true;
        enemyQueue = new LinkedList<Enemy>();
        activeEnemyQueue = new LinkedList<Enemy>();
//        towerList =  new ArrayList<Tower>();
        createEnemyQueueForLevel();
        gameOver = false;

    }


    public void createEnemyQueueForLevel(){
        generator = new EnemyGenerator(currentLevel - 1);
        generator.createEnemyQueue(gc);
        generator.RandomizeEnemyQueue();
        enemyQueue = generator.getCritterQueue();
        activeEnemyQueue = new LinkedList<Enemy>();
        activeEnemyQueue.add(enemyQueue.poll());
    }

    public void updateEnemies(){
        ArrayList<Enemy> enemiesToRemove = new ArrayList<Enemy>();
        //for each enemy list, update their movement if they are alive
        for(Enemy s : activeEnemyQueue){
            //only living enemies can move!
            if(s.isAlive())
                s.update(road.getMap());
            else{
                Player.getPlayer().addCredits(s.getPrize());
                enemiesToRemove.add(s);
            }

            if(s.isAtEndPoint()){
                Player.getPlayer().decreaseLife();
                enemiesToRemove.add(s);
            }
        }

        //remove all the dead critters and the critters that have arrived at the exit
        for(Enemy s : enemiesToRemove){
            activeEnemyQueue.remove(s);
        }

        if(enemyQueue.size()==0 && activeEnemyQueue.size() == 0){
            waveIsInProgress = false;
            currentLevel++;
        }
    }

    public void update() {

        if(waveIsInProgress){
            if(enemyQueue.size()!=0){
                addEnemiesToActiveEnemyQueue();
            }
            if(!gameOver){
                updateEnemies();
//                targetEnemies();
//                attackEnemies();
            }
        }

//        if(Mouse.isButtonDown(0)){
//            mouseClicked(Mouse.getX(), container.getHeight() - Mouse.getY(), sbg, container);
//        }

        if(Player.getPlayer().getLives() <= 0){
            System.out.println(Player.getPlayer().getLives() + "het mau cmnr");
            gameOver = true;
        }

//        for (int i = 0; i < enemyList.size(); i++) {
//            enemyList.get(i).update(road.getMap());
//        }
    }

//    private void drawMapandOverlay(GameContainer container, Graphics g){
//        //draw map and background
//
//        //draw the hearts
//        for(int x = 0 ; x < Player.getPlayer().getLives() ; x++){
//            if(x<8)
//                HeartGraphic.draw(x * (5 + HeartGraphic.getWidth()), currentMap.getHeightOfMap() * currentMap.getPixelSize() + 5);
//            else{
//                HeartGraphic.draw((x - 8) * (5 + HeartGraphic.getWidth()), currentMap.getHeightOfMap() * currentMap.getPixelSize() + 15 + HeartGraphic.getHeight());
//            }
//        }
//
//        //drawing buttons and overlays
//
//        //draw sell and upgrade buttons
//        int xCorner = currentMap.getWidthInPixel() +towerGraphicXStart + ((4)%2)*towerGraphicXOffset;
//        int yCorner = towerGraphicYStart + (4/2)*towerGraphicYOffset;
//        SellButtonGraphic.drawCentered(xCorner +towerButtonWidth/2,yCorner +towerButtonHeight/2);
//
//        xCorner = currentMap.getWidthInPixel() +towerGraphicXStart + ((5)%2)*towerGraphicXOffset;
//        yCorner = towerGraphicYStart + (5/2)*towerGraphicYOffset;
//        UpgradeButtonGraphic.drawCentered(xCorner +towerButtonWidth/2,yCorner +towerButtonHeight/2);
//
//        // drawing/updating the currency and level
//        ttf.drawString( CurrencyGraphic.getWidth() + 5, (container.getHeight() - 40), "" + Player.getPlayer().getCredits());
//        ttf.drawString(currentMap.getWidthInPixel() - 48, currentMap.getHeightInPixel() + 15, currentLevel + "");
//
//        //if the mouse is on the map, snap to map grid
//        if(mouseOnMap(Mouse.getX(),container.getHeight()-Mouse.getY())){
//            Image img;
//            switch(selectedTower){
//                case -3:
//                    img = SellSelectGraphic;
//                    break;
//                case -2:
//                    img = UpgradeSelectGraphic;
//                    break;
//                case -1:
//                    img = TileSelectGraphic;
//                    break;
//                case 0:
//                    img = BasicTowerGraphic;
//                    break;
//                case 1:
//                    img =  FreezeTowerGraphic;
//                    break;
//                case 2:
//                    img = SniperTowerGraphic;
//                    break;
//                default:
//                    img = TileSelectGraphic;
//                    break;
//            }
//            img.drawCentered(getClosestTileCenter(Mouse.getX()), container.getHeight() - getClosestTileCenter(Mouse.getY()));
//        }
//    }


    public void draw() {
        road.draw();
        if(waveIsInProgress){
            for (Enemy s : activeEnemyQueue) {
                s.draw();
            }
        }

        if(gameOver){
            TextFlow text_flow = new TextFlow();
            // create text
            Text noti = new Text("GAME OVER\n");
            // set the text color
            noti.setFill(Color.BLACK);
            // create a font
            Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 40);
            // set font of the text
            noti.setFont(font);
            // set text
            text_flow.getChildren().add(noti);
            // set line spacing
            text_flow.setLineSpacing(20.0f);
        }
//        for (Enemy s : activeEnemyQueue) {
//            s.draw();
//        }
    }

    @Override
    public void handle(long now) {
        update();
        draw();
    }
}
