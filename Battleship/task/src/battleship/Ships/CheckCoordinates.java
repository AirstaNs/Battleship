package battleship.Ships;


import static battleship.Field.FieldSettings.*;

public class CheckCoordinates {
    private final int sizeShip;

    public CheckCoordinates(int sizeShip) {
        this.sizeShip = sizeShip;
    }

    public void allCheckError(char[][] field, Position start, Position end) {

        if (isOutOfBounds(start.getX(), start.getY(), end.getX(), end.getY())) {
            throw new NumberFormatException();
        }
        if (isBiggerShipSize(start.getX(), start.getY(), end.getX(), end.getY())) {
            throw new IndexOutOfBoundsException();
        }
        if (isPlaceDiagonally(start.getX(), start.getY(), end.getX(), end.getY())) {
            throw new NumberFormatException();
        }
        if (isPlacedTooClose(field, start.getX(), start.getY(), end.getX(), end.getY())) {
            throw new IllegalArgumentException();
        }
    }

    // The ship should not stand next to the ship
    private boolean isPlacedTooClose(char[][] field, int X_start, int Y_start, int X_end, int Y_end) {
        //The field around the ship
        int y = Y_start - 1;
        int x = X_start - 1;
        int y_end = Y_end+1;
        int x_end = X_end+1;
        boolean isPlaced = true;

        for (int i = y; i < y_end; i++) {
            for (int j = x; j < x_end; j++) {
                try {
                    if (field[i][j] == SHIP_BLOCK) {
                        return isPlaced;
                    }
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        return !isPlaced;
    }

    // if coordinate out of bounds -> Z or -1
    private boolean isOutOfBounds(int X_start, int Y_start, int X_end, int Y_end) {

        boolean isOutOfBoundsStart = (Y_start < BEGIN) & (X_start < BEGIN);
        boolean isOutOfBoundsEnd = (Y_end >= SIZE_Y) & (X_end >= SIZE_Y);
        return isOutOfBoundsStart & isOutOfBoundsEnd;
    }

    //if length excess size ship return true
    private boolean isBiggerShipSize(int X_start, int Y_start, int X_end, int Y_end) {

        boolean isBiggerSize_Y = Y_end - Y_start != sizeShip;
        boolean isBiggerSize_X = X_end - X_start != sizeShip;
        return isBiggerSize_Y | isBiggerSize_X;
    }

    // A1 B2
    private boolean isPlaceDiagonally(int X_start, int Y_start, int X_end, int Y_end) {
        return X_start != X_end & Y_start != Y_end;
    }
}
