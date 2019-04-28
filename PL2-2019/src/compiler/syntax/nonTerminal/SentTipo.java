package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

public class SentTipo extends NonTerminal {

	private final List<ExpTipo> expTipos;

	public SentTipo(ExpTipo expTipo) {
		super();
		expTipos = new ArrayList<>();
		expTipos.add(expTipo);
	}

	public void addExpTipo(ExpTipo expTipo) {
		expTipos.add(expTipo);
	}

	public List<ExpTipo> getExpTipos() {
		return expTipos;
	}
}