package battleship.Ships;

public enum SizeShip {
    AircraftCarrier(5),
    Battleship(4),
    Submarine(3),
    Cruiser(3),
    Destroyer(2);
    private final int size;

    SizeShip(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
