package compiler.syntax.nonTerminal;

public class ExpConst extends NonTerminal {

	private final String identificador;
	private final ValorConst valorConst;

	public ExpConst(String identificador, ValorConst valorConst) {
		super();
		this.identificador = identificador;
		this.valorConst = valorConst;
	}

	public String getIdentificador() {
		return this.identificador;
	}

	public ValorConst getValorConst() {
		return this.valorConst;
	}
}