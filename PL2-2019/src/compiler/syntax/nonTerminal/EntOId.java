package compiler.syntax.nonTerminal;

public class EntOId extends NonTerminal {

	private String identificador;
	private Integer value;

	public EntOId(int value) {
		super();
		this.value = value;
	}

	public EntOId(String identificador) {
		super();
		this.identificador = identificador;
	}

	public String getIdentificador() {
		return identificador;
	}

	public Integer getValue() {
		return value;
	}
}