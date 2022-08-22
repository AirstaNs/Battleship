package battleship.Ships;

import battleship.Field.DrawField.TextConst;

import java.util.List;

import static battleship.Field.FieldSettings.INDEX_END_SHIP_COORDINATE;
import static battleship.Field.FieldSettings.INDEX_START_COORDINATE;

public class Ship {
    private int size;
    private String nameShip;
    private final String ErrorLength;
    private final String ErrorPlace = "Error! You placed it too close to another one.";
    private final String ErrorLocation = "Error! Wrong ship location! Try again:";
    private final String initMessage;

    private Position startPosition;
    private Position endPosition;

    public Ship(int size, String nameShip) {
        this.size = size;
        this.nameShip = nameShip;
        initMessage = String.format("Enter the coordinates of the %s (%d cells):", nameShip, size);
        ErrorLength = String.format("Error! Wrong length of the %s! Try again:", nameShip);
    }

    public boolean fieldFreeForShip(char[][] gameField, String position) {
        boolean isError = false;

        List<String> stringCoordinate = List.of(position.toUpperCase().trim().split("\\s+"));

        if (stringCoordinate.size() > Position.SIZE) {
            System.out.println(ErrorLength);
            return isError;
        }

        startPosition = new Position();
        endPosition = new Position();

        CheckCoordinates check = new CheckCoordinates();

        try {
            // 0 - startPos ; 1 - endPos
            startPosition.setCoordinates(stringCoordinate.get(INDEX_START_COORDINATE));
            endPosition.setCoordinates(stringCoordinate.get(INDEX_END_SHIP_COORDINATE));

            this.checkAndCorrectorMixedCoordinates(startPosition, endPosition);

            check.CheckErrorSetShip(size, gameField, startPosition, endPosition);

        } catch (NumberFormatException e) {
            System.out.println(ErrorLocation + TextConst.LINE_BREAK);
            return isError;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ErrorLength + TextConst.LINE_BREAK);
            return isError;
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorPlace + TextConst.LINE_BREAK);
            return isError;
        }
        return !isError;
    }

    // if coordinate written reverse - >  A10 A9  -> return true
    private boolean checkAndCorrectorMixedCoordinates(Position start, Position end) {
        int zero = 0;
        boolean isMixed = true;
        int sumStart = start.getX() + start.getY();
        int sumEnd = end.getX() + end.getY();

        if (sumEnd - sumStart < zero) {
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
        System.out.println();
        System.out.println(initMessage);
        System.out.println();
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }
}
