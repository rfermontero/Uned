package compiler.syntax.nonTerminal;

public class StmVar extends NonTerminal {

	private SentVar sentVar;

	public StmVar() {
		super();
	}

	public StmVar(SentVar sentVar) {
		super();
		this.sentVar = sentVar;
	}

	public SentVar getSentVar() {
		return this.sentVar;
	}
}