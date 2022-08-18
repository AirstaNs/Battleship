package battleship;

import java.util.ArrayList;
import java.util.List;

public class UtilsFillField {
    protected static void fillOneColumnLetters(String[][] field, int start, int endStr ) {
        List<String> let = getMassLetters();
        for (int i = start+1; i < endStr; i++) {
            field[i][0] = let.get(i - 1);
        }
    }
    protected static List<String> getMassLetters() {
        List<String> letter = new ArrayList<>();
        for (char i = 'A'; i <= 'J'; i++) {
            letter.add(String.valueOf(i));
        }
        return letter;
    }

    protected static void fillSpaceField(String[][] field, int start, int endStr, int endColumn) {
        field[0][0] = " ";
        for (int i = start; i < endStr; i++) {
            for (int j = start+1; j < endColumn; j += 2) {
                field[i][j] = " ";
            }
        }
    }

    protected static void FillOneStrNumbers(String[][] field, int start, int endColumn) {
        for (int i = start+1; i < endColumn; i ++) {
            field[0][i] = String.valueOf(i);
        }
    }
    protected static void FillFieldFOG(String[][] field, int start, int endStr, int endColumn){
        for (int i = start+1; i <endStr ; i++) {
            for (int j = start+1; j <endColumn ; j++) {
                field[i][j] = FieldSizeAndValues.FOG_BlOCK;
            }
        }
    }
}
