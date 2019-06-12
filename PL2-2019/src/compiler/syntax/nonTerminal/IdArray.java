package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.intermediate.TemporalIF;

public class IdArray extends NonTerminal {

	private final Expresion expresion;

	public IdArray(Expresion expresion) {
		super();
		this.expresion = expresion;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	@Override
	public TemporalIF getTemporal() {
		return expresion.getTemporal();
	}

	@Override
	public String toString() {
		return "IdArray{" +
				"expresion=" + expresion +
				'}';
	}
}