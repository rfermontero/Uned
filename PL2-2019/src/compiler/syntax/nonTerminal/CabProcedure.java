package compiler.syntax.nonTerminal;

public class CabProcedure extends NonTerminal {

	private final String identificador;
	private final ProcParenParam procParemParam;
	private final TipoRetorno tipoRetorno;

	public CabProcedure(String identificador, ProcParenParam procParenParam, TipoRetorno tipoRetorno) {
		super();
		this.identificador = identificador;
		this.procParemParam = procParenParam;
		this.tipoRetorno = tipoRetorno;
	}

	public String getIdentificador() {
		return identificador;
	}

	public ProcParenParam getProcParemParam() {
		return procParemParam;
	}

	public TipoRetorno getTipoRetorno() {
		return tipoRetorno;
	}
}