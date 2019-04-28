package compiler.syntax.nonTerminal;

public class StmTipos extends NonTerminal {

	private SentTipo sentTipo;

	public StmTipos() {
		super();
	}

	public StmTipos(SentTipo sentTipo) {
		super();
		this.sentTipo = sentTipo;
	}

	public SentTipo getSentTipo() {
		return sentTipo;
	}
}