package battleship.Field;

import battleship.Field.DrawField.TextConst;
import battleship.Field.DrawField.TextField;
import battleship.Ships.CheckCoordinates;
import battleship.Ships.Position;
import battleship.Ships.Ship;
import battleship.Ships.settingsShip;

import java.util.Arrays;
import java.util.Scanner;

import static battleship.Field.DrawField.TextConst.LINE_BREAK;
import static battleship.Field.FieldSettings.*;


public class GameField {
    private final char[][] field;
    private final TextField textField;
    private int COUNT_SHIP = 5;
    private final String hitShip = "You hit a ship!";
    private final String missed = "You missed!";

    public GameField() {
        field = getFieldFilledFog();
        textField = new TextField(FieldSettings.SIZE_Y, field);
    }

    //Создает и заполняет массив туманом
    public static char[][] getFieldFilledFog() {
        char[][] gameField = new char[FieldSettings.SIZE_Y][FieldSettings.SIZE_X];
        // fill all str ~~~~~~~~~~~
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < FieldSettings.SIZE_X; j++) {
                gameField[i][j] = FieldSettings.FOG_BlOCK;
            }
        }
        return gameField;
    }

    public char[][] getField() {
        return field;
    }

    public TextField getTextField() {
        return textField;
    }

    // Заполняет поле кораблями при старте игры - 5 штук
    public void fillFieldShips() {
        Scanner scanner = new Scanner(System.in);
        for (int i = BEGIN; i < COUNT_SHIP; i++) {
            boolean freeField;
            // название кораблей и их размер
            settingsShip settingShip = settingsShip.values()[i];
            Ship ship = new Ship(settingShip.getSize(), settingShip.getNameShip());
            do {
                ship.printInitMessage();
                //Проверка поля и координат на правильность
                freeField = ship.fieldFreeForShip(this.getField(), scanner.nextLine());
            } while (freeField);
            this.setShipToField(ship.getStartPosition(), ship.getEndPosition());
            this.textField.drawFieldToConsole();
        }
    }

    // The ship is standing horizontally or vertically
    private void setShipToField(Position startPosition, Position endPosition) {
        if (endPosition.getY() == startPosition.getY()) {
            setShipFieldHorizontally(startPosition.getY(), startPosition.getX(), endPosition.getX());
        } else {
            setShipFieldVertically(startPosition.getX(), startPosition.getY(), endPosition.getY());
        }
    }

    // The ship is standing horizontally
    private void setShipFieldHorizontally(int Y, int start, int end) {

        for (int i = start; i <= end; i++) {
            field[Y][i] = FieldSettings.SHIP_BLOCK;
        }
        // Заполняет текстовое поле
        textField.drawShipHorizontally(Y, start, end);

    }

    // The ship is standing Vertically
    private void setShipFieldVertically(int X, int start, int end) {
        for (int i = start; i <= end; i++) {
            field[i][X] = FieldSettings.SHIP_BLOCK;
        }
        // Заполняет текстовое поле
        textField.drawShipVertically(X, start, end);
    }

    /**
     * Наносит удар по полю, проверяя введенную позицию на ошибки
     *
     * @return true - попал по кораблю и продолжать играть, Нет - false
     */
    public boolean setShot() {
        Scanner scanner = new Scanner(System.in);
        Position cell = null;
        boolean isErr = true;
        do {
            // Пока поле введено с ошибками продолжать
            try {
                CheckCoordinates checkCoordinates = new CheckCoordinates();
                cell = new Position(scanner.nextLine());
                isErr = (checkCoordinates.isOutOfBoundsCoordinates(cell.getX(), cell.getY()));
            } catch (NumberFormatException e) {
                System.out.println(ErrorNumberCoordinate + LINE_BREAK);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(Error_Location_Shot + LINE_BREAK);
            }
        } while (isErr);

        boolean isShip = isShipBlock(cell);
        char block = isShip ? BROKEN_BLOCK : MISS_BLOCK;
        field[cell.getY()][cell.getX()] = block;

        textField.drawShot(block, cell);
        textField.drawFieldToConsole();
        System.out.println(isShip ? hitShip : missed);
        System.out.println();
        return isShip;
    }

    /**
     * @param cell клетка на поле
     * @return Клетка на поле это часть корабля?
     */
    public boolean isShipBlock(Position cell) {
        return field[cell.getY()][cell.getX()] == SHIP_BLOCK;
    }
}
