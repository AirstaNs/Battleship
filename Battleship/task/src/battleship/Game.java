package battleship;


import java.util.List;
import java.util.stream.IntStream;

import static battleship.Field.DrawField.TextConst.LINE_BREAK;
import static battleship.Field.FieldSettings.BEGIN;
import static battleship.Field.FieldSettings.BEGIN_NUMERIC;

public class Game {
    private static final int ONE_PLAYER = 0;
    private static final int TWO_PLAYER = 1;
    private static final String delimiterField = "---------------------";

    public void startGame() {
        List<Player> players = List.of(new Player(), new Player());

        IntStream.range(BEGIN, players.size()).forEach(i -> {
            printPlaceShip(i);
            players.get(i).getPlayersGameField().drawFieldToConsole();
            players.get(i).fillFieldShips();
        });



        for (int i = 0; players.get(ONE_PLAYER).isWin() | players.get(TWO_PLAYER).isWin(); i++) {
             passMove();

            int enemy;
            int setter;
            if (i % 2 == 0) {
                enemy = TWO_PLAYER;
                setter = ONE_PLAYER;
            } else {
                enemy = ONE_PLAYER;
                setter = TWO_PLAYER;
            }
            players.get(enemy).getFieldAnotherPlayer().drawFieldToConsole();
            System.out.println(delimiterField);

            players.get(setter).getPlayersGameField().drawFieldToConsole();

            printShot(setter);
            players.get(enemy).setShot();
        }
        printWin();
    }


    private static void printStartGame() {
        System.out.println("The game starts!" + LINE_BREAK);
    }

    private static void printPlaceShip(int numberPlayer) {
        System.out.printf("Player %d, place your ships to the game field%n%n", numberPlayer + BEGIN_NUMERIC);
    }

    private static void printShot(int numberPlayer) {
        System.out.printf("Player %d, it's your turn:%n%n", numberPlayer + BEGIN_NUMERIC);
    }

    private static void passMove() {
        System.out.printf("Press Enter and pass the move to another player\n");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
    private static void printWin(){
        System.out.println("You sank the last ship. You won. Congratulations!");
    }
}
