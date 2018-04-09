package compiler.syntax.nonTerminal;

public class RangoMatrizDeclr extends NonTerminal {

    private final int value;

    public RangoMatrizDeclr(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
