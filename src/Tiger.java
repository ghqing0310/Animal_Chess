import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Created by ghqing on 16/12/26.
 */
public class Tiger extends Animal implements JumpWater{

    public Tiger(boolean camp){

        super(camp);
        this.strength = 6;
        this.path = "File:animals/6Tiger" + (camp ? "Left" : "Right") + ".png";
        this.name = "虎";

    }

    @Override
    public Rectangle[] createRectangle(Pane junglePane, ImageView[][] animalImageView, GameHistory gameHistory, Animal[][] animal,
                                       String[][] tileArray, boolean player, int x, int y){

        Rectangle[] rectangle = new Rectangle[8];

        if (checkCanMove(tileArray, animal, x, y, x, y + 1)){
            Rectangle r1 = CreateRectangle.createRectangle(140 + 80 * x, 120 + 80 * (y + 1));
            rectangle[0] = r1;
            junglePane.getChildren().add(r1);
            r1.setOnMouseClicked(new MoveClickHandler(junglePane, animalImageView, gameHistory, animal, tileArray,
                    rectangle, player, x, y, x, y + 1));
        }
        if (checkCanMove(tileArray, animal, x, y, x, y - 1)){
            Rectangle r2 = CreateRectangle.createRectangle(140 + 80 * x, 120 + 80 * (y - 1));
            rectangle[1] = r2;
            junglePane.getChildren().add(r2);
            r2.setOnMouseClicked(new MoveClickHandler(junglePane, animalImageView, gameHistory, animal, tileArray,
                    rectangle, player, x, y, x, y - 1));
        }
        if (checkCanMove(tileArray, animal, x, y, x + 1, y)){
            Rectangle r3 = CreateRectangle.createRectangle(140 + 80 * (x + 1), 120 + 80 * y);
            rectangle[2] = r3;
            junglePane.getChildren().add(r3);
            r3.setOnMouseClicked(new MoveClickHandler(junglePane, animalImageView, gameHistory, animal, tileArray,
                    rectangle, player, x, y, x + 1, y));
        }
        if (checkCanMove(tileArray, animal, x, y, x - 1, y)){
            Rectangle r4 = CreateRectangle.createRectangle(140 + 80 * (x - 1), 120 + 80 * y);
            rectangle[3] = r4;
            junglePane.getChildren().add(r4);
            r4.setOnMouseClicked(new MoveClickHandler(junglePane, animalImageView, gameHistory, animal, tileArray,
                    rectangle, player, x, y, x - 1, y));
        }
        if (checkCanMove(tileArray, animal, x, y, x, y + 3)){
            Rectangle r5 = CreateRectangle.createRectangle(140 + 80 * x, 120 + 80 * (y + 3));
            rectangle[4] = r5;
            junglePane.getChildren().add(r5);
            r5.setOnMouseClicked(new MoveClickHandler(junglePane, animalImageView, gameHistory, animal, tileArray,
                    rectangle, player, x, y, x, y + 3));
        }
        if (checkCanMove(tileArray, animal, x, y, x, y - 3)){
            Rectangle r6 = CreateRectangle.createRectangle(140 + 80 * x, 120 + 80 * (y - 3));
            rectangle[5] = r6;
            junglePane.getChildren().add(r6);
            r6.setOnMouseClicked(new MoveClickHandler(junglePane, animalImageView, gameHistory, animal, tileArray,
                    rectangle, player, x, y, x, y - 3));
        }
        if (checkCanMove(tileArray, animal, x, y, x + 4, y)){
            Rectangle r7 = CreateRectangle.createRectangle(140 + 80 * (x + 4), 120 + 80 * y);
            rectangle[6] = r7;
            junglePane.getChildren().add(r7);
            r7.setOnMouseClicked(new MoveClickHandler(junglePane, animalImageView, gameHistory, animal, tileArray,
                    rectangle, player, x, y, x + 4, y));
        }
        if (checkCanMove(tileArray, animal, x, y, x - 4, y)){
            Rectangle r8 = CreateRectangle.createRectangle(140 + 80 * (x - 4), 120 + 80 * y);
            rectangle[7] = r8;
            junglePane.getChildren().add(r8);
            r8.setOnMouseClicked(new MoveClickHandler(junglePane, animalImageView, gameHistory, animal, tileArray,
                    rectangle, player, x, y, x - 4, y));
        }

        return rectangle;
    }

    @Override
    public boolean checkCanMove(String[][] tileArray, Animal[][] animal, int x, int y, int xAfter, int yAfter) {

        if (!(Math.abs(xAfter - x) == 1 && yAfter == y || Math.abs(xAfter - x) == 4 && yAfter == y ||
                Math.abs(yAfter - y) == 1 && xAfter == x || Math.abs(yAfter - y) == 3 && xAfter == x)){
            return false;
        }
        if (xAfter < 0 || xAfter > 8 || yAfter < 0 || yAfter > 6){
            return false;
        }
        if (Math.abs(xAfter - x) == 4 && yAfter == y || Math.abs(yAfter - y) == 3 && xAfter == x){
            if (checkJumpWater(tileArray, x, y, xAfter, yAfter)){
                if (checkMouse(animal, x, y, xAfter, yAfter)){
                    return true;
                }
            }else {
                return false;
            }
        }  //检查能否跳河
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
            if (animal[y][x].getStrength() < animal[yAfter][xAfter].getStrength()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean checkJumpWater(String[][] tileArray, int x, int y, int xAfter, int yAfter){

        if (xAfter >= 0 && xAfter <= 8 && yAfter >= 0 && yAfter <= 6) {
            if (xAfter == x + 4) {
                if ((tileArray[y][x + 1].equals("1"))) {
                    return true;
                }
            }
            if (xAfter == x - 4) {
                if ((tileArray[y][x - 1].equals("1"))) {
                    return true;
                }
            }
            if (yAfter == y + 3) {
                if ((tileArray[y + 1][x].equals("1"))) {
                    return true;
                }
            }
            if (yAfter == y - 3) {
                if ((tileArray[y - 1][x].equals("1"))) {
                    return true;
                }
            }
        }

        return false;
    }  //检查是否想跳河

    @Override
    public boolean checkMouse(Animal[][] animal, int x, int y, int xAfter, int yAfter){

        int i;

        if (xAfter == x + 4){
            for (i = x + 1; i < xAfter; i++){
                if (animal[yAfter][i] != null) {
                    if (animal[yAfter][i].getCamp() != animal[y][x].getCamp() && animal[yAfter][i].getStrength() == 1) {
                        return true;
                    }
                }
            }
        }
        if (xAfter == x - 4){
            for (i = xAfter + 1; i < x; i++){
                if (animal[yAfter][i] != null) {
                    if (animal[yAfter][i].getCamp() != animal[y][x].getCamp() && animal[yAfter][i].getStrength() == 1) {
                        return true;
                    }
                }
            }
        }
        if (yAfter == y + 3){
            for (i = y + 1; i < yAfter; i++){
                if (animal[i][xAfter] != null){
                    if (animal[i][xAfter].getCamp() != animal[y][x].getCamp() && animal[i][xAfter].getStrength() == 1){
                        return true;
                    }
                }
            }
        }
        if (yAfter == y - 3){
            for (i = yAfter + 1; i < y; i++){
                if (animal[i][xAfter] != null){
                    if (animal[i][xAfter].getCamp() != animal[y][x].getCamp() && animal[i][xAfter].getStrength() == 1){
                        return true;
                    }
                }
            }
        }
        return true;
    }  //检查敌方鼠是否在跳河路线中

    @Override
    public Tiger clone() throws CloneNotSupportedException {
        return (Tiger) super.clone();
    }

    @Override
    public boolean checkCannotMove(Animal[][] animal, String[][] tileArray, boolean player){

        int number = 0;

        for (int i = 0; i < animal.length; i++){
            for (int j = 0; j < animal[0].length; j++){
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
                                || checkCanMove(tileArray, animal, j, i, j, i + 1) || checkCanMove(tileArray, animal, j, i, j, i - 1)
                                || checkCanMove(tileArray, animal, j, i, j + 3, i) || checkCanMove(tileArray, animal, j, i, j - 3, i)
                                || checkCanMove(tileArray, animal, j, i, j, i + 4) || checkCanMove(tileArray, animal, j, i, j, i - 4))) {
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
    }
}
