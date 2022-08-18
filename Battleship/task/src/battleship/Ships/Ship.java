package battleship.Ships;

import java.util.Arrays;

abstract public class Ship {
    private final String[] shipBlocks;
    public Ship(int size) {
        shipBlocks = new String[size];
        Arrays.fill(shipBlocks, "O");
    }
    public String[] getShipBlocks() {
        return shipBlocks;
    }

    @Override
    public String toString() {
        return String.join(" ",shipBlocks);
    }
}
