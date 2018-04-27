package compiler.syntax.nonTerminal;

public class Assignation extends AbstractSentence {

	private final Expression expression;

	public Assignation(Expression expression) {
		super();
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}
}
