package battleship;


import battleship.WorkWithText.FieldToConsoleTextFormat;

import java.util.Arrays;


public class PlayingField {
    private static final char[][] field = new char[FieldSizeAndValues.SIZE_Field_STR][FieldSizeAndValues.SIZE_Field_COLUMNS];

    protected PlayingField() {
        initFillField();
    }

    private void initFillField() {
        char[] strFillFogBlocks = new char[FieldSizeAndValues.SIZE_Field_COLUMNS];
        Arrays.fill(strFillFogBlocks, FieldSizeAndValues.FOG_BlOCK);

        for (int i = 0; i < FieldSizeAndValues.SIZE_Field_STR; i++) {
            field[i] = strFillFogBlocks;
        }
    }

    @Override
    public String toString() {
        FieldToConsoleTextFormat fieldToText = new FieldToConsoleTextFormat(FieldSizeAndValues.SIZE_Field_STR);
        // get String Field
        return fieldToText.getStrFieldFormatOutConsole(field);
    }

    public char[][] getField() {
        return field;
    }
}
