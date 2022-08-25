package battleship;


import battleship.Field.GameField;
import battleship.Ships.CheckCoordinates;
import battleship.Ships.Position;
import battleship.Ships.Ship;
import battleship.Ships.settingsShip;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static battleship.Field.TextConst.LINE_BREAK;
import static battleship.Field.FieldSettings.*;
import static battleship.Field.FieldSettings.COUNT_SHIP;

public class Player {
    private final GameField playersGameField;
    private final GameField fieldAnotherPlayer;
    private final String hitShip = "You hit a ship!";
    private final String missed = "You missed!";
    private final List<Ship> ships;
    private final String sunkShip = "You sank a ship! Specify a new target:\n\n";

    public Player() {
        playersGameField = new GameField();
        fieldAnotherPlayer = new GameField();
        ships = new ArrayList<>(COUNT_SHIP);
        ;
    }

    public GameField getPlayersGameField() {
        return playersGameField;
    }

    public GameField getFieldAnotherPlayer() {
        return fieldAnotherPlayer;
    }

    public boolean isWin() {
        return !(ships.size() == ZERO_BLOCK_SHIP);
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

    /*  Если сломанный блок, то пишем "вы попали в корабль" тк это раньше был корабль //TODO такие правила JetBrains
        Устанавливает блок в зависимости какой установлен на поле уже
        TODO COMMAND ПАТТЕРН ИСПОЛЬЗОВАТЬ
     */
    public void setBlock(Position cell) {
        // получаем GameFiled для противника и для себя
        int Y = cell.getY();
        int X = cell.getX() * skipSpace;
        StringBuilder positionField = playersGameField.getField().get(Y);
        StringBuilder positionAnotherField = fieldAnotherPlayer.getField().get(Y);

        char block = positionField.charAt(X);
        //В зависимости от блока печатаем фразу и меняем символ
        switch (block) {
            case FOG_BlOCK:
                positionField.setCharAt(X, MISS_BLOCK);
                positionAnotherField.setCharAt(X, MISS_BLOCK);
                System.out.println(missed + LINE_BREAK);
                break;
            case SHIP_BLOCK:
                //Если корабль уничтожен печатаем фразу
                if (DecreaseShipLength(cell)) {
                    positionField.setCharAt(X, BROKEN_BLOCK);
                    positionAnotherField.setCharAt(X, BROKEN_BLOCK);
                    System.out.println(sunkShip);
                    break;
                }
            default:
                positionField.setCharAt(X, BROKEN_BLOCK);
                positionAnotherField.setCharAt(X, BROKEN_BLOCK);
                System.out.println(hitShip + LINE_BREAK);
                break;
        }
    }

    private boolean DecreaseShipLength(Position shot) {
        boolean decrease = true;

        for (int i = 0; i < ships.size(); i++) {
            Ship ship = ships.get(i);
            var startPosition = ship.getStartPosition();
            var endPosition = ship.getEndPosition();
            if (startPosition.compareTo(shot) <= 0 | endPosition.compareTo(shot) >= 0) {
                ship.HitShip();
                if (ship.getLength() == ZERO_BLOCK_SHIP) {
                    ships.remove(ship);
                    return decrease;
                }
                break;
            }
        }
        return !decrease;
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
            ships.add(ship);
            playersGameField.drawShipToField(ship.getStartPosition(), ship.getEndPosition());
            playersGameField.drawFieldToConsole();

        }
    }
}
