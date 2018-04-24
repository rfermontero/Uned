package compiler.syntax.nonTerminal;

public class ExpressionReturn extends AbstractReturn {
    private final Expression expression;
    public ExpressionReturn(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression(){
        return this.expression;
    }
}
