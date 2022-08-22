package battleship.Field.DrawField;

import battleship.Field.FieldSettings;
import battleship.Ships.Position;

import java.util.ArrayList;
import java.util.List;

import static battleship.Field.DrawField.TextConst.LINE_BREAK;
import static battleship.Field.FieldSettings.BEGIN;
import static battleship.Field.FieldSettings.BEGIN_NUMERIC;

public class TextField {

    private final int FieldStr;
    private final int NumberTitle;
    private final List<StringBuilder> textField;
    private final int skipSpace = 2;
    private final int skipNumericStr = 1;
    private final int skipLetter = 2;

    public TextField(int sizeStr, char[][] massField) {
        this.FieldStr = sizeStr;
        this.NumberTitle = sizeStr;
        this.textField = initTextField(massField);
    }

    private List<StringBuilder> initTextField(char[][] massField) {
        List<StringBuilder> fieldTextFormatted = new ArrayList<>();
        // fill one str __1_2_3_4_5_6_7_8_9_10
        fieldTextFormatted.add(fillWithTitleNumbers(NumberTitle));
        // fill zero point to Letter A  \n B   \n  C  e.t.c
        concatFieldAndLetter(fieldTextFormatted, massField);
        // get String Field
        return fieldTextFormatted;
    }

    public void drawFieldToConsole() {
        for (StringBuilder stringBuilder : textField) {
            System.out.println(stringBuilder.toString());
        }
        System.out.print(LINE_BREAK);
    }

    public List<StringBuilder> getTextField() {
        return textField;
    }

    public void drawShipHorizontally(int Y, int start, int end) {
        int startStr = skipNumericStr + Y;

        int normalizedLength = end - start + 1;
        int startShip = start + skipLetter + start;
        int endShip = startShip + normalizedLength * skipSpace;

        for (int i = startShip; i < endShip; i += skipSpace) {
            textField.get(startStr).setCharAt(i, FieldSettings.SHIP_BLOCK);
        }
    }

    public void drawShipVertically(int X, int start, int end) {
        int startShip = start + skipNumericStr;
        int endShip = end + skipNumericStr;

        int startColumn = X + skipLetter + X;

        for (int i = startShip; i <= endShip; i++) {
            textField.get(i).setCharAt(startColumn, FieldSettings.SHIP_BLOCK);
        }
    }

    public void drawShot(char block, Position position) {
        int startX = position.getX() + skipLetter + position.getX();
        int Y = position.getY() + skipNumericStr;
        textField.get(Y).setCharAt(startX, block);
    }

    protected void concatFieldAndLetter(List<StringBuilder> textField, char[][] massField) {
        // getList A B C D E F G H I J
        List<String> letters = getMassLetters();

        for (int i = BEGIN; i < FieldStr; i++) {
            StringBuilder b = new StringBuilder();
            // ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            b.append(AddSpaceAndChar(massField[i]));
            // A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            b.insert(BEGIN, letters.get(i));
            // trimCapacity str
            this.trimSizeCapacityTextStr(b);
            // add new str to List Str
            textField.add(b);
        }
    }

    // ~~~~~~~~~~   ->  _~ ~ ~ ~ ~ ~ ~ ~ ~ ~
    protected String AddSpaceAndChar(char[] mass) {
        String[] addSpaceChar = new String[mass.length];
        for (int i = BEGIN; i < mass.length; i++) {
            addSpaceChar[i] = (TextConst.SPACE.toString() + mass[i]);
        }
        return String.join(TextConst.EMPTY.toString(), addSpaceChar);
    }

    // fill one str __1_2_3_4_5_6_7_8_9_10
    protected StringBuilder fillWithTitleNumbers(int sizeStringFieldStr) {
        StringBuilder strNums = new StringBuilder(TextConst.SPACE.toString());
        // zero position = _
        for (int i = BEGIN_NUMERIC; i <= sizeStringFieldStr; i++) {
            strNums.append(TextConst.SPACE);
            strNums.append(i);
        }
        this.trimSizeCapacityTextStr(strNums);
        return strNums;
    }

    // getList A B C D E F G H I J
    protected List<String> getMassLetters() {
        List<String> letter = new ArrayList<>();
        char start = TextConst.A_char.toChar();
        char end = TextConst.J_char.toChar();
        for (char i = start; i <= end; i++) {
            letter.add(String.valueOf(i));
        }
        return letter;
    }

    private void trimSizeCapacityTextStr(StringBuilder str) {
        if (str != null) {
            str.trimToSize();
        }
    }
}
