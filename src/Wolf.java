import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by ghqing on 16/12/26.
 */
public class Wolf extends Animal{

    public Wolf(boolean camp){

        super(camp);
        this.strength = 3;
        this.path = "File:animals/3Wolf" + (camp ? "Left" : "Right") + ".png";
        this.name = "狼";
    }

    @Override
    public Wolf clone() throws CloneNotSupportedException {
        return (Wolf) super.clone();
    }
}
