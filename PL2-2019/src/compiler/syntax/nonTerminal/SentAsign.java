package compiler.syntax.nonTerminal;

public class SentAsign extends NonTerminal {

	private final Variables variables;
	private final Expresion expresion;

	public SentAsign(Variables variables, Expresion expresion) {
		super();
		this.variables = variables;
		this.expresion = expresion;
	}

	public Variables getVariables() {
		return variables;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	@Override
	public String toString() {
		return "SentAsign{" +
				"variables=" + variables +
				", expresion=" + expresion +
				'}';
	}
}