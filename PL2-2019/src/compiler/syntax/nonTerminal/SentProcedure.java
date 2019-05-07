package compiler.syntax.nonTerminal;

public class SentProcedure extends NonTerminal {

	private final String identificador;
	private ParFuncion parFuncion;

	public SentProcedure(String identificador, ParFuncion parFuncion) {
		super();
		this.identificador = identificador;
		this.parFuncion = parFuncion;
	}

	public SentProcedure(String identificador) {
		super();
		this.identificador = identificador;
	}

	public String getIdentificador() {
		return identificador;
	}

	public ParFuncion getParFuncion() {
		return parFuncion;
	}
}