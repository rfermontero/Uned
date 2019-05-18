package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.intermediate.LabelIF;

/**
 * Abstract Class for Axiom non terminal.
 */
public abstract class Axiom extends NonTerminal {

	private final CabModule cabModule;
	private final Cuerpo cuerpo;

	/**
	 * Constructor for Axiom.
	 */
	public Axiom(CabModule cabModule, Cuerpo cuerpo) {
		super();
		this.cabModule = cabModule;
		this.cuerpo = cuerpo;
	}

	@Override
	public String toString() {
		return "Axiom{" +
				"cabModule=" + cabModule +
				", cuerpo=" + cuerpo +
				'}';
	}

	public abstract void setLabel(LabelIF label);

	public abstract LabelIF getLabel();
}
