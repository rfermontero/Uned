package compiler.syntax.nonTerminal;

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
}
