package battleship.Ships;

import battleship.Field.DrawField.TextConst;

import java.util.List;


public class Position {
    protected static final int SIZE = 2;
    private int X = Integer.MIN_VALUE;
    private int Y = Integer.MIN_VALUE;

    public Position(String position) {
        setCoordinates(position);
    }

    public Position() {
    }

    public void setCoordinates(String position) throws NumberFormatException, IndexOutOfBoundsException {
        this.X = intOf_X(position);
        this.Y = intOf_Y(position);
    }


    // J5 -> [9, 5]       return  {X_start, Y_start}, {X_end, Y_end}
    public List<Integer> getListPosition(String coordinate) {
        if (X == Integer.MIN_VALUE & Y == Integer.MIN_VALUE) {
            setY(coordinate);
            setX(coordinate);
        }
        return List.of(X, Y);
    }

    // B5 -> get [B] to int ->  A-J % 65
    private int intOf_Y(String y_coordinates) throws IndexOutOfBoundsException {
        int Y_indexString = 0;
        char char_Y_start = y_coordinates.charAt(Y_indexString);
        int convertedY = char_Y_start % TextConst.A_char.toChar(); // A-J % 65 get int
        return normalizationCoordinate(convertedY);
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

    public void setX(String str) {
        this.X = intOf_X(str);
    }

    public void setY(String str) {
        this.Y = intOf_Y(str);
    }
}
