package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

public class SentVar extends NonTerminal {

	private final List<ExpVar> expVars;

	public SentVar(ExpVar expVar) {
		super();
		expVars = new ArrayList<>();
		expVars.add(expVar);
	}

	public void addExpVar(ExpVar expVar) {
		this.expVars.add(expVar);
	}

	public List<ExpVar> getExpVars() {
		return expVars;
	}
}