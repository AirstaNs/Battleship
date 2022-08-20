package battleship.Ships;

import battleship.Field.DrawField.TextConst;

import java.util.ArrayList;
import java.util.List;

import static battleship.Field.FieldSettings.*;

public class CheckCoordinates {
    private final int sizeShip;
    private static final int NUMBER_OF_COORDINATES = 2;

    public CheckCoordinates(int sizeShip) {
        this.sizeShip = sizeShip;
    }

    public List<List<Integer>> allChecks(char[][] field, String position) {
        /* index 0 = startShip  /\ index 1 = endShip
            A5 B2 ->  [A5, B2]
         */
        List<String> stringCoordinate = List.of(position.trim().split("\\s+"));

        if (stringCoordinate.size() > NUMBER_OF_COORDINATES) {
            throw new IndexOutOfBoundsException();
        }

        // 0 - x ; 1 - y
        List<Integer> startPosition = getListPosition(stringCoordinate.get(INDEX_START_COORDINATE));
        List<Integer> endPosition = getListPosition(stringCoordinate.get(INDEX_END_SHIP_COORDINATE));

        int X_start = startPosition.get(INDEX_X);
        int Y_start = startPosition.get(INDEX_Y);

        int X_end = endPosition.get(INDEX_X);
        int Y_end = endPosition.get(INDEX_Y);

        if (isMixedCoordinates(X_start + Y_start, X_end + Y_end)) {
            int temp_X_start = X_start;
            int temp_Y_start = Y_start;

            X_start = X_end;
            Y_start = Y_end;
            X_end = temp_X_start;
            Y_end = temp_Y_start;
        }

        if (isOutOfBounds(X_start, Y_start, X_end, Y_end)) {
            throw new NumberFormatException();
        }
        if (isBiggerShipSize(X_start, Y_start, X_end, Y_end)) {
            throw new IndexOutOfBoundsException();
        }
        if (isPlaceDiagonally(X_start, Y_start, X_end, Y_end)) {
            throw new NumberFormatException();
        }
        if (isPlacedTooClose(field, X_start, Y_start, X_end, Y_end)) {
            throw new IllegalArgumentException();
        }

        return List.of(List.of(X_start, Y_start), List.of(X_end, X_end));
    }

    private boolean isPlacedTooClose(char[][] field, int X_start, int Y_start, int X_end, int Y_end) {
        X_start -= 1;
        Y_start -= 1;
        Y_end += 1;
        X_end += 1;
        boolean isPlaced = true;
        for (int i = Y_start; i < Y_end; i++) {
            for (int j = X_start; j < X_end; j++) {
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

    // if coordinate written reverse - > A10 A9 -> return true
    private boolean isMixedCoordinates(int sumStart, int sumEnd) {
        return (sumEnd - sumStart < 0);
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


    // J5 -> [9, 5]       return  {X_start, Y_start}, {X_end, Y_end}
    private ArrayList<Integer> getListPosition(String coordinate) {
        int X = getNUmberOf_X(coordinate);
        int Y = getNumberOf_Y(coordinate);
        return new ArrayList<>(List.of(X, Y));
    }

    // B5 -> get B to int ->  A-J % 65
    private int getNumberOf_Y(String y_coordinates) {
        int Y_index = 0;
        char char_Y_start = y_coordinates.charAt(Y_index);
        return char_Y_start % TextConst.A_char.toChar(); // A-J % 65 get int
    }

    // A10 -> get 10
    private int getNUmberOf_X(String x_coordinates) throws NumberFormatException {
        int X_index = 1;
        String str_X_end = x_coordinates.substring(X_index);
        return Integer.parseInt(str_X_end);
    }
}
