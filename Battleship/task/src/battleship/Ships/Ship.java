package battleship.Ships;

import battleship.Field.FieldSettings;

import java.util.Arrays;

abstract public class Ship {
    private final char[] shipBlocks;
    private char[] coordinates;
    public Ship(int size) {
        shipBlocks = new char[size];
        Arrays.fill(shipBlocks, FieldSettings.SHIP_BLOCK);
    }

    public char[] getShipBlocks() {
        return shipBlocks;
    }

    @Override
    public String toString() {
        return String.join("", Arrays.toString(shipBlocks));
    }
}
