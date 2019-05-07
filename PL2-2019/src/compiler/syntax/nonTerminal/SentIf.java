package compiler.syntax.nonTerminal;

public class SentIf extends NonTerminal {

	private final Expresion expresion;
	private final Sentencias sentencias;
	private final SentElse sentElse;

	public SentIf(Expresion expresion, Sentencias sentencias, SentElse sentElse) {
		super();
		this.expresion = expresion;
		this.sentencias = sentencias;
		this.sentElse = sentElse;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public Sentencias getSentencias() {
		return sentencias;
	}

	public SentElse getSentElse() {
		return sentElse;
	}
}