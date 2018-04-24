package compiler.syntax.nonTerminal;

public class PrintISentence extends AbstractSentence {
    private final Expression expression;

    public PrintISentence(Expression expression) {
        this.expression = expression;
    }

    public PrintISentence() {
        this.expression = null;
    }

    public Expression getExpression() {
        return expression;
    }
}
