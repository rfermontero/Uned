package compiler.syntax.nonTerminal;

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

	public ListSentencia getListSentencia() {
		return listSentencia;
	}
}