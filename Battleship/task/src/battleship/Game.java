package battleship;


import battleship.Field.GameField;

import java.util.Scanner;

import static battleship.Field.DrawField.TextConst.LINE_BREAK;

public class Game {
    public void startGame() {

        Player playerOne = new Player();
        GameField playerOneField = playerOne.getPlayersGameField();

        playerOneField.getTextField().drawFieldToConsole();
        playerOneField.fillFieldShips();

        printStartGame();
        playerOneField.getTextField().drawFieldToConsole();
        boolean isShipBlock;
        do {
            printShot();
            isShipBlock = playerOneField.setShot();
        } while (isShipBlock);
    }

    private static void printStartGame() {
        System.out.println("The game starts!" + LINE_BREAK);
    }

    private static void printShot() {
        System.out.println("Take a shot!" + LINE_BREAK);
    }
}
