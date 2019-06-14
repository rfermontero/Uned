package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

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

	public List<ProcParam> getProcParams(){
		List<ProcParam> params = new ArrayList<>();
		for(ProcParam procParam : procListParam.getProcListParam()){
			params.add(procParam);
		}
		return params;
	}

	@Override
	public String toString() {
		return "ProcParenParam{" +
				"procListParam=" + procListParam +
				'}';
	}
}