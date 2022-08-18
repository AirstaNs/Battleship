package battleship;

import java.util.ArrayList;
import java.util.List;

public class FieldToConsoleTextFormat {
    private static final String space = " ";
    private static final String Empty = "";
    private static final String LineBreak = "\n";
    private final int sizeStringFieldStr;
    private final int getSizeStringFieldStrForNumberTitle;

    public FieldToConsoleTextFormat(int sizeStr) {
        sizeStringFieldStr = sizeStr;
        getSizeStringFieldStrForNumberTitle = sizeStr + 1;
    }

    public String getStrFieldFormatOutConsole(char[][] massField) {
        //init  space char in title (_)
        StringBuilder formatFieldText = new StringBuilder(space);
        // fill one str __1_2_3_4_5_6_7_8_9_10
        formatFieldText.append(fillWithTitleNumbers(getSizeStringFieldStrForNumberTitle));

        concatFieldAndLetter(formatFieldText, massField);
        // get String Field
        return formatFieldText.toString();
    }

    protected void concatFieldAndLetter(StringBuilder formatFieldText, char[][] massField) {
        // getList A B C D E F G H I J
        List<String> letters = getMassLetters();

        for (int i = 1; i < sizeStringFieldStr; i++) {
            StringBuilder b = new StringBuilder();
            // add  ( \n )
            formatFieldText.append(LineBreak);
            // ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            b.append(AddSpaceAndChar(massField[i - 1]));
            // A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            b.insert(0, letters.get(i - 1));
            // add new str to main Str
            formatFieldText.append(b);
        }
    }

    // ~~~~~~~~~~   ->  _~ ~ ~ ~ ~ ~ ~ ~ ~ ~
    protected String AddSpaceAndChar(char[] mass) {
        String[] addSpaceChar = new String[mass.length];
        for (int i = 0; i < mass.length; i++) {
            addSpaceChar[i] = (FieldToConsoleTextFormat.space + mass[i]);
        }
        return String.join(Empty, addSpaceChar);
    }

    // fill one str __1_2_3_4_5_6_7_8_9_10
    protected String fillWithTitleNumbers(int sizeStringFieldStr) {
        StringBuilder strNums = new StringBuilder();
        // zero position = _
        for (int i = 1; i < sizeStringFieldStr; i++) {
            strNums.append(space);
            strNums.append(i);
        }
        return strNums.toString();
    }

    // getList A B C D E F G H I J
    protected List<String> getMassLetters() {
        List<String> letter = new ArrayList<>();
        for (char i = 'A'; i <= 'J'; i++) {
            letter.add(String.valueOf(i));
        }
        return letter;
    }
}
