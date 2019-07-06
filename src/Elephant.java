import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by ghqing on 16/12/26.
 */
public class Elephant extends Animal {

    public Elephant(boolean camp){

        super(camp);
        this.strength = 8;
        this.path = "File:animals/8Elephant" + (camp ? "Left" : "Right") + ".png";
        this.name = "象";

    }

    @Override
    public boolean checkCanMove(String[][] tileArray, Animal[][] animal, int x, int y, int xAfter, int yAfter) {

        if (!(Math.abs(xAfter - x) == 1 && yAfter == y|| Math.abs(yAfter - y) == 1 && xAfter == x)){
            return false;
        }
        if (xAfter < 0 || xAfter > 8 || yAfter < 0 || yAfter > 6){
            return false;
        }
        if (tileArray[yAfter][xAfter].equals("3") && animal[y][x].getCamp() ||
                tileArray[yAfter][xAfter].equals("5") && !animal[y][x].getCamp()){
            return false;
        }
        if (tileArray[yAfter][xAfter].equals("1")){
            return false;
        }
        if (animal[yAfter][xAfter] != null) {

            if (animal[y][x].getCamp() == animal[yAfter][xAfter].getCamp()) {
                return false;
            }
            if (tileArray[yAfter][xAfter].equals(animal[yAfter][xAfter].getCamp() ? "4" : "2")){
                return true;
            }
            if (animal[yAfter][xAfter].getStrength() == 1) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Elephant clone() throws CloneNotSupportedException {
        return (Elephant) super.clone();
    }

}
