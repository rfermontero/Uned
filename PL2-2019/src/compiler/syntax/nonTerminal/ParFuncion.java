package compiler.syntax.nonTerminal;

public class ParFuncion extends NonTerminal {

	private Parametros parametros;

	public ParFuncion() {
		super();
	}

	public ParFuncion(Parametros parametros) {
		super();
		this.parametros = parametros;
	}

	public Parametros getParametros() {
		return parametros;
	}

	@Override
	public String toString() {
		return "ParFuncion{" +
				"parametros=" + parametros +
				'}';
	}
}