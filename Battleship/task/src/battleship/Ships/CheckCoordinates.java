package battleship.Ships;


import java.util.List;

import static battleship.Field.FieldSettings.*;

public class CheckCoordinates {

    public boolean CheckErrorSetShip(int sizeShip, List<StringBuilder> gameField, Position start, Position end)
            throws IndexOutOfBoundsException, IllegalArgumentException {

        isOutOfBoundsCoordinates(start.getX(), start.getY());

        isOutOfBoundsCoordinates(end.getX(), end.getY());

        isBiggerShipSize(sizeShip, start.getX(), start.getY(), end.getX(), end.getY());

        isPlaceDiagonally(start.getX(), start.getY(), end.getX(), end.getY());

        isPlacedTooClose(gameField, start.getX(), start.getY(), end.getX(), end.getY());
        return false;
    }

    // The ship should not stand next to the ship
    public boolean isPlacedTooClose(List<StringBuilder> gameField, int X_start, int Y_start, int X_end, int Y_end) {
        //The field around the ship
        int y = Y_start <= BEGIN ? BEGIN : Y_start - BEGIN_NUMERIC;
        int x = X_start <= BEGIN ? BEGIN : ((X_start - BEGIN_NUMERIC) * skipSpace);
        int y_end = Y_end >= SIZE_Y - BEGIN_NUMERIC ? Y_end : Y_end + BEGIN_NUMERIC;
        int x_end = X_end >= SIZE_X - BEGIN_NUMERIC ? X_end : X_end + BEGIN_NUMERIC;
        x_end = (x_end * skipSpace);

        for (int i = y; i <= y_end; i++) {
            for (int j = x; j <= x_end; j += skipSpace) {
                if (gameField.get(i).charAt(j) == SHIP_BLOCK) {
                    throw new IllegalArgumentException();
                }
            }
        }
        return false;
    }

    // if coordinate out of bounds -> Z or -1
    public boolean isOutOfBoundsCoordinates(int X, int Y) {

        boolean isOutOfBoundsStart = (Y < BEGIN) | (X < BEGIN);
        boolean isOutOfBoundsEnd = (Y >= SIZE_Y) | (X >= SIZE_X);

        if (isOutOfBoundsStart | isOutOfBoundsEnd) {
            throw new IndexOutOfBoundsException();
        }
        return false;
    }


    //if length excess size ship return true
    public boolean isBiggerShipSize(int sizeShip, int X_start, int Y_start, int X_end, int Y_end) {
        int one = 1;
        int length_Y = Y_end - Y_start + one;
        int length_X = X_end - X_start + one;

        if (length_Y != sizeShip & length_X != sizeShip) {
            throw new IndexOutOfBoundsException();
        }
        return false;
    }

    // A1 B2
    public boolean isPlaceDiagonally(int X_start, int Y_start, int X_end, int Y_end) {
        if (X_start != X_end & Y_start != Y_end) {
            throw new NumberFormatException();
        }
        return false;
    }
}
