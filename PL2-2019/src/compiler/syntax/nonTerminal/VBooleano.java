package compiler.syntax.nonTerminal;

public class VBooleano extends NonTerminal {

	private final Boolean value;

	public VBooleano(Boolean value) {
		super();
		this.value = value;
	}

	public Boolean getValue() {
		return this.value;
	}
}