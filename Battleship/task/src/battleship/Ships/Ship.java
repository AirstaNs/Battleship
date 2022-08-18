package battleship.Ships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract public class Ship {
    List<String> shipBlocks;
    public Ship(int size) {
        shipBlocks = new ArrayList<>(size);
        Collections.fill(shipBlocks,"O");
    }
}
