package battleship.Ships;

import battleship.Field.DrawField.TextConst;

import java.util.ArrayList;
import java.util.List;

import static battleship.Field.FieldSettings.*;

public class CheckCoordinates {
    private final int sizeShip;
    private static final int NUMBER_OF_COORDINATES = 2;
    private final String ErrorLength;
    private final String ErrorPlace = "Error! You placed it too close to another one.";
    private final String ErrorLocation = "Error! Wrong ship location! Try again:";
    // 0 - x ; 1 - y
    private static final int indexStartCoordinate = 0;
    private static final int indexEndShipCoordinate = 1;

    public CheckCoordinates(String nameShip, int sizeShip) {
        ErrorLength = String.format("Error! Wrong length of the %s! Try again:", nameShip);
        this.sizeShip = sizeShip;
    }

    public boolean setShip(char[][] field, String position) {
        boolean isError = true;
        int index_X = 0;
        int index_Y = 1;

        List<Integer> startPosition;
        List<Integer> endPosition;
        try {                                                       // 0 - x ; 1 - y
            startPosition = convertTwoPositionOnList(position).get(indexStartCoordinate);
            endPosition = convertTwoPositionOnList(position).get(indexEndShipCoordinate);

        } catch (NumberFormatException e) {
            System.out.println(ErrorLocation);
            return isError;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ErrorLength);
            return isError;
        }

        int X_start = startPosition.get(index_X);
        int Y_start = startPosition.get(index_Y);

        int X_end = endPosition.get(index_X);
        int Y_end = endPosition.get(index_Y);


        if (isOutOfBounds(X_start, Y_start, X_end, Y_end)) {
            System.out.println(ErrorLocation);
            return isError;
        }
        if (isBiggerShipSize(X_start, Y_start, X_end, Y_end)) {
            System.out.println(ErrorLength);
            return isError;
        }
        if (isPlaceDiagonally(X_start, Y_start, X_end, Y_end)) {
            System.out.println(ErrorLocation);
            return isError;
        }

        return !isError;
    }

    // return  {X_start, Y_start}, {X_end, Y_end}
    private List<List<Integer>> convertTwoPositionOnList(String position) throws RuntimeException {


        /*
         index 0 = startShip  /\ index 1 = endShip
         A5 B2
         [A5, B2]
         */
        List<String> listCoordinate = new ArrayList<>(List.of(position.trim().split("\\s+")));

        if (listCoordinate.size() > NUMBER_OF_COORDINATES) {
            throw new IndexOutOfBoundsException();
        }

        List<Integer> startPosition;
        List<Integer> endPosition;


        startPosition = getListPosition(listCoordinate.get(indexStartCoordinate));
        endPosition = getListPosition(listCoordinate.get(indexEndShipCoordinate));

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

    //if length excess size ship return true
    private boolean isBiggerShipSize(int X_start, int Y_start, int X_end, int Y_end) {

        boolean isBiggerSize_Y = Y_end - Y_start != sizeShip;
        boolean isBiggerSize_X = X_end - X_start != sizeShip;
        return isBiggerSize_Y | isBiggerSize_X;
    }

    // if coordinate out of bounds -> Z or -1
    private boolean isOutOfBounds(int X_start, int Y_start, int X_end, int Y_end) {

        boolean isOutOfBoundsStart = (Y_start < BEGIN) & (X_start < BEGIN);
        boolean isOutOfBoundsEnd = (Y_end >= SIZE_Y) & (X_end >= SIZE_Y);
        return isOutOfBoundsStart & isOutOfBoundsEnd;
    }

    // A1 B2
    private boolean isPlaceDiagonally(int X_start, int Y_start, int X_end, int Y_end) {
        return X_start != X_end & Y_start != Y_end;
    }


    // J5 -> [9, 5]
    private List<Integer> getListPosition(String coordinate) {
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

    // if coordinate written reverse - > A10 A9 -> return true
    private boolean isMixedCoordinates(int start, int end) {
        return (end - start < 0);
    }
}
