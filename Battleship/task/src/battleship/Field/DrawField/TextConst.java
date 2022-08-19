package battleship.Field.DrawField;

public enum TextConst {
    SPACE(" "),
    EMPTY(""),
    LINE_BREAK("\n"),
    A_char("A"),
    J_char("J");

    private final String symbol;

    TextConst(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return this.symbol;
    }
    public char toChar(){
        return symbol.charAt(0);
    }
}
