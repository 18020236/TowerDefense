package Game;

import Scene.SceneManager;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameStage {

    protected static Stage stage = new Stage();
    static SceneManager sceneManager;
    GameStage(Stage stage)
    {
        this.stage=stage;
    }

    public void run()
    {
        AnchorPane root = new AnchorPane();
        sceneManager = new SceneManager(stage, root);
        sceneManager.goToMenuScene(sceneManager);
    }
}
