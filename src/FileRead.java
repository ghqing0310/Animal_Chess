import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by ghqing on 16/12/25.
 */
public class FileRead{

    //获取初始动物视图
    public static ImageView[][] getAnimalImageView(Animal[][] animal, ImageView[][] animalImageView) throws FileNotFoundException {

        for (int i = 0; i < animalImageView.length; i++) {
            for (int j = 0; j < animalImageView[0].length; j++) {
                if (animal[i][j] != null) {
                    animalImageView[i][j] = new ImageView(new Image(animal[i][j].getPath()));
                    animalImageView[i][j].setFitWidth(80);
                    animalImageView[i][j].setFitHeight(80);
                    animalImageView[i][j].setX(140 + 80 * j);
                    animalImageView[i][j].setY(120 + 80 * i);
                } else {
                    animalImageView[i][j] = new ImageView();
                    animalImageView[i][j].setImage(null);
                }
            }
        }

        return animalImageView;
    }

    //读取初始化动物数组
    public static Animal[][] getAnimal(Animal[][] animal) throws FileNotFoundException {

        Scanner animalScanner = new Scanner(new File("animal.txt"));
        String[][] animalArray = new String[7][9];
        boolean camp;

        for (int i = 0; i < animal.length; i++) {
            String animalString = animalScanner.nextLine();
            for (int j = 0; j < animal[0].length; j++) {
                animalArray[i][j] = "" + animalString.charAt(j);
                if (j / 2 < 2) {
                    camp = true;
                } else {
                    camp = false;
                }
                switch (animalArray[i][j].charAt(0)) {
                    case '0':
                        animal[i][j] = null;
                        break;
                    case '1':
                        animal[i][j] = new Mouse(camp);
                        break;
                    case '2':
                        animal[i][j] = new Cat(camp);
                        break;
                    case '3':
                        animal[i][j] = new Wolf(camp);
                        break;
                    case '4':
                        animal[i][j] = new Dog(camp);
                        break;
                    case '5':
                        animal[i][j] = new Leopard(camp);
                        break;
                    case '6':
                        animal[i][j] = new Tiger(camp);
                        break;
                    case '7':
                        animal[i][j] = new Lion(camp);
                        break;
                    case '8':
                        animal[i][j] = new Elephant(camp);
                        break;
                }
            }
        }

        return animal;

    }

    //初始化地形地图
    public static String[][] getMap() throws FileNotFoundException {

        Scanner tileScanner = new Scanner(new File("tile.txt"));
        String[][] tileArray = new String[7][9];

        for (int i = 0; i < 7; i++) {
            String tileString = tileScanner.nextLine();
            for (int j = 0; j < 9; j++) {
                tileArray[i][j] = "" + tileString.charAt(j);
            }
        }

        return tileArray;
    }

    //初始化面板
    public static Pane getJunglePane(Pane junglePane, ImageView[][] animalImageView){

        ImageView jungleImageView = new ImageView(new Image("File:Map3.png"));
        junglePane.getChildren().add(jungleImageView);

        for (int i = 0; i < animalImageView.length; i++){
            for (int j = 0; j < animalImageView[0].length; j++){
                if (animalImageView[i][j].getImage() != null) {
                    junglePane.getChildren().add(animalImageView[i][j]);
                    animalImageView[i][j].setX(140 + 80 * j);
                    animalImageView[i][j].setY(120 + 80 * i);
                }
            }
        }

        return junglePane;
    }
}
