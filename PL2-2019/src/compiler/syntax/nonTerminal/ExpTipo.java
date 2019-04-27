package compiler.syntax.nonTerminal;

public class ExpTipo extends NonTerminal {

	private final String identificador;
	private final EntOId left;
	private final EntOId rigth;
	private final IntOBool intOBool;

	public ExpTipo(String identificador, EntOId left, EntOId rigth, IntOBool intOBool) {
		super();
		this.identificador = identificador;
		this.left = left;
		this.rigth = rigth;
		this.intOBool = intOBool;
	}

	public String getIdentificador() {
		return identificador;
	}

	public EntOId getLeft() {
		return left;
	}

	public EntOId getRigth() {
		return rigth;
	}

	public IntOBool getIntOBool() {
		return intOBool;
	}
}