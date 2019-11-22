package Initialization;

import Resources.Audio.Audio;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Control {

    private Button button1;
    private Button button2;
    private VBox layout = new VBox();
    private Scene scene;
    static Audio clickAudio = new Audio("src/Resources/Audio/Click.mp3");
    public Control(AnchorPane root) {
        button1 = new Button("Normal Tower",new ImageView(ImageProcessing.splits(15,11)));
        button1.setMinSize(100,50);
        button1.setOnAction(event -> {
            System.out.println("clicked");
        });

        button2 = new Button("test");
        layout.getChildren().addAll(button1, button2);

        AnchorPane.setLeftAnchor(layout, (double) 64 * 20);
        root.getChildren().add(layout);
    }


    public static void mouseMoved(MouseEvent mouseEvent) {
//        System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
    }

    public static void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("YES");
        clickAudio.stop();
        clickAudio.play();
    }
}
