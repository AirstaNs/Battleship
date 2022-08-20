package battleship.Field;

public interface FieldSettings {
    int SIZE_X = 10;
    int SIZE_Y = 10;
    int BEGIN = 0;
    char FOG_BlOCK = '~';
    char SHIP_BLOCK = 'O';
    char BROKEN_BLOCK = 'X';
    char MISS_BLOCK = 'M';
    int INDEX_START_COORDINATE = 0;
    int INDEX_END_SHIP_COORDINATE = 1;
}
