import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by ghqing on 16/12/26.
 */
public class Cat extends Animal{

    public Cat(boolean camp){

        super(camp);
        this.strength = 2;
        this.path = "File:animals/2Cat" + (camp ? "Left" : "Right") + ".png";
        this.name = "猫";

    }

    @Override
    public Cat clone() throws CloneNotSupportedException {
        return (Cat)super.clone();
    }

}
