package compiler.syntax.nonTerminal;

public class ExprLogica extends NonTerminal {

	private Expresion expresionl;
	private String operacion;
	private Expresion expresionr;
	private VBooleano vBooleano;

	public ExprLogica(VBooleano vBooleano) {
		this.vBooleano = vBooleano;
	}

	public ExprLogica(Expresion expresionl, String op, Expresion expresionr) {
		super();
		this.expresionl = expresionl;
		this.operacion = op;
		this.expresionr = expresionr;
	}

	public ExprLogica(Expresion expresionl, String op) {
		super();
		this.expresionl = expresionl;
		this.operacion = op;
	}

	public Boolean getFinalValue() {
		if (vBooleano != null) {
			return vBooleano.getValue();
		} else {
			switch (operacion) {
				case ">":
					return (int) expresionl.getValue() > (int) expresionr.getValue();
				case "=":
					return (int) expresionl.getValue() == (int) expresionr.getValue();
				case "OR":
					return (boolean) expresionl.getValue() || (boolean) expresionr.getValue();
				case "NOT":
					return (boolean) expresionl.getValue() || (boolean) expresionr.getValue();
				default:
					throw new RuntimeException("Error de tipos");
			}
		}

	}

	public Expresion getExpresionl() {
		return expresionl;
	}

	public String getOperacion() {
		return operacion;
	}

	public Expresion getExpresionr() {
		return expresionr;
	}

	public VBooleano getvBooleano() {
		return vBooleano;
	}
}