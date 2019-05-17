package compiler.syntax.nonTerminal;

public class ExpArit extends NonTerminal {

	private Integer value;
	private Expresion expresionl;
	private String operacion;
	private Expresion expresionr;

	public ExpArit(Expresion expresionl, String operacion, Expresion expresionr) {
		super();
		this.expresionl = expresionl;
		this.operacion = operacion;
		this.expresionr = expresionr;
	}

	public ExpArit(Integer value) {
		this.value = value;
	}

	public Integer getFinalValue() {
		if (value != null) {
			return value;
		} else {
			if (operacion.equals("-")) {
				return (int) expresionl.getValue() - (int) expresionr.getValue();
			} else {
				return (int) expresionl.getValue() * (int) expresionr.getValue();
			}
		}
	}

	public Integer getValue() {
		return value;
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

	@Override
	public String toString() {
		return "ExpArit{" +
				"value=" + value +
				", expresionl=" + expresionl +
				", operacion='" + operacion + '\'' +
				", expresionr=" + expresionr +
				'}';
	}
}