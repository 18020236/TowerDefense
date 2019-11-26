package Scene;

import Game.GameField;
import Initialization.Config;
import Initialization.Control;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class SceneManager {
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Stage stage;
    private Timeline animation;
    private AnchorPane root;

    /**
     * Constructor for SceneManager class
     * @param primaryStage the Stage that this SceneManager uses
     */
    public SceneManager(Stage primaryStage, AnchorPane root) {
        this.root = root;
        this.stage = primaryStage;
        this.animation = new Timeline();
        stage.show();
    }

    /**
     * Sets the scene to be the Menu Scene
     * @param sceneManager SceneManager currently being used
     */
    public void goToMenuScene(SceneManager sceneManager) {
        animation.stop();
        Menu menu = new Menu(sceneManager);
        Scene menuScene = menu.init(Config.WIDTH, Config.HEIGHT + Config.PLAYER_BAR_HEIGHT);
        stage.setScene(menuScene);
    }

    public void goToGameOverScene(SceneManager sceneManager) {
        GameOver gameOver = new GameOver(sceneManager);
        Scene gameOverScene = gameOver.init(Config.WIDTH, Config.HEIGHT + Config.PLAYER_BAR_HEIGHT);
        stage.setScene(gameOverScene);
        stage.setResizable(false);
//        GameOver gameOver = new GameOver(sceneManager);
//        Scene gameOverScene = gameOver.init(Config.WIDTH, Config.HEIGHT + Config.PLAYER_BAR_HEIGHT);
        stage.setScene(gameOverScene);
    }

    public void goToGameScene() {
        stage.setTitle( "Tower Defense" );
        stage.setResizable(false);
        AnchorPane root = new AnchorPane();

        Scene scene = new Scene( root , Config.WIDTH, Config.HEIGHT + Config.PLAYER_BAR_HEIGHT);
        stage.setScene( scene );
        Canvas canvas = new Canvas( Config.WIDTH, Config.HEIGHT );
        root.getChildren().add( canvas );
//        System.out.println(Player.getPlayer().getLives());
//        if (GameField.gameOver) {
//            System.out.println("------------------------");
//            root.getChildren().add(GameField.text("GAME OVER", 200, 300));
//        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Control control = new Control(root);
        scene.setOnMouseMoved(Control::mouseMoved);
//        scene.setOnMouseClicked(Control::mouseClicked);

        GameField gameField = new GameField(gc, root, stage);
        gameField.start();

        stage.show();
    }

    private void setGameLoop(KeyFrame frame) {
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }
//    /**
//     * Sets the scene to be the Instructions Scene
//     * @param sceneManager SceneManager currently being used
//     */
//    public void goToInstructionsScene(SceneManager sceneManager) {
//        animation.stop();
//        Instructions instructions = new Instructions(sceneManager);
//        Scene instructionsScene = instructions.init(Main.SIZE, Main.SIZE);
//        stage.setScene(instructionsScene);
//    }
//
//    /**
//     * Sets the scene to be GameOver Scene
//     * @param sceneManager SceneManager currently being used
//     */
//    public void goToGameOverScene(SceneManager sceneManager) {
//        animation.stop();
//        GameOver gameOver = new GameOver(sceneManager);
//        Scene gameOverScene = gameOver.init(Main.SIZE, Main.SIZE);
//        stage.setScene(gameOverScene);
//    }
//
//    /**
//     * Sets the scene to be the GameWon Scene
//     * @param sceneManager SceneManager currently being used
//     */
//    public void goToGameWonScene(SceneManager sceneManager) {
//        animation.stop();
//        GameWon gameWon = new GameWon(sceneManager);
//        Scene gameWonScene = gameWon.init(Main.SIZE, Main.SIZE);
//        stage.setScene(gameWonScene);
//    }
//
//    /**
//     * Sets the scene to be the NextLevel Scene
//     * @param sceneManager SceneManager currently being used
//     * @param level the next level
//     */
//    public void goToNextLevelScene(SceneManager sceneManager, int level) {
//        animation.stop();
//        NextLevel nextLevel = new NextLevel(sceneManager, level);
//        Scene nextLevelScene = nextLevel.init(Main.SIZE, Main.SIZE);
//        stage.setScene(nextLevelScene);
//    }
//
//    /**
//     * Sets the scene to be Battle Scene
//     * @param sceneManager SceneManager currently being used
//     * @param level the next level
//     */
//    public void goToBattleScene(SceneManager sceneManager, int level) {
//        Battle battle = new Battle(sceneManager, level);
//        Scene battleScene = battle.init(Main.SIZE, Main.SIZE);
//        stage.setScene(battleScene);
//
//        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> battle.step(SECOND_DELAY));
//        setGameLoop(frame);
//    }
//
//    /**
//     * Sets the scene to be the BossBattle Scene
//     * @param sceneManager SceneManager currently being used
//     */

}
