package compiler.syntax.nonTerminal;

public class CabModule extends NonTerminal {

	private final String identifier;

	public CabModule(String identifier) {
		super();
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return this.identifier;
	}
}