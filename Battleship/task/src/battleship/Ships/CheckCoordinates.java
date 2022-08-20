package battleship.Ships;

import battleship.Field.DrawField.TextConst;

import java.util.ArrayList;
import java.util.List;

import static battleship.Field.FieldSettings.*;

public class CheckCoordinates {
    private final int sizeShip;
    private static final int NUMBER_OF_COORDINATES = 2;
    private final String ErrorLength=String.format("Error! Wrong length of the %s! Try again:", nameShip);
    private final String ErrorPlace = "Error! You placed it too close to another one.";
    private final String ErrorLocation = "Error! Wrong ship location! Try again:";

    public CheckCoordinates(int sizeShip) {
        this.sizeShip = sizeShip;
    }

    public List<List<Integer>> allChecks(String position) {

        List<List<Integer>> fullCoordinates = convertTwoPositionOnList(position);
        // 0 - x ; 1 - y
        List<Integer> startPosition = fullCoordinates.get(INDEX_START_COORDINATE);
        List<Integer>  endPosition = fullCoordinates.get(INDEX_END_SHIP_COORDINATE);

        int X_start = startPosition.get(INDEX_X);
        int Y_start = startPosition.get(INDEX_Y);

        int X_end = endPosition.get(INDEX_X);
        int Y_end = endPosition.get(INDEX_Y);

        if (isOutOfBounds(X_start, Y_start, X_end, Y_end)) {
            throw  new NumberFormatException();
        }
        if (isBiggerShipSize(X_start, Y_start, X_end, Y_end)) {
            throw  new IndexOutOfBoundsException();
        }
        if (isPlaceDiagonally(X_start, Y_start, X_end, Y_end)) {
            throw  new NumberFormatException();
        }

        return !isError;
    }

    // return  {X_start, Y_start}, {X_end, Y_end}
    private List<List<Integer>> convertTwoPositionOnList(String position) throws RuntimeException {
        /* index 0 = startShip  /\ index 1 = endShip
            A5 B2 ->  [A5, B2]
         */
        List<String> listCoordinate = List.of(position.trim().split("\\s+"));

        if (listCoordinate.size() > NUMBER_OF_COORDINATES) {
            throw new IndexOutOfBoundsException();
        }
        List<Integer> startPosition = getListPosition(listCoordinate.get(INDEX_START_COORDINATE));
        List<Integer> endPosition = getListPosition(listCoordinate.get(INDEX_END_SHIP_COORDINATE));

        for (int i = 0; i < startPosition.size(); i++) {
            // 5 4 -> 4 5
            int start = startPosition.get(i);
            int end = endPosition.get(i);
            if (isMixedCoordinates(start, end)) {
                startPosition.set(i, end);
                endPosition.set(i, start);
            }
        }
        return List.of(startPosition, endPosition);
    }
    // if coordinate written reverse - > A10 A9 -> return true
    private boolean isMixedCoordinates(int start, int end) {
        return (end - start < 0);
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


    // J5 -> [9, 5]
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
