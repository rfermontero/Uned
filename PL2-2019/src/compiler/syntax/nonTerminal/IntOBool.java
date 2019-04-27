package compiler.syntax.nonTerminal;

public class IntOBool extends NonTerminal {

	private final String tipo;

	public IntOBool(String tipo) {
		super();
		this.tipo = tipo;
	}

	public boolean isBool() {
		return this.tipo.equals("BOOL");
	}

	public boolean isInteger() {
		return this.tipo.equals("INTEGER");
	}

}