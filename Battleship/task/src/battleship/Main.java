package battleship;

import battleship.Ships.AircraftCarrier;
import battleship.Ships.Ship;

public class Main {
    public static void main(String[] args) {

        PlayingField fieldOnePlayer = new PlayingField();
        System.out.println(fieldOnePlayer);
        Ship ship = new AircraftCarrier();
        System.out.println(ship);
//        PlayingField fieldTwoPlayer = new PlayingField();
//        System.out.println(fieldTwoPlayer);

    }

}
