package compiler.syntax.nonTerminal;

public class ValorConst extends NonTerminal {

	private Integer value;
	private Boolean bool;

	public ValorConst(Integer value) {
		super();
		this.value = value;
	}

	public ValorConst(Boolean bool) {
		super();
		this.bool = bool;
	}

	public int getValue() {
		return this.value;
	}

	public Boolean getBoolean() {
		return this.bool;
	}

	public Boolean isBoolean() {
		return this.bool != null;
	}
}