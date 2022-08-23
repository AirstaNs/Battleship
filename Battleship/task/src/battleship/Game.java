package battleship;


import battleship.Field.GameField;

import static battleship.Field.DrawField.TextConst.LINE_BREAK;
import static battleship.Field.FieldSettings.ZERO_BLOCK_SHIP;

public class Game {
    private final String hitShip = "You hit a ship!";
    private final String missed = "You missed!";
    private final String WIN = "You sank the last ship. You won. Congratulations!";

    public void startGame() {

        Player playerOne = new Player();
        GameField playerOneField = playerOne.getPlayersGameField();

        playerOneField.getTextField().drawFieldToConsole();
        playerOneField.fillFieldShips();

        printStartGame();
        playerOneField.resetTextField();

        playerOneField.getTextField().drawFieldToConsole();
        boolean isShipBlock;
//        do {
//            printShot();
//            isShipBlock = playerOneField.setShot();
//
//            playerOneField.getResetField().drawFieldToConsole();
//            System.out.println(isShipBlock ? hitShip : missed);
//            System.out.println();
//        } while (isShipBlock);
        while (playerOneField.getNumberShipCells() != ZERO_BLOCK_SHIP) {
            isShipBlock = true;
            while (isShipBlock) {
                printShot();
                isShipBlock = playerOneField.setShot();

                playerOneField.getTextField().drawFieldToConsole();

                if (playerOneField.getNumberShipCells() == ZERO_BLOCK_SHIP) {
                    System.out.println(WIN);
                    isShipBlock = false;
                } else if (isShipBlock) {
                    System.out.println(hitShip + LINE_BREAK);
                } else {
                    System.out.println(missed + LINE_BREAK);
                }
            }
        }
    }

    private static void printStartGame() {
        System.out.println("The game starts!" + LINE_BREAK);
    }

    private static void printShot() {
        System.out.println("Take a shot!" + LINE_BREAK);
    }
}
