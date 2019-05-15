package compiler.syntax.nonTerminal;

public class ProcParam extends NonTerminal {

	private final CadIdVar cadIdVar;
	private final TipoVar tipoVar;

	public ProcParam(CadIdVar cadIdVar, TipoVar tipoVar) {
		super();
		this.cadIdVar = cadIdVar;
		this.tipoVar = tipoVar;
	}

	public CadIdVar getCadIdVar() {
		return this.cadIdVar;
	}

	public TipoVar getTipoVar() {
		return this.tipoVar;
	}

	@Override
	public String toString() {
		return "ProcParam{" +
				"cadIdVar=" + cadIdVar +
				", tipoVar=" + tipoVar +
				'}';
	}
}