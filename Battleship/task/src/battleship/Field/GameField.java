package battleship.Field;


import battleship.Field.DrawField.TextConst;
import battleship.Ships.Position;
import java.util.ArrayList;
import java.util.List;

import static battleship.Field.DrawField.TextConst.LINE_BREAK;
import static battleship.Field.DrawField.TextConst.SPACE;
import static battleship.Field.FieldSettings.*;


public class GameField {
    private final List<StringBuilder> field;
    private static final String stringNumber = drawTitleNumbers();
    private static final List<Character> letter = getMassLetters();
    private final int skipSpace = 2;

    //viewFieldForAnotherPlayer
    public GameField() {
        field = FieldFilledFog();
        // TODO  textField = new TextField(FieldSettings.SIZE_Y, field);
    }

    // The ship is standing horizontally or vertically
    public void drawShipToField(Position startPosition, Position endPosition) {
        if (endPosition.getY() == startPosition.getY()) {
            drawShipHorizontally(startPosition.getY(), startPosition.getX(), endPosition.getX());
        } else {
            drawShipVertically(startPosition.getX(), startPosition.getY(), endPosition.getY());
        }
    }

    // The ship is standing horizontally
    private void drawShipHorizontally(int Y, int start, int end) {
        int normalizedLength = end - start + 1;
        int startShip = start * skipSpace;
        int endShip = startShip + normalizedLength * skipSpace;

        for (int i = startShip; i < endShip; i += skipSpace) {
            field.get(Y).setCharAt(i, FieldSettings.SHIP_BLOCK);
        }

    }

    // The ship is standing Vertically
    private void drawShipVertically(int X, int start, int end) {
        int startColumn = skipSpace * X;
        for (int i = start; i <= end; i++) {
            field.get(i).setCharAt(startColumn, FieldSettings.SHIP_BLOCK);
        }
    }

    public void drawFieldToConsole() {
        System.out.println(stringNumber);

        for (int i = 0; i < SIZE_Y; i++) {
            // A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            System.out.printf("%c %s", letter.get(i), field.get(i));
            System.out.print(LINE_BREAK);
        }
        System.out.print(LINE_BREAK);
    }

    //Создает и заполняет массив туманом
    public List<StringBuilder> FieldFilledFog() {
        List<StringBuilder> textField = new ArrayList<>();
        // fill all str ~~~~~~~~~~~
        String str = getStringFillingFog();

        for (int i = BEGIN; i < SIZE_Y; i++) {
            StringBuilder b = new StringBuilder();
            // ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            b.append(str);
            // trimCapacity str
            trimSizeCapacityTextStr(b);
            // add new str to List Str
            textField.add(b);
        }
        return textField;
    }

    // ~~~~~~~~~~   ->  _~ ~ ~ ~ ~ ~ ~ ~ ~ ~
    private String getStringFillingFog() {
        StringBuilder b = new StringBuilder();
        for (int i = BEGIN; i < FieldSettings.SIZE_X; i++) {
            b.append(FieldSettings.FOG_BlOCK);
            b.append(SPACE);
        }
        b.deleteCharAt((FieldSettings.SIZE_X * skipSpace) - 1);
        trimSizeCapacityTextStr(b);
        return b.toString();
    }

    // fill one str __1_2_3_4_5_6_7_8_9_10
    private static String drawTitleNumbers() {
        StringBuilder strNums = new StringBuilder(TextConst.SPACE.toString());
        // zero position = _
        for (int i = BEGIN_NUMERIC; i <= FieldSettings.SIZE_X; i++) {
            strNums.append(TextConst.SPACE);
            strNums.append(i);
        }
        return strNums.toString();
    }

    // getList A B C D E F G H I J
    private static List<Character> getMassLetters() {
        List<Character> letter = new ArrayList<>();
        char start = TextConst.A_char.toChar();
        char end = TextConst.J_char.toChar();
        for (char i = start; i <= end; i++) {
            letter.add(i);
        }
        return letter;
    }

    private void trimSizeCapacityTextStr(StringBuilder str) {
        if (str != null) {
            str.trimToSize();
        }
    }

    public List<StringBuilder> getField() {
        return field;
    }
}
