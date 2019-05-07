package compiler.syntax.nonTerminal;

public class SWriteInt extends NonTerminal {

	private Expresion expresion;

	public SWriteInt(Expresion expresion) {
		super();
		this.expresion = expresion;
	}


	public Expresion getExpresion() {
		return expresion;
	}
}