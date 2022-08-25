package battleship.Ships;

import battleship.Field.DrawField.TextConst;

import java.util.List;

import static battleship.Field.FieldSettings.SIZE_X;


public class Position {
    protected static final int SIZE = 2;
    private int X = Integer.MIN_VALUE;
    private int Y = Integer.MIN_VALUE;

    public Position(String position) throws NumberFormatException, IndexOutOfBoundsException {
        setCoordinates(position);
    }

    public Position() {
    }

    // String -> int
    public void setCoordinates(String position) throws NumberFormatException, IndexOutOfBoundsException {
        this.X = intOf_X(position);
        this.Y = intOf_Y(position);
    }
    public void setCoordinates(List<Integer> coordinates)  {
        int x = 0;
        int y = 1;
        this.setX(coordinates.get(x));
        this.setY(coordinates.get(y));
    }

    // J5 -> [9, 5]       return  {X_start, Y_start}, {X_end, Y_end}
    public List<Integer> getListPosition(String coordinate) {
        if (isNotFieldInitialized()) {
            setY(coordinate);
            setX(coordinate);
        }
        return List.of(X, Y);
    }

    public List<Integer> getListPosition() {
        return isNotFieldInitialized() ? List.of() : List.of(X, Y);
    }

    // B5 -> get [B] to int ->  A-J % 65
    private int intOf_Y(String y_coordinates) throws IndexOutOfBoundsException {
        int Y_StartIndexString = 0;
        char char_Y_start = y_coordinates.charAt(Y_StartIndexString);
        int convertedY = char_Y_start % TextConst.A_char.toChar(); // A-J % 65 get int

        if (!(convertedY < SIZE_X)) {
            throw new IndexOutOfBoundsException();
        }
        return convertedY;
    }

    // A10 -> get 10
    private int intOf_X(String x_coordinates) throws NumberFormatException {
        int X_indexString = 1;
        String str_X_end = x_coordinates.substring(X_indexString);
        int convertedX = Integer.parseInt(str_X_end);
        return normalizationCoordinate(convertedX);
    }

    private int normalizationCoordinate(int coordinate) {
        return --coordinate;
    }

    private boolean isNotFieldInitialized() {
        return X == Integer.MIN_VALUE & Y == Integer.MIN_VALUE;
    }

    public void setX(String str) {
        this.X = intOf_X(str);
    }

    public void setY(String str) {
        this.Y = intOf_Y(str);
    }

    public void setX(int x) {
        this.X = x;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}
