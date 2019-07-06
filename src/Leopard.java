import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by ghqing on 16/12/26.
 */
public class Leopard extends Animal {

    public Leopard(boolean camp){

        super(camp);
        this.strength = 5;
        this.path = "File:animals/5Leopard" + (camp ? "Left" : "Right") + ".png";
        this.name = "豹";
    }

    @Override
    public Leopard clone() throws CloneNotSupportedException {
        return (Leopard) super.clone();
    }

}
