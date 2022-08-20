package battleship.Ships;

public enum settingsShip {
    AircraftCarrier(5, "AircraftCarrier"),
    Battleship(4,"Battleship"),
    Submarine(3,"Submarine"),
    Cruiser(3,"Cruiser"),
    Destroyer(2,"Destroyer");
    private final int size;
    private final String nameShip;
    settingsShip(int size, String nameShip) {
        this.size = size;
        this.nameShip = nameShip;
    }

    public int getSize() {
        return size;
    }
    public String getNameShip(){
        return nameShip;
    }
}
