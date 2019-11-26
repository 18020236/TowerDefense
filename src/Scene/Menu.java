package Scene;

import Game.Audio;
import Initialization.Config;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;

/**
 * This class represents the Menu Scene from where the levels can be started.
 * It is the main starting Scene for almost every action. From here, the player
 * can either go read the instructions or start the game.
 *
 * @author Daniel Chai (dhc10)
 * @version 1.0
 */
public class Menu implements SceneInterface {
    public static final String DEFAULT_FONT_FAMILY = "Verdana";
    public static final int DEFAULT_FONT_SIZE = 20;
    public static final double DEFAULT_BUTTON_WIDTH = 100;
    public static final double DEFAULT_BUTTON_HEIGHT = 25;

    private SceneManager sceneManager;
    private Scene menuScene;
    private Group root;

    String path = "src/Resources/Audio/determination.mp3";

    //Instantiating Media class
    Media bg_audio = new Media(new File(path).toURI().toString());

    //Instantiating MediaPlayer class
    MediaPlayer bg_player = new MediaPlayer(bg_audio);
    Audio clickAudio = new Audio("src/Resources/Audio/Click.mp3");
    /**
     * Constructor for Menu class
     * @param sceneManager SceneManager currently being used
     */
    public Menu(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Returns the Menu Scene
     */
    @Override
    public Scene init(int width, int height) {
        bg_player.setAutoPlay(true);
//        mediaPlayer.autoPlayProperty();
        bg_player.setCycleCount(MediaPlayer.INDEFINITE);
        bg_player.setStartTime(Duration.seconds(0));
        bg_player.setStopTime(Duration.seconds(220));

        Image image = new Image("Resources/Map/tower_defense_background.png");
        //Setting the image view
        ImageView imageView = new ImageView(image);
        imageView.fitWidthProperty();
        imageView.setFitWidth(Config.WIDTH);
        imageView.setFitHeight(Config.HEIGHT + Config.PLAYER_BAR_HEIGHT);
        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(false);


        root = new Group(imageView);

//        addStartButton("Guide");

        menuScene = new Scene(root, width, height, Color.AZURE);
        createButtonImage("Resources/Map/map.png", 125, 240);
        return menuScene;
    }

    public static Button createButton(String text, double x, double y) {
        return createButton(text, x, y, DEFAULT_BUTTON_WIDTH, DEFAULT_BUTTON_HEIGHT, DEFAULT_FONT_SIZE);
    }

    public void createButtonImage(String path, int x, int y) {
        Image map = new Image(path);
        ImageView map1 = new ImageView(map);

        map1.setScaleX(0.4);
        map1.setScaleY(0.4);
        map1.setX(x);
        map1.setY(y);

        map1.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                clickAudio.play();
                sceneManager.goToGameScene();
                bg_player.stop();
            }

        });

        // TODO other event handlers like mouse up
        root.getChildren().add(map1);
    }

    public static Button createButton(String text, double x, double y, double width, double height, int fontSize) {
        Button buttonUI = new Button();
        buttonUI.setText(text);
        buttonUI.setLayoutX(x);
        buttonUI.setLayoutY(y);
        buttonUI.setMinWidth(width);
        buttonUI.setMinHeight(height);
        buttonUI.setFont(Font.font(DEFAULT_FONT_FAMILY, fontSize));

        return buttonUI;
    }

    private void addStartButton(String buttonName) {
        Button startButton = createButton(buttonName, 100, 150);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clickAudio.play();
                sceneManager.goToGameScene();
                bg_player.stop();
            }
        });

        root.getChildren().add(startButton);
    }

//    private void addInstructionsButton() {
//        Button instructionsButton = createButton("Instructions", 50, 100);
//
//        instructionsButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                sceneManager.goToInstructionsScene(sceneManager);
//            }
//        });
//
//        root.getChildren().add(instructionsButton);
//    }
}