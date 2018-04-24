package compiler.syntax.nonTerminal;

public class ArrayRangeDeclaration extends NonTerminal {

    private final int value;

    public ArrayRangeDeclaration(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
