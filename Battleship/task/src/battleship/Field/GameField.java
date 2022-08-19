package battleship.Field;

import battleship.Field.DrawField.TextField;

import java.util.Arrays;
import java.util.Scanner;


public class GameField {
    private final char[][] field = new char[FieldSettings.SIZE_Y][FieldSettings.SIZE_X];
    private final TextField textField;
    private int COUNT_SHIP = 5;

    public GameField() {
        initFillField();
        textField = new TextField(FieldSettings.SIZE_Y, field);
    }

    private void initFillField() {
        // fill one str ~~~~~~~~~~~
        char[] strFillFogBlocks = new char[FieldSettings.SIZE_X];
        Arrays.fill(strFillFogBlocks, FieldSettings.FOG_BlOCK);
        // fill all str ~~~~~~~~~~~
        for (int i = 0; i < FieldSettings.SIZE_Y; i++) {
            field[i] = strFillFogBlocks;
        }
    }

    public char[][] getField() {
        return field;
    }

    public TextField getTextField() {
        return textField;
    }

    public void fillFieldShips(Scanner scanner) {

    }

}
