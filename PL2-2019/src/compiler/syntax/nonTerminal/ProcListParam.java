package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

public class ProcListParam extends NonTerminal {

	private final List<ProcParam> procParam;

	public ProcListParam(ProcParam procParam) {
		super();
		this.procParam = new ArrayList<>();
		this.procParam.add(procParam);
	}

	public List<ProcParam> getProcListParam() {
		return this.procParam;
	}

	public void addProcParam(ProcParam procParam) {
		this.procParam.add(procParam);
	}
}