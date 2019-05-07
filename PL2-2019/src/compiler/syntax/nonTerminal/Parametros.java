package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

public class Parametros extends NonTerminal {

	private final List<Expresion> expresionList;

	public Parametros() {
		super();
		this.expresionList = new ArrayList<>();
	}

	public Parametros(Expresion expresion){
		this.expresionList = new ArrayList<>();
		this.expresionList.add(expresion);
	}

	public void addExpresion(Expresion expresion){
		expresionList.add(expresion);
	}

	public List<Expresion> getExpresionList() {
		return expresionList;
	}
}