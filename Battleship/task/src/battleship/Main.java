package battleship;


import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        GameField gameFieldOnePlayer = new GameField();
//        System.out.println(gameFieldOnePlayer);
//        Game game = new Game();
//        game.startGame();
//        Ship ship = new AircraftCarrier();
    }

    private static void extracted(String position) {
        String[] pos = position.trim().split("\\s+");
        String oneCoordinates = pos[0];
        String twoCoordinates = pos[1];
        Arrays.sort(pos);
        System.out.println(Arrays.toString(pos));
    }
}
