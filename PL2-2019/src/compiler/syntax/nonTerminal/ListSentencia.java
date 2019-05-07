package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

public class ListSentencia extends NonTerminal {

	private final List<Sentencia> sentenciaList;

	public ListSentencia(Sentencia sentencia) {
		super();
		this.sentenciaList = new ArrayList<>();
		this.sentenciaList.add(sentencia);
	}

	public void addSentencia(Sentencia sentencia){
		this.sentenciaList.add(sentencia);
	}
}