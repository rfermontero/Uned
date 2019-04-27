package compiler.syntax.nonTerminal;

public class Variables extends NonTerminal {

	private IdArray idArray;
	private ParFuncion parFuncion;
	private String identificador;

	public Variables(String identificador) {
		super();
		this.identificador = identificador;
	}

	public Variables(ParFuncion parFuncion) {
		super();
		this.parFuncion = parFuncion;
	}

	public Variables(IdArray idArray) {
		super();
		this.idArray = idArray;
	}
}