package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

public class ListSentencia extends NonTerminal {

	private final List<Sentencia> sentenciaList =  new ArrayList<>();

	public ListSentencia() {
	}

	public ListSentencia(Sentencia sentencia) {
		super();
		this.sentenciaList.add(sentencia);
	}

	public void addSentencia(Sentencia sentencia){
		this.sentenciaList.add(sentencia);
	}

	public List<Sentencia> getSentenciaList() {
		return sentenciaList;
	}
}