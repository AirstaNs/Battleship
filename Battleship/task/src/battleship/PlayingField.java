package battleship;


import java.util.Arrays;
import java.util.List;

import static java.lang.String.valueOf;

public class PlayingField {
    private static final char[][] field = new char[FieldSizeAndValues.SIZE_STR][FieldSizeAndValues.SIZE_COLUMNS];

    protected PlayingField() {
        initFillField();
    }

    private void initFillField() {
        char[] strFillFogBlocks = new char[FieldSizeAndValues.SIZE_COLUMNS];
        Arrays.fill(strFillFogBlocks, FieldSizeAndValues.FOG_BlOCK);

        for (int i = 0; i < FieldSizeAndValues.SIZE_STR; i++) {
            field[i] = strFillFogBlocks;
        }
    }


    private String fillOneStrNumbers(int sizeStringFieldStr) {
        StringBuilder strNums = new StringBuilder("  ");
        for (int i = 1; i < sizeStringFieldStr; i++) {
            strNums.append(i);
            strNums.append(" ");
        }
        return strNums.toString();
    }

    @Override
    public String toString() {
        int sizeStringFieldStr = FieldSizeAndValues.SIZE_STR + 1;

        String space = " ";
        // create 11 str
        String[] str = new String[sizeStringFieldStr];


        // fill one str __1_2_3_4_5_6_7_8_9_10
        str[0] = fillOneStrNumbers(sizeStringFieldStr);
        // getList A B C D E F G H I J
        List<String> letters = UtilsFillField.getMassLetters();

        for (int i = 1; i < sizeStringFieldStr; i++) {
            StringBuilder b = new StringBuilder();
            b.append("\n");
            // ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            b.append(String.join("",AddChar(field[i-1], space)));
            // A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            b.insert(1, letters.get(i - 1) + space);

            str[i] = b.toString();
        }
        return String.join("", str);
    }

    public char[][] getField() {
        return field;
    }

    private String[] AddChar(char[] mass, String ch) {
        String[] addSpaceChar = new String[mass.length];
        for (int i = 0; i < mass.length; i++) {
            addSpaceChar[i] = (mass[i] + ch);
        }
        return addSpaceChar;
    }
}
