package battleship.Ships;

import battleship.FieldSizeAndValues;

import java.util.Arrays;

abstract public class Ship {
    private final char[] shipBlocks;

    public Ship(int size) {
        shipBlocks = new char[size];
        Arrays.fill(shipBlocks, FieldSizeAndValues.SHIP_BLOCK);
    }

    public char[] getShipBlocks() {
        return shipBlocks;
    }

    @Override
    public String toString() {
        return String.join("", Arrays.toString(shipBlocks));
    }
}
