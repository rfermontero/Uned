package compiler.syntax.nonTerminal;

import compiler.intermediate.Variable;

public class Variables extends NonTerminal {

	private IdArray idArray;
	private ParFuncion parFuncion;
	private String identificador;
	private Variable variable;

	public Variables(String identificador) {
		super();
		this.identificador = identificador;
	}

	public Variables(String identificador, ParFuncion parFuncion) {
		super();
		this.parFuncion = parFuncion;
		this.identificador = identificador;
	}

	public Variables(String identificador, IdArray idArray) {
		super();
		this.idArray = idArray;
		this.identificador = identificador;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
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

	public Variable getVariable(){
		return this.variable;
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