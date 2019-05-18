package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.intermediate.LabelIF;

/**
 * Class for Axiom non terminal.
 */
public class AxiomImpl extends Axiom {

	private LabelIF label;

	/**
	 * Constructor for Axiom.
	 */
	public AxiomImpl(CabModule cabModule, Cuerpo cuerpo) {
		super(cabModule, cuerpo);
	}

	public void setLabel(LabelIF label) {
		this.label = label;
	}

	public LabelIF getLabel() {
		return label;
	}

}
