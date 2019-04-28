package compiler.syntax.nonTerminal;

public class StmConstantes extends NonTerminal {

	private SentConst sentConst;

	public StmConstantes() {
		super();
	}

	public StmConstantes(SentConst sentConst) {
		super();
		this.sentConst = sentConst;
	}

	public SentConst getSentConst() {
		return this.sentConst;
	}
}