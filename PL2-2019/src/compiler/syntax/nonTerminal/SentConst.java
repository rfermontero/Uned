package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

public class SentConst extends NonTerminal {

	private final List<ExpConst> expConstList;

	public SentConst(ExpConst expConst) {
		super();
		this.expConstList = new ArrayList<>();
		this.expConstList.add(expConst);
	}

	public List<ExpConst> getExpConstList() {
		return this.expConstList;
	}

	public void addExpConst(ExpConst expConst) {
		this.expConstList.add(expConst);
	}
}