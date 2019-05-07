package compiler.syntax.nonTerminal;

public class SentFor extends NonTerminal {

	private final String identificador;
	private final Expresion expresionFrom;
	private final Expresion expresionTo;
	private final Sentencias sentencias;

	public SentFor(String identificador, Expresion expresionFrom, Expresion expresionTo, Sentencias sentencias) {
		super();
		this.identificador = identificador;
		this.expresionFrom = expresionFrom;
		this.expresionTo = expresionTo;
		this.sentencias = sentencias;
	}

	public String getIdentificador() {
		return identificador;
	}

	public Expresion getExpresionFrom() {
		return expresionFrom;
	}

	public Expresion getExpresionTo() {
		return expresionTo;
	}

	public Sentencias getSentencias() {
		return sentencias;
	}
}