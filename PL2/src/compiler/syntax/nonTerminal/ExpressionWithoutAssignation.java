package compiler.syntax.nonTerminal;

import compiler.semantic.type.TypeArray;
import es.uned.lsi.compiler.semantic.ScopeManagerIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;

public class ExpressionWithoutAssignation extends Expression {

	private int value;
	private ArrayRange from;
	private ArrayRange to;
	private String identifier;
	private Expression expression;
	private Expression leftExpression;
	private Expression rightExpression;
	private CallFunction callFunction;

	public ExpressionWithoutAssignation(Expression expression) {
		this.expression = expression;
	}

	public ExpressionWithoutAssignation(CallFunction callFunction){
		this.callFunction = callFunction;
	}

	public ExpressionWithoutAssignation(Expression leftExpression, Expression rightExpression) {
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}

	public ExpressionWithoutAssignation(int value) {
		this.value = value;
	}

	public ExpressionWithoutAssignation(String identifier) {
		this.identifier = identifier;
	}

	public ExpressionWithoutAssignation(String identifier, ArrayRange from, ArrayRange to) {
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

	public Expression getExpression() {
		return expression;
	}

	public Expression getLeftExpression() {
		return leftExpression;
	}

	public Expression getRightExpression() {
		return rightExpression;
	}

	public boolean checkResultValueIsArray(ScopeManagerIF scopeManager) {
		if (expression instanceof ExpressionWithoutAssignation) {
			ExpressionWithoutAssignation expressionWithoutAssignation = (ExpressionWithoutAssignation) expression;
			if (expressionWithoutAssignation.getIdentifier() != null && getFrom() == null) {
				SymbolIF identifierSymbol = scopeManager.searchSymbol(getIdentifier());
				return identifierSymbol.getType() instanceof TypeArray;
			}
		}
		return false;
	}
}
