package compiler.syntax.nonTerminal;

public class ExpressionWithoutAssignation extends Expression {

    private int value;
    private ArrayRange from;
    private ArrayRange to;
    private String identifier;


    public ExpressionWithoutAssignation() {
        super(null);
    }

    public ExpressionWithoutAssignation(Object obj) {
        super(null);
    }

    public ExpressionWithoutAssignation(int value) {
        super(null);
        this.value = value;
    }

    public ExpressionWithoutAssignation(String identifier, ArrayRange from, ArrayRange to) {
        super(null);
        this.identifier = identifier;
        this.from = from;
        this.to = to;
    }


    public String getIdentifier() {
        return identifier;
    }

    public ArrayRange getTo() {
        return to;
    }

    public ArrayRange getFrom() {
        return from;
    }

    public int getValue() {
        return value;
    }
}
