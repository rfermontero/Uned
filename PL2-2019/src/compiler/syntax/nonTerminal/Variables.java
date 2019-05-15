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

	public IdArray getIdArray() {
		return idArray;
	}

	public ParFuncion getParFuncion() {
		return parFuncion;
	}

	public String getIdentificador() {
		return identificador;
	}

	@Override
	public String toString() {
		return "Variables{" +
				"idArray=" + idArray +
				", parFuncion=" + parFuncion +
				", identificador='" + identificador + '\'' +
				'}';
	}
}