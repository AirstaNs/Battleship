package battleship.Ships;

import battleship.Field.DrawField.TextConst;
import battleship.Field.FieldSettings;

import java.util.Arrays;
import java.util.List;

import static battleship.Field.FieldSettings.INDEX_END_SHIP_COORDINATE;
import static battleship.Field.FieldSettings.INDEX_START_COORDINATE;

abstract public class Ship {
    int size;
    String initMessage = String.format("Enter the coordinates of the %s (%d cells):", this.getClass().getSimpleName(),size);
    private final String ErrorLength = String.format("Error! Wrong length of the %s! Try again:", this.getClass().getSimpleName());
    private final String ErrorPlace = "Error! You placed it too close to another one.";
    private final String ErrorLocation = "Error! Wrong ship location! Try again:";

    public Ship(int size) {
        this.size = size;
    }

    public boolean setShip(char[][] field, String position) {
        boolean isError = true;

        CheckCoordinates check = new CheckCoordinates(size);
        List<Integer> startPosition;
        List<Integer> endPosition;
        try {
            List<List<Integer>> fullCoordinates = check.allChecks(field,position);
            startPosition =  fullCoordinates.get(INDEX_START_COORDINATE);
            endPosition = fullCoordinates.get(INDEX_END_SHIP_COORDINATE);

        } catch (NumberFormatException e) {
            System.out.println(ErrorLocation);
            return isError;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ErrorLength);
            return isError;
        }catch (IllegalArgumentException e){
            System.out.println(ErrorPlace);
            return isError;
        }
//        boolean isError = true;
//        int[][] coordinates;
//        try {
//            coordinates = convertStringToCoordinates(position);
//
//        } catch (NumberFormatException e) {
//            System.out.println(ErrorLocation);
//            return isError;
//        } catch (RuntimeException e) {
//            System.out.println(ErrorLength);
//            return isError;
//        }
//        int X_start = coordinates[0][0];
//        int Y_start = coordinates[0][1];
//
//        int X_end = coordinates[1][0];
//        int Y_end = coordinates[1][1];
//
//        if (isBiggerShipSize(X_start, Y_start, X_end, Y_end))
//            return isError;
//
//        boolean isOutOfBoundsStart = Y_start < FieldSettings.BEGIN & X_start < FieldSettings.BEGIN;
//        boolean isOutOfBoundsEnd = Y_end >= FieldSettings.SIZE_Y & X_end >= FieldSettings.SIZE_Y;
//
//        if (isOutOfBoundsStart & isOutOfBoundsEnd) {
//            System.out.println(ErrorLocation);
//            return isError;
//        }
//        return !isError;
    }

    private boolean isBiggerShipSize(int X_start, int Y_start, int X_end, int Y_end) {
        if ((Y_end - Y_start != size | X_end - X_start != size)) {
            System.out.println(ErrorLength);
            return true;
        }
        return false;
    }

    // return  {X_start, Y_start}, {X_end, Y_end}
    private int[][] convertStringToCoordinates(String position) throws RuntimeException, NumberFormatException {
        String[] coordinates = position.trim().split("\\s+");
        if (coordinates.length > 2) {
            throw new RuntimeException();
        }
        String strOneCoordinates = coordinates[0];
        String strTwoCoordinates = coordinates[1];

        int Y_start = strOneCoordinates.charAt(0) % TextConst.A_char.toChar();
        int X_start = Integer.parseInt(strOneCoordinates.substring(1));

        int Y_end = strTwoCoordinates.charAt(0) % TextConst.A_char.toChar();
        int X_end = Integer.parseInt(strTwoCoordinates.substring(1));

        if (isMixedCoordinates(Y_start, Y_end)) {
            int temp = Y_start;
            Y_start = Y_end;
            Y_end = temp;
        }
        if (isMixedCoordinates(X_start, X_end)) {
            int temp = X_start;
            X_start = X_end;
            X_end = temp;
        }

        return new int[][]{
                {X_start, Y_start},
                {X_end, Y_end},
        };
    }

    protected boolean isMixedCoordinates(int start, int end) {
        return (end - start < 0);
    }


    protected void setShipFieldHorizontally(char[][] field, int Y, int start, int End) {
        Arrays.fill(field[Y], start, End, FieldSettings.SHIP_BLOCK);
    }

    protected void setShipFieldVertical(char[][] field, int X, int start, int End) {
        for (int i = start; i < End; i++) {
            field[i][X] = FieldSettings.SHIP_BLOCK;
        }
    }

    public void printInitMessage() {
        System.out.println(initMessage);
    }
}
