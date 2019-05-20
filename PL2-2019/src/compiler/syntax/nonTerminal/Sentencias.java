package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;

import java.util.ArrayList;
import java.util.List;

public class Sentencias extends NonTerminal {

	private ListSentencia listSentencia;

	public Sentencias() {
		super();
		this.listSentencia = new ListSentencia();
	}

	public Sentencias(ListSentencia listSentencia) {
		super();
		this.listSentencia = listSentencia;
	}

	@Override
	public List<QuadrupleIF> getIntermediateCode() {
		List<QuadrupleIF> quadruple = new ArrayList<>();
		for (Sentencia sentencia : listSentencia.getSentenciaList()) {
			quadruple.addAll(sentencia.getIntermediateCode());
		}
		return quadruple;
	}

	public ListSentencia getListSentencia() {
		return listSentencia;
	}
}