import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;

/**
 * Created by ghqing on 17/1/4.
 */
public class FileRenew {

    //从面板移走小方块
    public static Pane removeRectangle(Pane junglePane, Rectangle[] rectangle){

        for (int i = 0; i < rectangle.length; i++) {
            if (rectangle[i] != null) {
                junglePane.getChildren().remove(rectangle[i]);
            }
        }

        return junglePane;
    }

    //从面板移走动物视图
    public static Pane removeAnimalImageView(Pane junglePane, ImageView[][] animalImageView){

        for (int i = 0; i < animalImageView.length; i++) {
            for (int j = 0; j < animalImageView[0].length; j++) {
                junglePane.getChildren().remove(animalImageView[i][j]);
            }
        }

        return junglePane;
    }

    //通过动物视图数组更新面板
    public static Pane renewJunglePane(Pane junglePane, ImageView[][] animalImageView){

        for (int i = 0; i < animalImageView.length; i++){
            for (int j = 0; j < animalImageView[0].length; j++) {
                if (animalImageView[i][j].getImage() != null) {
                    junglePane.getChildren().add(animalImageView[i][j]);
                    animalImageView[i][j].setX(140 + 80 * j);
                    animalImageView[i][j].setY(120 + 80 * i);
                }
            }
        }

        return junglePane;
    }

    //通过动物数组更新动物视图数组
    public static ImageView[][] renewAnimalImageView(Animal[][] animal, ImageView[][] animalImageView){

        for (int i = 0; i < animalImageView.length; i++) {
            for (int j = 0; j < animalImageView[0].length; j++) {
                if (animal[i][j] != null) {
                    animalImageView[i][j].setImage(new Image(animal[i][j].getPath()));
                    animalImageView[i][j].setFitWidth(80);
                    animalImageView[i][j].setFitHeight(80);
                    animalImageView[i][j].setX(140 + 80 * j);
                    animalImageView[i][j].setY(120 + 80 * i);
                } else {
                    animalImageView[i][j].setImage(null);
                }
            }
        }

        return animalImageView;
    }

    //重新开始
    public static void restart(Pane junglePane, GameHistory gameHistory, Animal[][] animal, ImageView[][] animalImageView, boolean[] player) throws FileNotFoundException {

        junglePane = removeAnimalImageView(junglePane, animalImageView);
        animal = gameHistory.restart(animal);
        animalImageView = renewAnimalImageView(animal, animalImageView);
        junglePane = renewJunglePane(junglePane, animalImageView);
        player[0] = true;

    }

   //悔棋
    public static void undo(Pane junglePane, GameHistory gameHistory, Animal[][] animal, ImageView[][] animalImageView, boolean[] player){

        junglePane = removeAnimalImageView(junglePane, animalImageView);
        animal = gameHistory.undo(animal);
        animalImageView = renewAnimalImageView(animal, animalImageView);
        junglePane = renewJunglePane(junglePane, animalImageView);
        player[0] = !player[0];
    }
}
