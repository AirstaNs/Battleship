package battleship;


import java.util.Arrays;
import java.util.List;


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

    @Override
    public String toString() {
        FieldToConsoleTextFormat fieldToText = new FieldToConsoleTextFormat(FieldSizeAndValues.SIZE_STR);
        // get String Field
        return fieldToText.getStrFieldFormatOutConsole(field);
    }

    public char[][] getField() {
        return field;
    }
}
