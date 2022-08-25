package battleship.Ships;

import java.util.List;

import static battleship.Field.TextConst.LINE_BREAK;
import static battleship.Field.FieldSettings.*;

public class Ship {
    private final int size;
    private int length;

    private Position startPosition;
    private Position endPosition;
    private final String nameShip;
    private final String ErrorLength;
    private final String initMessage;

    public Ship(int size, String nameShip) {
        this.size = size;
        length = size;
        this.nameShip = nameShip;
        initMessage = String.format("Enter the coordinates of the %s (%d cells):", nameShip, size);
        ErrorLength = String.format("Error! Wrong length of the %s! Try again:", nameShip);
    }

    public boolean fieldFreeForShip(List<StringBuilder> gameField, String position) {
        boolean isError = true;
        List<String> stringCoordinate = List.of(position.toUpperCase().trim().split("\\s+"));

        if (stringCoordinate.size() > Position.SIZE) {
            System.out.println(ErrorLength + LINE_BREAK);
            return isError;
        }
        startPosition = new Position();
        endPosition = new Position();

        try {
            // 0 - startPos ; 1 - endPos
            startPosition.setCoordinates(stringCoordinate.get(INDEX_START_COORDINATE));
            endPosition.setCoordinates(stringCoordinate.get(INDEX_END_SHIP_COORDINATE));

            this.checkAndCorrectorMixedCoordinates(startPosition, endPosition);

            isError = new CheckCoordinates().CheckErrorSetShip(size, gameField, startPosition, endPosition);

        } catch (NumberFormatException e) {
            System.out.println(Error_Location_Ship + LINE_BREAK);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ErrorLength + LINE_BREAK);
        } catch (IllegalArgumentException e) {
            System.out.println(ErrorPlace + LINE_BREAK);
        }
        return isError;
    }

    // if coordinate written reverse - >  A10 A9  -> return true
    private boolean checkAndCorrectorMixedCoordinates(Position start, Position end) {
        boolean isMixed = true;

        if (end.compareTo(start) < BEGIN) {
            endPosition = start;
            startPosition = end;
            return isMixed;
        } else {
            return !isMixed;
        }
    }

    public void printInitMessage() {
        System.out.println(initMessage + LINE_BREAK);
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public int getLength() {
        return length;
    }

    public void HitShip() {
        length--;
    }
}
