package compiler.syntax.nonTerminal;

public class ArrayRange extends NonTerminal {

    private final Expression expression;

    public ArrayRange(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
