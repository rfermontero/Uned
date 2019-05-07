package compiler.syntax.nonTerminal;

public class SentElse extends NonTerminal {

	private Sentencias sentencias;

	public SentElse(Sentencias sentencias) {
		super();
		this.sentencias = sentencias;
	}

	public SentElse() {

	}

	public Sentencias getSentencias() {
		return sentencias;
	}
}