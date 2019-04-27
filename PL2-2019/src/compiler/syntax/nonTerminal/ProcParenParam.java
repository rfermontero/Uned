package compiler.syntax.nonTerminal;


public class ProcParenParam extends NonTerminal {

	private final ProcListParam procListParam;

	public ProcParenParam(ProcListParam procListParam) {
		super();
		this.procListParam = procListParam;
	}

	public ProcListParam getProcListParam() {
		return this.procListParam;
	}
}