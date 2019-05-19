package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

import java.util.ArrayList;
import java.util.List;

public class ListSentencia extends NonTerminal {

	private final List<Sentencia> sentenciaList = new ArrayList<>();

	public ListSentencia() {
		super();
	}

	public ListSentencia(Sentencia sentencia) {
		super();
		this.sentenciaList.add(sentencia);
	}

	public void addSentencia(Sentencia sentencia) {
		this.sentenciaList.add(sentencia);
	}		


	public List<Sentencia> getSentenciaList() {
		return sentenciaList;
	}

	@Override
	public List<QuadrupleIF> getIntermediateCode() {
		List<QuadrupleIF> listaQuadruples = new ArrayList<>();
		for (Sentencia sentencia : sentenciaList) {
			listaQuadruples.addAll(sentencia.getIntermediateCode());
		}
		return listaQuadruples;
	}
}