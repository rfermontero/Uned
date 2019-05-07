package compiler.syntax.nonTerminal;


public class ProcParenParam extends NonTerminal {

	private ProcListParam procListParam;

	public ProcParenParam() {
		super();
		this.procListParam = new ProcListParam();
	}

	public ProcParenParam(ProcListParam procListParam) {
		super();
		this.procListParam = procListParam;
	}

	public ProcListParam getProcListParam() {
		return this.procListParam;
	}
}