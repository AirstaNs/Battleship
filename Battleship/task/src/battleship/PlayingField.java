package battleship;


import java.util.List;

public class PlayingField {
    private static final String[][] field = new String[FieldSizeAndValues.SIZE_STR][FieldSizeAndValues.SIZE_COLUMNS];

    protected PlayingField() {
        initFillField();
    }

    private void initFillField() {
//        field[0][0] = " ";
//        UtilsFillField.fillSpaceField(field,0, FieldSizeAndValues.SIZE_STR, FieldSizeAndValues.SIZE_COLUMNS);
//
//        UtilsFillField.FillOneStrNumbers(field,1, FieldSizeAndValues.SIZE_COLUMNS);
//
//        UtilsFillField.fillOneColumnLetters(field,1, FieldSizeAndValues.SIZE_STR);

        UtilsFillField.FillFieldFOG(field, 0, FieldSizeAndValues.SIZE_STR, FieldSizeAndValues.SIZE_COLUMNS);
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
        int sizeStringFieldColumn = FieldSizeAndValues.SIZE_COLUMNS * 2 + 1;

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
            b.append(String.join(" ", field[i - 1]));
            // A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
            b.insert(1, letters.get(i - 1) + " ");

            str[i] = b.toString();
        }
        return String.join("", str);
    }

    public String[][] getField() {
        return field;
    }
}
