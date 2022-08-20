package battleship.Ships;

import battleship.Field.DrawField.TextConst;
import battleship.Field.FieldSettings;

import java.util.Arrays;
import java.util.List;

import static battleship.Field.FieldSettings.INDEX_END_SHIP_COORDINATE;
import static battleship.Field.FieldSettings.INDEX_START_COORDINATE;

abstract public class Ship {
    String initMessage = String.format("Enter the coordinates of the %s (%d cells):", this.getClass().getSimpleName(), size);
    private final String ErrorLength = String.format("Error! Wrong length of the %s! Try again:", this.getClass().getSimpleName());
    private final String ErrorPlace = "Error! You placed it too close to another one.";
    private final String ErrorLocation = "Error! Wrong ship location! Try again:";
    int size;
    private Position startPosition;
    private Position endPosition;
    public Ship(int size) {
        this.size = size;
    }

    public boolean setShip(char[][] field, String position) {
        boolean isError = true;

        List<String> stringCoordinate = List.of(position.trim().split("\\s+"));

        if (stringCoordinate.size() > Position.SIZE) {
            System.out.println(ErrorLength);
            return isError;
        }

         startPosition = new Position();
         endPosition = new Position();

        CheckCoordinates check = new CheckCoordinates(size);

        try {
            // 0 - startPos ; 1 - endPos
            startPosition.setCoordinates(stringCoordinate.get(INDEX_START_COORDINATE));
            endPosition.setCoordinates(stringCoordinate.get(INDEX_END_SHIP_COORDINATE));
            this.checkAndCorrectorMixedCoordinates(startPosition,endPosition);

            check.allChecks(field, startPosition, endPosition);

        } catch (NumberFormatException e) {
            System.out.println(ErrorLocation);
            return isError;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ErrorLength);
            return isError;
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorPlace);
            return isError;
        }
        return !isError;
    }

    protected void setShipFieldHorizontally(char[][] field, int Y, int start, int End) {
        Arrays.fill(field[Y], start, End, FieldSettings.SHIP_BLOCK);
    }

    protected void setShipFieldVertical(char[][] field, int X, int start, int End) {
        for (int i = start; i < End; i++) {
            field[i][X] = FieldSettings.SHIP_BLOCK;
        }
    }
    // if coordinate written reverse - >  A10 A9  -> return true
    private boolean checkAndCorrectorMixedCoordinates(Position start, Position end) {
        boolean isMixed = true;
        int sumStart = start.getX() + start.getY();
        int sumEnd = end.getX() + end.getY();
        if (sumEnd - sumStart < 0) {
            int temp_start_X = start.getX();
            int temp_start_Y = start.getY();

            start.setX(end.getX());
            start.setY(end.getY());

            end.setX(temp_start_X);
            end.setY(temp_start_Y);
            return isMixed;
        } else {
            return !isMixed;
        }
    }

    public void printInitMessage() {
        System.out.println(initMessage);
    }
}
