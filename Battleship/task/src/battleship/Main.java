package battleship;

import battleship.Field.GameField;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
//        GameField gameFieldOnePlayer = new GameField();
//        System.out.println(gameFieldOnePlayer);
        Game game = new Game();
        game.startGame();
    }
}
