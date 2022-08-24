package battleship;

import battleship.Field.GameField;

public class Player {
    private boolean isWin = false;
    private final GameField playersGameField;
    public Player() {
        playersGameField = new GameField();
    }
    public GameField getPlayersGameField() {
        return playersGameField;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }
}
