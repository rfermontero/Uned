package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

public class Parametros extends NonTerminal {

	private final List<Expresion> expresionList  = new ArrayList<>();

	public Parametros() {
	}

	public Parametros(Expresion expresion){
		this.expresionList.add(expresion);
	}

	public void addExpresion(Expresion expresion){
		expresionList.add(expresion);
	}

	public List<Expresion> getExpresionList() {
		return expresionList;
	}

	@Override
	public String toString() {
		return "Parametros{" +
				"expresionList=" + expresionList +
				'}';
	}
}