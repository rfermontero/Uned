package compiler.syntax.nonTerminal;

public class TipoRetorno extends NonTerminal {

	private IntOBool intOBool;

	public TipoRetorno() {
		super();
	}

	public TipoRetorno(IntOBool intOBool) {
		super();
		this.intOBool = intOBool;
	}

	public IntOBool getIntOBool() {
		return intOBool;
	}
}