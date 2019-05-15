package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

public class CadIdVar extends NonTerminal {

	private final List<String> identificadores;

	public CadIdVar(String identificador) {
		super();
		this.identificadores = new ArrayList<>();
		this.identificadores.add(identificador);
	}

	public List<String> getIdentificadores() {
		return this.identificadores;
	}

	public void addIdentificador(String identificador) {
		this.identificadores.add(identificador);
	}

	@Override
	public String toString() {
		return "CadIdVar{" +
				"identificadores=" + identificadores +
				'}';
	}
}