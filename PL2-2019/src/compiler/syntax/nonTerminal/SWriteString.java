package compiler.syntax.nonTerminal;

public class SWriteString extends NonTerminal {

	private String string;

	public SWriteString(String string) {
		super();
		this.string = string;
	}

	public String getString() {
		return string;
	}
}