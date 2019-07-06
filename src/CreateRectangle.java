import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * Created by ghqing on 16/12/31.
 */
public class CreateRectangle {

    public static Rectangle createRectangle(int x, int y){
        Rectangle rectangle = new Rectangle(x, y, 80, 80);
        rectangle.setFill(Color.YELLOW);
        return rectangle;
    }
}
