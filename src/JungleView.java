import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

/**
 * Created by ghqing on 16/12/18.
 */
public class JungleView extends Application {

    Animal[][] animal = FileRead.getAnimal(new Animal[7][9]);
    private GameHistory gameHistory = new GameHistory();
    private String[][] tileArray = FileRead.getMap();
    private ImageView[][] animalImageView = FileRead.getAnimalImageView(animal, new ImageView[7][9]);
    private Pane junglePane = FileRead.getJunglePane(new Pane(), animalImageView);
    private boolean[] player = {true};

    public JungleView() throws FileNotFoundException {
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        primaryStage.setTitle("AnimalChess");
        primaryStage.setResizable(false);

        gameHistory.addRecord(animal);

        //帮助，退出，重新开始
        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(20, 20, 20, 20));
        junglePane.getChildren().add(hBox);
        new Buttons().help(hBox);
        new Buttons().exit(hBox);
        Buttons.restart(junglePane, hBox, gameHistory, animal, animalImageView, player);
        Buttons.undo(junglePane, hBox, gameHistory, animal, animalImageView, player);

        junglePane.setOnMouseClicked(new AnimalClickHandler());

        Scene scene = new Scene(junglePane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

     public class AnimalClickHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {

            int x = getIndexOfX(event.getX());
            int y = getIndexOfY(event.getY());

            new Buttons().cannotMove(animal, tileArray, player[0], x, y);

            if (animal[y][x] != null) {
                if (animal[y][x].getCamp() == player[0]) {
                    animal[y][x].createRectangle(junglePane, animalImageView, gameHistory, animal, tileArray, player[0], x, y);
                    player[0] = !player[0];
                }
            }
        }  //产生显示能走的黄色小方块

        private int getIndexOfX(double x) {

            return (int) ((x - 140) / 80);
        }  //捕获点击像素所在行

        private int getIndexOfY(double y) {

            return (int) ((y - 120) / 80);
        }  //捕获点击像素所在列
    }
}