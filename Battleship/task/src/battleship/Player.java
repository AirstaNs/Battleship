package battleship;

import battleship.Field.GameField;
import battleship.Ships.CheckCoordinates;
import battleship.Ships.Position;
import battleship.Ships.Ship;
import battleship.Ships.settingsShip;

import java.util.Scanner;

import static battleship.Field.DrawField.TextConst.LINE_BREAK;
import static battleship.Field.FieldSettings.*;
import static battleship.Field.FieldSettings.COUNT_SHIP;

public class Player {
    private final GameField playersGameField;
    private GameField fieldAnotherPlayer;
    private static int numberShipCells = 17;
    private final String hitShip = "You hit a ship!";
    private final String missed = "You missed!";

    public Player() {
        playersGameField = new GameField();
        fieldAnotherPlayer = new GameField();
    }

    public GameField getPlayersGameField() {
        return playersGameField;
    }

    public GameField getFieldAnotherPlayer() {
        return fieldAnotherPlayer;
    }

    public boolean isWin() {
        return !(numberShipCells <= ZERO_BLOCK_SHIP);
    }


    /**
     * Наносит удар по полю, проверяя введенную позицию на ошибки
     *
     * @return true - попал по кораблю и продолжать играть, Нет - false
     */
    public void setShot() {
        Scanner scanner = new Scanner(System.in);
        Position cell = null;
        boolean isErr = true;
        do {
            // Пока поле введено с ошибками - продолжать запрашивать координаты
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

        this.setBlock(cell);

        // TODO   textField.drawShot(block, cell);

    }

    /*  Если сломанный блок, то просто возвращаем true тк это раньше был корабль //TODO такие правила JetBrains
        Устанавливает блок в зависимости какой установлен на поле уже
     */
    public void setBlock(Position cell) {
        StringBuilder positionField = playersGameField.getField().get(cell.getY());
        StringBuilder positionAnotherField = fieldAnotherPlayer.getField().get(cell.getY());

        int column = cell.getX() * skipSpace;
        char block = positionField.charAt(column);

        switch (block) {
            case FOG_BlOCK:
                positionField.setCharAt(column, MISS_BLOCK);
                positionAnotherField.setCharAt(column, MISS_BLOCK);
                System.out.println(missed + LINE_BREAK);
                break;
            case SHIP_BLOCK:
                numberShipCells--;
            default:
                positionField.setCharAt(column, BROKEN_BLOCK);
                positionAnotherField.setCharAt(column, BROKEN_BLOCK);
                System.out.println(hitShip + LINE_BREAK);
                break;
        }
    }

    // Заполняет поле кораблями при старте игры - 5 штук
    public void fillFieldShips() {
        Scanner scanner = new Scanner(System.in);
        for (int i = BEGIN; i < COUNT_SHIP; i++) {
            boolean freeField;
            // название кораблей и их размер
            settingsShip settingShip = settingsShip.values()[i];
            Ship ship = new Ship(settingShip.getSize(), settingShip.getNameShip());
            ship.printInitMessage();
            do {
                //Проверка поля и координат на правильность
                freeField = ship.fieldFreeForShip(this.getPlayersGameField().getField(), scanner.nextLine());
            } while (freeField);
            playersGameField.drawShipToField(ship.getStartPosition(), ship.getEndPosition());
            playersGameField.drawFieldToConsole();
        }
    }
}
