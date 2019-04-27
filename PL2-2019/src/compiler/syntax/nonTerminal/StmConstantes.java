package compiler.syntax.nonTerminal;

public class StmConstantes extends NonTerminal {

	private final SentConst sentConst;

	public StmConstantes(SentConst sentConst) {
		super();
		this.sentConst = sentConst;
	}

	public SentConst getSentConst() {
		return this.sentConst;
	}
}