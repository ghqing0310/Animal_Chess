import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by ghqing on 16/12/26.
 */
public class Mouse extends Animal{

    public Mouse(boolean camp){

        super(camp);
        this.strength = 1;
        this.path = "File:animals/1Mouse" + (camp ? "Left" : "Right") + ".png";
        this.name = "鼠";

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
        if (animal[yAfter][xAfter] != null) {

            if (animal[y][x].getCamp() == animal[yAfter][xAfter].getCamp()) {
                return false;
            }
            if (tileArray[yAfter][xAfter].equals(animal[yAfter][xAfter].getCamp() ? "4" : "2")){
                return true;
            }
            if (animal[yAfter][xAfter].getStrength() != 8) {
                return false;
            }
            if (tileArray[y][x].equals("1")){
                return false;
            }  //检查水里的鼠是否吃象
        }

        return true;
    }

    @Override
    public Mouse clone() throws CloneNotSupportedException {
        return (Mouse)super.clone();
    }
}
