package Enemy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class HealthBar extends Pane {

    Rectangle outerHealthRect;
    Rectangle innerHealthRect;

    public HealthBar() {
        double height = 3;

        double outerWidth = 40;
        double innerWidth = 30;

        double x=0.0;
        double y=0.0;

        outerHealthRect = new Rectangle( x, y, outerWidth, height);
        outerHealthRect.setStrokeType( StrokeType.OUTSIDE);
        outerHealthRect.setFill(Color.RED);

        innerHealthRect = new Rectangle( x, y, innerWidth, height);
        innerHealthRect.setStrokeType( StrokeType.OUTSIDE);
        innerHealthRect.setFill(Color.LIMEGREEN);

        getChildren().addAll( outerHealthRect, innerHealthRect);

    }

    public void setValue( double value, double x, double y) {
        innerHealthRect.setWidth( outerHealthRect.getWidth() * value);
        outerHealthRect.setX(x);
        outerHealthRect.setY(y);
        innerHealthRect.setX(x);
        innerHealthRect.setY(y);
    }

}
