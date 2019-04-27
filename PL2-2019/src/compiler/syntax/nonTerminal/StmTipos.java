package compiler.syntax.nonTerminal;

public class StmTipos extends NonTerminal {

	private final SentTipo sentTipo;

	public StmTipos(SentTipo sentTipo) {
		super();
		this.sentTipo = sentTipo;
	}

	public SentTipo getSentTipo() {
		return sentTipo;
	}
}