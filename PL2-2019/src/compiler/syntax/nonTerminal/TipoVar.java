package compiler.syntax.nonTerminal;

public class TipoVar extends NonTerminal {

	private IntOBool intOBool;
	private String identificador;

	public TipoVar(IntOBool intOBool) {
		super();
		this.intOBool = intOBool;
	}

	public TipoVar(String identificador) {
		super();
		this.identificador = identificador;
	}

	public IntOBool getIntOBool() {
		return this.intOBool;
	}

	public String getIdentificadorTipo() {
		if (esTipoCustom()) {
			return identificador;
		} else {
			if (intOBool.isBool()) {
				return "boolean";
			} else {
				return "int";
			}
		}
	}

	public boolean esTipoCustom() {
		return identificador != null;
	}

	@Override
	public String toString() {
		return "TipoVar{" +
				"intOBool=" + intOBool +
				", identificador='" + identificador + '\'' +
				'}';
	}
}