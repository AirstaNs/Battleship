package battleship.Field;

public interface FieldSettings {
    int SIZE_X = 10;
    int SIZE_Y = 10;
    int BEGIN = 0;
    int BEGIN_NUMERIC = 1;
    char FOG_BlOCK = '~';
    char SHIP_BLOCK = 'O';
    char BROKEN_BLOCK = 'X';
    char MISS_BLOCK = 'M';
    int INDEX_START_COORDINATE = 0;
    int INDEX_END_SHIP_COORDINATE = 1;
    int ZERO_BLOCK_SHIP = 0;
     String ErrorPlace = "Error! You placed it too close to another one.";
     String Error_Location_Ship = "Error! Wrong ship location! Try again:";
     String Error_Location_Shot = "Error! You entered the wrong coordinates! Try again:";
     String ErrorNumberCoordinate = "Error! should be 1 coordinate";
}
