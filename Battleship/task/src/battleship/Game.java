package battleship;



import battleship.Field.GameField;

import java.util.Scanner;

public class Game {
    public void startGame(){
        Scanner scanner = new Scanner(System.in);

        Player playerOne = new Player();
        GameField playerOneField =  playerOne.getPlayersGameField();

        playerOneField.getTextField().drawFieldToConsole();
        playerOneField.fillFieldShips(scanner);

        this.startGame();

    }
    private static void printStartGame(){
        System.out.println("The game starts!");
    }
}
