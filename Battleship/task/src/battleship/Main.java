package battleship;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();

    }

    private static void extracted(String position) {
        String[] pos = position.trim().split("\\s+");
        String oneCoordinates = pos[0];
        String twoCoordinates = pos[1];
        Arrays.sort(pos);
        System.out.println(Arrays.toString(pos));
    }
}
