package battleship.Field.DrawField;

import java.util.ArrayList;
import java.util.List;

public class TextField {

    private final int FieldStr;
    private final int NumberTitle;
    private final StringBuilder textField;

    public TextField(int sizeStr, char[][] massField) {
        FieldStr = sizeStr;
        NumberTitle = sizeStr + 1;
        textField = initTextField(massField);
    }

    private StringBuilder initTextField(char[][] massField) {
        //init  space char in title (_)
        StringBuilder fieldTextFormatted = new StringBuilder(TextConst.SPACE.toString());
        // fill one str __1_2_3_4_5_6_7_8_9_10
        fieldTextFormatted.append(fillWithTitleNumbers(NumberTitle));
        // fill zero point to Letter A  \n B   \n  C  e.t.c
        concatFieldAndLetter(fieldTextFormatted, massField);
        // get String Field
        return fieldTextFormatted;
    }

    public void drawFieldToConsole() {
        System.out.println(textField.toString());
    }

    public StringBuilder getTextField() {
        return textField;
    }

    public void drawShot(String Shot) {

    }

    protected void concatFieldAndLetter(StringBuilder formatFieldText, char[][] massField) {
        // getList A B C D E F G H I J
        List<String> letters = getMassLetters();

        for (int i = 0; i < FieldStr; i++) {
            StringBuilder b = new StringBuilder();
            // add  ( \n )
            formatFieldText.append(TextConst.LINE_BREAK);
            // ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            b.append(AddSpaceAndChar(massField[i]));
            // A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            b.insert(0, letters.get(i));
            // add new str to main Str
            formatFieldText.append(b);
        }
    }

    // ~~~~~~~~~~   ->  _~ ~ ~ ~ ~ ~ ~ ~ ~ ~
    protected String AddSpaceAndChar(char[] mass) {
        String[] addSpaceChar = new String[mass.length];
        for (int i = 0; i < mass.length; i++) {
            addSpaceChar[i] = (TextConst.SPACE.toString() + mass[i]);
        }
        return String.join(TextConst.EMPTY.toString(), addSpaceChar);
    }

    // fill one str __1_2_3_4_5_6_7_8_9_10
    protected String fillWithTitleNumbers(int sizeStringFieldStr) {
        StringBuilder strNums = new StringBuilder();
        // zero position = _
        for (int i = 1; i < sizeStringFieldStr; i++) {
            strNums.append(TextConst.SPACE);
            strNums.append(i);
        }
        return strNums.toString();
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
}
