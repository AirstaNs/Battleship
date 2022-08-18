package battleship;


import java.util.ArrayList;
import java.util.List;

public class PlayingField {
    private static final String[][] field = new String[11][21];
    private static final String FOG = "~";

    protected PlayingField() {
        initFillField();
    }

    private void initFillField() {

        initSpace();

        initNumbers();

        List<String> let = massLetter();
        for (int i = 1; i < 11; i++) {
            field[i][0] = let.get(i - 1);
        }
        for (int i = 1; i < field.length; i++) {
            for (int j = 2; j < field[i].length; j += 2) {
                field[i][j] = FOG;
            }
        }
    }

    private List<String> massLetter() {
        List<String> letter = new ArrayList<>();
        for (char i = 'A'; i <= 'J'; i++) {
            letter.add(String.valueOf(i));
        }
        return letter;
    }

    private void initSpace() {
        field[0][0] = " ";
        for (int i = 0; i < 11; i++) {
            for (int j = 1; j < 21; j += 2) {
                field[i][j] = " ";
            }
        }
    }

    private void initNumbers() {
        for (int i = 0; i < 21; i += 2) {
            int numberColumn = i / 2;
            field[0][i] = String.valueOf(numberColumn);
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                b.append(field[i][j]);
            }
            b.append("\n");
        }
        return b.toString();
    }

    public String[][] getField() {
        return field;
    }
}
