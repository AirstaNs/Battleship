package battleship.Ships;

import battleship.Field.FieldSettings;

import java.util.Arrays;
import java.util.List;

import static battleship.Field.FieldSettings.INDEX_END_SHIP_COORDINATE;
import static battleship.Field.FieldSettings.INDEX_START_COORDINATE;

abstract public class Ship {
    private int size;
    private  String  nameShip;
    private final String ErrorLength = String.format("Error! Wrong length of the %s! Try again:", nameShip);
    private final String ErrorPlace = "Error! You placed it too close to another one.";
    private final String ErrorLocation = "Error! Wrong ship location! Try again:";
    String initMessage = String.format("Enter the coordinates of the %s (%d cells):", nameShip, size);
    protected Position startPosition;
    protected Position endPosition;

    public Ship(int size,String nameShip) {
        this.size = size;
        this.nameShip = nameShip;
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

            this.checkAndCorrectorMixedCoordinates(startPosition, endPosition);

            check.allCheckError(field, startPosition, endPosition);

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

        this.setStrategySettingShip(field, startPosition, endPosition);

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

    // The ship is standing horizontally or vertically
    private void setStrategySettingShip(char[][] field, Position startPosition, Position endPosition) {
        if (endPosition.getY() == startPosition.getY())
            setShipFieldHorizontally(field, startPosition.getY(), startPosition.getX(), endPosition.getX());
        else {
            setShipFieldVertically(field, startPosition.getX(), startPosition.getY(), startPosition.getY());
        }
    }

    // The ship is standing horizontally
    protected void setShipFieldHorizontally(char[][] field, int Y, int start, int End) {
        Arrays.fill(field[Y], start, End, FieldSettings.SHIP_BLOCK);
    }

    // The ship is standing Vertically
    protected void setShipFieldVertically(char[][] field, int X, int start, int End) {
        for (int i = start; i < End; i++) {
            field[i][X] = FieldSettings.SHIP_BLOCK;
        }
    }

    public void printInitMessage() {
        System.out.println(initMessage);
    }
}
