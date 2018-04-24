package compiler.syntax.nonTerminal;

public class Parameter extends NonTerminal {

    private final String identifier;
    private final ArrayRange range;
    private final ArrayRange rangeEnd;

    public Parameter(String identifier, ArrayRange range, ArrayRange rangeEnd) {
        this.identifier = identifier;
        this.range = range;
        this.rangeEnd = rangeEnd;
    }

    public String getName() {
        return identifier;
    }

    public ArrayRange getRange() {
        return range;
    }

    public ArrayRange getRangeEnd() {
        return rangeEnd;
    }
}
