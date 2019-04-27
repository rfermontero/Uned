package compiler.syntax.nonTerminal;

public class ExpVar extends NonTerminal {

	private final CadIdVar cadIdVar;
	private final TipoVar tipoVar;

	public ExpVar(CadIdVar cadIdVar, TipoVar tipoVar) {
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
}