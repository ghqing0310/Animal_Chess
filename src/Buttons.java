import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

/**
 * Created by ghqing on 17/1/3.
 */
public class Buttons {

    public void help(HBox hBox) {

        Button help = new javafx.scene.control.Button("帮助");
        hBox.getChildren().add(help);
        help.setOnAction(new HelpClickHandler());
    }

    private class HelpClickHandler extends Application implements EventHandler<ActionEvent> {

        private HelpClickHandler() {

        }

        @Override
        public void handle(ActionEvent event) {

            start(new Stage());
        }

        @Override
        public void start(Stage primaryStage) {

            Pane pane = new Pane();
            Label help = new Label("指令介绍：\n\n1.移动指令\n   移动指令由两个部分组成。\n   第一个部分是数字1-8，根据战斗力分别对应鼠（1)"
                    + "，猫（2），狼（3），狗（4），豹（5），虎（6），狮（7），象（8)\n   第二个部分是字母wasd中的一个，w对应上方向，a对应左方向，s对应下方向，d对应右方向\n"
                    + "   比如指令'1d'表示鼠向右走，'4w'表示狗向左走\n\n2.游戏指令\n   输入 restart 重新开始游戏\n   输入 help 查看帮助\n"
                    + "   输入 undo 悔棋\n   输入 redo 取消悔棋\n   输入 exit 退出游戏");
            pane.getChildren().add(help);
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public void exit(HBox hBox) {

        Button exit = new javafx.scene.control.Button("退出");
        hBox.getChildren().add(exit);
        exit.setOnAction(event -> System.exit(0));
    }

    public static void restart(Pane junglePane, HBox hBox, GameHistory gameHistory, Animal[][] animal, ImageView[][] animalImageView, boolean[] player) {

        Button restart = new javafx.scene.control.Button("重新开始");
        hBox.getChildren().add(restart);
        restart.setOnAction(event -> {
            try {
                FileRenew.restart(junglePane, gameHistory, animal, animalImageView, player);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void win(Animal[][] animal, String[][] tileArray, boolean player, int x1, int y1){

        if (Animal.checkWin(animal, tileArray, player, x1, y1)){
            new WinView(player).start(new Stage());
        }
    }

    public void cannotMove(Animal[][] animal, String[][] tileArray, boolean player, int x, int y){

        if (animal[y][x] != null) {
            if (animal[y][x].checkCannotMove(animal, tileArray, player)) {
                new WinView(!player).start(new Stage());
            }
        }
    }

    private class WinView extends Application{

        boolean player;

        private WinView(boolean player){

            this.player = player;
        }

        @Override
        public void start(Stage primaryStage){

            GridPane pane = new GridPane();
            pane.setPadding(new Insets(10, 10, 10, 10));
            pane.setAlignment(Pos.CENTER);
            Label win = new Label((player ? "左" : "右") + "方胜利！");
            win.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            pane.getChildren().add(win);
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public static void undo(Pane junglePane, HBox hBox, GameHistory gameHistory, Animal[][] animal, ImageView[][] animalImageView, boolean[] player){

        Button undo = new javafx.scene.control.Button("悔棋");
        hBox.getChildren().add(undo);
        undo.setOnAction(event -> {
            FileRenew.undo(junglePane, gameHistory, animal, animalImageView, player);
        });
    }
}
