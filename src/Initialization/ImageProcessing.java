package Initialization;

import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ImageProcessing {
    public static Image imageSheet = new Image("Initialization/Entity.png");

    public static Image splits(int x, int y)
    {
        ImageView imageView = new ImageView(imageSheet);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        parameters.setViewport(new Rectangle2D(x*64,y*64,64,64));
        return imageView.snapshot(parameters, null);
    }

    public static Image rotate(Image img, double angle)
    {
        ImageView imageView = new ImageView(img);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        parameters.setViewport(new Rectangle2D(0,0,64,64));
        imageView.setRotate(angle);
        return imageView.snapshot(parameters, null);
    }
}
