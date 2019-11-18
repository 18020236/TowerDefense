package Game;

import Scene.SceneManager;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameStage {

    protected static Stage stage = new Stage();

    GameStage(Stage stage)
    {
        this.stage=stage;
    }

    public void run()
    {
//        stage.setTitle( "Tower Defense" );
//
        AnchorPane root = new AnchorPane();
//
//        Scene theScene = new Scene( root , Config.WIDTH, Config.HEIGHT);
//        stage.setScene( theScene );
//        Canvas canvas = new Canvas( Config.WIDTH, Config.HEIGHT );
//        root.getChildren().add( canvas );
//        System.out.println(Player.getPlayer().getLives());
////        if (GameField.gameOver) {
////            System.out.println("------------------------");
////            root.getChildren().add(GameField.text("GAME OVER", 200, 300));
//        }
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        Control control = new Control(root);
//        theScene.setOnMouseMoved(Control::mouseMoved);
//        theScene.setOnMouseClicked(Control::mouseClicked);
//
//        GameField gameField= new GameField(gc, root);
//        gameField.start();
//
//
        SceneManager sceneManager = new SceneManager(stage, root);
        sceneManager.goToMenuScene(sceneManager);
//        stage.show();
    }
}
