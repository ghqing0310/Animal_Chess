import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by ghqing on 16/12/26.
 */
public class Dog extends Animal{

    public Dog(boolean camp){

        super(camp);
        this.strength = 4;
        this.path = "File:animals/4Dog" + (camp ? "Left" : "Right") + ".png";
        this.name = "狗";
    }

    @Override
    public Dog clone() throws CloneNotSupportedException {
        return (Dog)super.clone();
    }

}
