package battleship;


public class PlayingField {
    private static final String[][] field = new String[FieldSizeAndValues.SIZE_STR][FieldSizeAndValues.SIZE_COLUMNS];

    protected PlayingField() {
        initFillField();
    }

    private void initFillField() {

        UtilsFillField.fillSpaceField(field,0, FieldSizeAndValues.SIZE_STR, FieldSizeAndValues.SIZE_COLUMNS);

        UtilsFillField.FillOneStrNumbers(field,0, FieldSizeAndValues.SIZE_COLUMNS);

        UtilsFillField.fillOneColumnLetters(field,0, FieldSizeAndValues.SIZE_STR);

        UtilsFillField.FillFieldFOG(field,0, FieldSizeAndValues.SIZE_STR, FieldSizeAndValues.SIZE_COLUMNS);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (String[] strings : field) {
            for (String string : strings) {
                b.append(string);
            }
            b.append("\n");
        }
        return b.toString();
    }

    public String[][] getField() {
        return field;
    }
}
