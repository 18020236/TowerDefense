package Game;

import Bullet.Bullet;
import Enemy.Enemy;
import Enemy.EnemyGenerator;
import Initialization.Background;
import Initialization.Config;
import Initialization.ImageProcessing;
import Scene.SceneManager;
import Tower.*;
import com.sun.javafx.geom.Vec2d;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GameField extends AnimationTimer {
    protected GraphicsContext gc;
    Stage stage;
    private final int startingLevel = 1;
    protected AnchorPane root;
    SceneManager sceneManager;

    public GameField(GraphicsContext gc, AnchorPane root, Stage stage) {
        this.gc = gc;
        this.root = root;
        this.stage = stage;
        road = new Background(gc);
        gameOver = false;
        createTowerMenu();
        restartGame();
    }

    private Background road;
    Audio bgAudio = new Audio("src/Resources/Audio/Brirfing_theme.mp3");

//    String path = "src/Resources/Audio/Brirfing_theme.mp3";
//
//    //Instantiating Media class
//    Media bg_audio = new Media(new File(path).toURI().toString());
//
//    //Instantiating MediaPlayer class
//    MediaPlayer bg_player = new MediaPlayer(bg_audio);

    //by setting this property to true, the audio will be player
    private static Queue<Enemy> enemyQueue = new LinkedList<Enemy>();
    public static Queue<Enemy> activeEnemyQueue = new LinkedList<Enemy>();
    private static ArrayList<Tower> towerList = new ArrayList<Tower>();
    private static ArrayList<Bullet> bulletList = new ArrayList<Bullet>();

    private final int enemySpawnDelay = 50;
    private static boolean waveIsInProgress = true;
    static long tickCount = 0;
    public static boolean gameOver = false;
    private static int currentLevel = 0;
    private String level;
    private String lives;
    private String cash;
    EnemyGenerator generator;
    private TowerMenu nT, sT , mT;
    private VBox towerMenu = new VBox();
    private ImageView tower = null;
    private double tempX, tempY;
    private String whichTower;
    Font font = Font.font("Verdana", FontWeight.NORMAL, 20);
    private Text textLevel = new Text("");
    private Text textLives = new Text("");
    private Text textCash = new Text("");
    private Text textGameOver = new Text("GAME OVER");

    boolean isUpdate = true;

    ImageView restartButton;
    ImageView pauseButton;

    public void addEnemiesToActiveEnemyQueue() {
        tickCount++;
        if (tickCount > enemySpawnDelay) {
            activeEnemyQueue.add(enemyQueue.poll());
            tickCount = 0;
        }
    }

    public boolean canPlaceTowerHere(int x, int y) {
        if((y==12 || y==13) && x>=0 && x<= 13) return false;
        if((y==12 || y==13) && x>=20 && x<=24) return false;
        if((y==1  || y ==2) && x>=2 && x<=21) return false;
        if((y==6 || y==7) && x>=2 && x<=13) return false;
        if((x==12 || x==13) && y>=6 && y<=13) return false;
        if((x==2 || x==3) && y>=1 && y<=7) return false;
        if((x==20 || x==21) && y>=3 && y<= 13 ) return false;
        return true;
    }

    public void addListener(Stage stage) {

        // Listen to the click of the Tower Button
        nT.addEventFilter(MouseEvent.MOUSE_CLICKED, e ->{
            if(Player.getPlayer().getCredits() >= 50) {
                if (tower == null) {
                    tower = new ImageView(ImageProcessing.splits(19,8));
                    whichTower = "Normal";
                    tower.setFitWidth(32);
                    tower.setFitHeight(32);
                    tower.setX(stage.getWidth() - (nT.getPrefWidth()/2));
                    tower.setY(0);
                    tower.setPreserveRatio(true);
                    root.getChildren().add(tower);
                }
            }
        } );
        sT.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if(Player.getPlayer().getCredits() >= 150) {
                if(tower == null) {
                    tower = new ImageView(ImageProcessing.splits(20,10));
                    whichTower = "Sniper";
                    tower.setFitWidth(32);
                    tower.setFitHeight(32);
                    tower.setX(stage.getWidth() - (sT.getPrefWidth()/2));
                    tower.setY(20);
                    tower.setPreserveRatio(true);
                    root.getChildren().add(tower);
                }
            }
        });

        mT.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if(Player.getPlayer().getCredits() >= 100) {
                if(tower == null) {
                    tower = new ImageView(ImageProcessing.splits(19,10));
                    whichTower = "MachineGun";
                    tower.setFitWidth(32);
                    tower.setFitHeight(32);
                    tower.setX(stage.getWidth() - (mT.getPrefWidth()/2));
                    tower.setY(50);
                    tower.setPreserveRatio(true);
                    root.getChildren().add(tower);
                }
            }
        });



        // MOVE THE TOWER AROUND ON THE SCREEN

        stage.getScene().addEventHandler(MouseEvent.MOUSE_MOVED, e -> {
            if(tower != null) {
                tower.setVisible(true);
                tempX = e.getX() - (tower.getFitWidth() / 2);
                tempY = e.getY() - (tower.getFitHeight() / 2);
                tower.setX(e.getX() - (tower.getFitWidth() / 2));
                tower.setY(e.getY() - (tower.getFitHeight() / 2));
            }
        });

        // PLACES THE TOWER ON THE CLICKED POSITION
        stage.getScene().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            int xForCheck =  (int)((Math.round(tempX)+16)/ Config.LENGTH_IMAGE);
            int yForCheck =  (int)((Math.round(tempY)+16)/ Config.LENGTH_IMAGE);
            if(tower != null && canPlaceTowerHere(xForCheck,yForCheck) ) {
                if(whichTower.equals("Normal")) {
                    towerList.add(new NormalTower(gc,new Vec2d(tempX,tempY),activeEnemyQueue));
                    Player.getPlayer().addCredits(-50);
                    whichTower = "";
                    root.getChildren().remove(tower);
                    tower = null;
                }

                if(whichTower.equals("Sniper")) {
                    towerList.add(new SniperTower(gc,new Vec2d(tempX,tempY),activeEnemyQueue));
                    Player.getPlayer().addCredits(-150);
                    whichTower = "";
                    root.getChildren().remove(tower);
                    tower = null;
                }

                if(whichTower.equals("MachineGun")) {
                    towerList.add(new MachineGunTower(gc,new Vec2d(tempX,tempY),activeEnemyQueue));
                    Player.getPlayer().addCredits(-100);
                    whichTower = "";
                    root.getChildren().remove(tower);
                    tower = null;
                }
            }
        });

    }


    public void restartGame() {
        isUpdate = true;
        restartButton = createButtonImage("Resources/restart-button-png-1.png", 420, 405);
        pauseButton = createPauseButton("Resources/pause.png", 520, 390);
        root.getChildren().add(restartButton);
        root.getChildren().add(pauseButton);
        if (root.getChildren().contains(textGameOver)){
            root.getChildren().remove(textGameOver);
        }
        for (Enemy e: activeEnemyQueue){
            root.getChildren().remove(e.healthBar());
        }
        towerList.clear();
        enemyQueue.clear();
        activeEnemyQueue.clear();
        currentLevel = startingLevel;
        Player.getPlayer().reset();
        waveIsInProgress = true;
        enemyQueue = new LinkedList<Enemy>();
        activeEnemyQueue = new LinkedList<Enemy>();
        createEnemyQueueForLevel();
        gameOver = false;
        addListener(stage);
        bgAudio.playCycle(36);
    }

    public void createTowerMenu() {
        nT = new TowerMenu("Normal $50 ");
        sT = new TowerMenu("Sniper $150 ");
        mT = new TowerMenu("MG $100 ");
        towerMenu.getChildren().add(nT);
        towerMenu.getChildren().add(sT);
        towerMenu.getChildren().add(mT);
        towerMenu.setLayoutX(stage.getWidth() - mT.getPrefWidth());
        towerMenu.setLayoutY(0);
        root.getChildren().add(towerMenu);
    }

    public void createEnemyQueueForLevel() {
        generator = new EnemyGenerator(currentLevel - 1);
        generator.createEnemyQueue(gc);
     //   generator.RandomizeEnemyQueue();
        enemyQueue = generator.getCritterQueue();
        activeEnemyQueue.add(enemyQueue.poll());
    }

    public void updateEnemies() {
        ArrayList<Enemy> enemiesToRemove = new ArrayList<Enemy>();
        //for each enemy list, update their movement if they are alive
        for (Enemy s : activeEnemyQueue) {
            //only living enemies can move!
            if (s.isAlive())
                s.update(road.getMap());
            else {
                Player.getPlayer().addCredits(s.getPrize());
                enemiesToRemove.add(s);
            }

            if (s.isAtEndPoint()) {
                Player.getPlayer().decreaseLife();
                root.getChildren().remove(s.healthBar());
                enemiesToRemove.add(s);
            }
        }

        //remove all the dead enemies and the enemies that have arrived at the exit
        for (Enemy s : enemiesToRemove) {
            root.getChildren().remove(s.healthBar());
            activeEnemyQueue.remove(s);
        }

        if (gameOver == false && enemyQueue.size() == 0 && activeEnemyQueue.size() == 0) {
//            waveIsInProgress = false;
            currentLevel++;
            createEnemyQueueForLevel();
        }
    }

    public void update() {
        if (waveIsInProgress) {
            if (enemyQueue.size() != 0) {
                addEnemiesToActiveEnemyQueue();
            } else if (gameOver == false && enemyQueue.size() == 0 && activeEnemyQueue.size() == 0) {
                currentLevel++;
            }
            if (!gameOver) {
                updateEnemies();
                for(int i = 0;i <towerList.size();i++) {
                    towerList.get(i).update();
                }
            }
        }

        if (Player.getPlayer().getLives() <= 0 && gameOver == false) {
            System.out.println(Player.getPlayer().getLives() + " health ====> GAME OVER");
            isUpdate = false;
            gameOver = true;
        }
    }

    public void drawText(Text text, int size, double x, double y, Paint value) {
        Font font = Font.font("Verdana", FontWeight.NORMAL, size);
        text.setFill(value);
        text.setFont(font);
        text.setX(x);
        text.setY(y);
        root.getChildren().remove(text);
        root.getChildren().add(text);
    }

    public ImageView createButtonImage(String path, int x, int y) {
        Image map = new Image(path);
        ImageView map1 = new ImageView(map);

        map1.setScaleX(0.2);
        map1.setScaleY(0.2);
        map1.setX(x);
        map1.setY(y);

        map1.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                restartGame();
                System.out.println(3);
            }

        });

        // TODO other event handlers like mouse up
        return map1;
    }

    public ImageView createPauseButton(String path, int x, int y) {
        Image pause = new Image(path);
        ImageView pauseIV = new ImageView(pause);

        pauseIV.setScaleX(0.2);
        pauseIV.setScaleY(0.2);
        pauseIV.setX(x);
        pauseIV.setY(y);

        pauseIV.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                System.out.println("paused");
                if (isUpdate) isUpdate = false;
                else isUpdate = true;
            }
        });

        // TODO other event handlers like mouse up
        return pauseIV;
    }

    public void draw() {
        road.draw();
        if (waveIsInProgress) {
            for (Enemy s : activeEnemyQueue) {
                s.draw();
                s.drawEnemyHealth(root);
            }
            for (Tower t : towerList) {
                t.draw();
            }
        }

        lives = "LIVES " + Player.getPlayer().getLives();
        cash = "CASH " + Player.getPlayer().getCredits();
        level = "LEVEL " + currentLevel;
        textLives.setText(lives);
        textCash.setText(cash);
        textLevel.setText(level);
        drawText(textLives, 20, 280, Config.HEIGHT + Config.PLAYER_BAR_HEIGHT - 15, Color.RED);
        drawText(textCash, 20, 380, Config.HEIGHT + Config.PLAYER_BAR_HEIGHT - 15, Color.GREEN);
        drawText(textLevel, 20, 515, Config.HEIGHT + Config.PLAYER_BAR_HEIGHT - 15, Color.BROWN);

        if (gameOver) {
            root.getChildren().remove(textLives);
            root.getChildren().remove(textLevel);
            root.getChildren().remove(textCash);
            drawText(textGameOver, 100, 80, 180, Color.RED);
        }
    }

    @Override
    public void handle(long now) {
        if (isUpdate) update();
        draw();
    }
}