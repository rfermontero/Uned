package compiler.syntax.nonTerminal;

public class SentReturn extends NonTerminal {

	private Expresion expresion;

	public SentReturn(Expresion expresion) {
		super();
		this.expresion = expresion;
	}

	public Expresion getExpresion() {
		return expresion;
	}
}