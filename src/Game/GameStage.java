package Game;

import Initialization.Config;
import Initialization.Control;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
        stage.setTitle( "Tower Defense" );

        AnchorPane root = new AnchorPane();

        Scene theScene = new Scene( root );
        stage.setScene( theScene );

        Canvas canvas = new Canvas( Config.WIDTH, Config.HEIGHT );
        root.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Control control = new Control(root);
        theScene.setOnMouseMoved(Control::mouseMoved);
        theScene.setOnMouseClicked(Control::mouseClicked);

        GameField gameField= new GameField(gc);
        gameField.start();

        stage.show();

    }


}
