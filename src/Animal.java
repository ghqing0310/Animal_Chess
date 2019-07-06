import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;

/**
 * Created by ghqing on 16/12/18.
 */
public class Animal implements Cloneable {

    protected boolean camp;
    protected int strength;
    protected String path;
    protected String name;

    public Animal() {

    }

    protected Animal(boolean camp) {
        this.camp = camp;
    }

    public Rectangle[] createRectangle(Pane junglePane, ImageView[][] animalImageView, GameHistory gameHistory, Animal[][] animal,
                                       String[][] tileArray, boolean player, int x, int y) {

        Rectangle[] rectangle = new Rectangle[4];

        if (checkCanMove(tileArray, animal, x, y, x, y + 1)) {
            Rectangle r1 = CreateRectangle.createRectangle(140 + 80 * x, 120 + 80 * (y + 1));
            rectangle[0] = r1;
            junglePane.getChildren().add(r1);
            r1.setOnMouseClicked(new MoveClickHandler(junglePane, animalImageView, gameHistory, animal, tileArray,
                    rectangle, player, x, y, x, y + 1));
        }
        if (checkCanMove(tileArray, animal, x, y, x, y - 1)) {
            Rectangle r2 = CreateRectangle.createRectangle(140 + 80 * x, 120 + 80 * (y - 1));
            rectangle[1] = r2;
            junglePane.getChildren().add(r2);
            r2.setOnMouseClicked(new MoveClickHandler(junglePane, animalImageView, gameHistory, animal, tileArray,
                    rectangle, player, x, y, x, y - 1));
        }
        if (checkCanMove(tileArray, animal, x, y, x + 1, y)) {
            Rectangle r3 = CreateRectangle.createRectangle(140 + 80 * (x + 1), 120 + 80 * y);
            rectangle[2] = r3;
            junglePane.getChildren().add(r3);
            r3.setOnMouseClicked(new MoveClickHandler(junglePane, animalImageView, gameHistory, animal, tileArray,
                    rectangle, player, x, y, x + 1, y));
        }
        if (checkCanMove(tileArray, animal, x, y, x - 1, y)) {
            Rectangle r4 = CreateRectangle.createRectangle(140 + 80 * (x - 1), 120 + 80 * y);
            rectangle[3] = r4;
            junglePane.getChildren().add(r4);
            r4.setOnMouseClicked(new MoveClickHandler(junglePane, animalImageView, gameHistory, animal, tileArray,
                    rectangle, player, x, y, x - 1, y));
        }

        return rectangle;
    }

    public boolean checkCanMove(String[][] tileArray, Animal[][] animal, int x, int y, int xAfter, int yAfter) {

        if (!(Math.abs(xAfter - x) == 1 && yAfter == y || Math.abs(yAfter - y) == 1 && xAfter == x)) {
            return false;
        }  //检查是否按规则移动
        if (xAfter < 0 || xAfter > 8 || yAfter < 0 || yAfter > 6) {
            return false;
        }  //检查是否走出边界
        if (tileArray[yAfter][xAfter].equals("3") && animal[y][x].getCamp() ||
                tileArray[yAfter][xAfter].equals("5") && !animal[y][x].getCamp()) {
            return false;
        }  //检查是否进入己方兽穴
        if (tileArray[yAfter][xAfter].equals("1")) {
            return false;
        }  //检查是否能下水
        if (animal[yAfter][xAfter] != null) {

            if (animal[y][x].getCamp() == animal[yAfter][xAfter].getCamp()) {
                return false;
            }  //检查是否进入友方单位所在格子
            if (tileArray[yAfter][xAfter].equals(animal[yAfter][xAfter].getCamp() ? "4" : "2")) {
                return true;
            }  //检查敌方是否在己方陷阱
            if (animal[y][x].getStrength() < animal[yAfter][xAfter].getStrength()) {
                return false;
            }  //检查是否送死
        }

        return true;
    }

    @Override
    public Animal clone() throws CloneNotSupportedException {
        return (Animal) super.clone();
    }

    public static boolean checkWin(Animal[][] animal, String[][] tileArray, boolean player, int x1, int y1) {
        if (tileArray[y1][x1].equals(animal[y1][x1].getCamp() ? "5" : "3")) {
            return true;
        }  //是否进入敌方兽穴
        for (int i = 0; i < animal.length; i++) {
            for (int j = 0; j < animal[0].length; j++) {
                if (animal[i][j] != null) {
                    if (animal[i][j].getCamp() != player) {
                        return false;
                    }
                }
            }
        }  //敌方动物是否全被消灭
        return true;
    }

    public boolean checkCannotMove(Animal[][] animal, String[][] tileArray, boolean player) {

        int number = 0;

        for (int i = 0; i < animal.length; i++) {
            for (int j = 0; j < animal[0].length; j++) {
                if (animal[i][j] != null) {
                    if (animal[i][j].getCamp() == player) {
                        number++;
                    }
                }
            }
        }
        for (int i = 0; i < animal.length; i++) {
            for (int j = 0; j < animal[0].length; j++) {
                if (animal[i][j] != null) {
                    if (animal[i][j].getCamp() == player) {
                        if (!(checkCanMove(tileArray, animal, j, i, j + 1, i) || checkCanMove(tileArray, animal, j, i, j - 1, i)
                                || checkCanMove(tileArray, animal, j, i, j, i + 1) || checkCanMove(tileArray, animal, j, i, j, i - 1))) {
                            number--;
                        }
                    }
                }
            }
        }

        if (number != 0) {
            return false;
        } else {
            return true;
        }
        }  //检查是否无子可动

    public String getPath() {
        return path;
    }

    public int getStrength() {
        return strength;
    }

    public boolean getCamp() {
        return camp;
    }

    public String getName() {
        return name;
    }

    public class MoveClickHandler implements EventHandler<MouseEvent> {

        private Pane junglePane;
        private ImageView[][] animalImageView;
        private GameHistory gameHistory;
        private Animal[][] animal;
        private String[][] tileArray;
        private Rectangle[] rectangle;
        private boolean player;
        private int x;
        private int y;
        private int x1;
        private int y1;

        public MoveClickHandler(Pane junglePane, ImageView[][] animalImageView, GameHistory gameHistory, Animal[][] animal,
                                String[][] tileArray, Rectangle[] rectangle, boolean player, int x, int y, int x1, int y1) {

            this.junglePane = junglePane;
            this.animalImageView = animalImageView;
            this.gameHistory = gameHistory;
            this.animal = animal;
            this.tileArray = tileArray;
            this.rectangle = rectangle;
            this.player = player;
            this.x = x;
            this.y = y;
            this.x1 = x1;
            this.y1 = y1;
        }

        @Override
        public void handle(MouseEvent event) {

            junglePane = FileRenew.removeRectangle(junglePane, rectangle);
            try {
                animal[y1][x1] = animal[y][x].clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            animal[y][x] = null;
            gameHistory.addRecord(animal);
            if (animalImageView[y1][x1].getImage() != null) {
                junglePane.getChildren().remove(animalImageView[y1][x1]);
            }
            animalImageView = FileRenew.renewAnimalImageView(animal, animalImageView);
            junglePane = FileRenew.removeAnimalImageView(junglePane, animalImageView);
            junglePane = FileRenew.renewJunglePane(junglePane, animalImageView);
            new Buttons().win(animal, tileArray, player, x1, y1);
        }  //移除黄色小方块且移动动物
    }
}
