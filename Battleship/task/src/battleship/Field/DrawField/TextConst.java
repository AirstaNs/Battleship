package battleship.Field.DrawField;

public enum TextConst {
    SPACE(" "),
    EMPTY(""),
    LINE_BREAK("\n");
    private final String symbol;

    TextConst(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}
