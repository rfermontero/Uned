package compiler.syntax.nonTerminal;

import compiler.CompilerContext;
import es.uned.lsi.compiler.semantic.ScopeManagerIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolTableIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;
import es.uned.lsi.compiler.semantic.type.TypeTableIF;

import java.util.ArrayList;
import java.util.List;

public class Expresion extends NonTerminal {

	private ExpArit expArit;
	private ExprLogica exprLogica;
	private List<Expresion> expresionList = new ArrayList<>();
	private Variables variables;

	public Expresion(ExpArit expArit) {
		super();
		this.expArit = expArit;
	}

	public Expresion(Variables variables) {
		this.variables = variables;
	}

	public Expresion(ExprLogica exprLogica) {
		this.exprLogica = exprLogica;
	}

	public Expresion(Expresion expresion) {
		this.expresionList.add(expresion);
	}

	public void addExpresion(Expresion expresion) {
		this.expresionList.add(expresion);
	}

	public ExpArit getExpArit() {
		return expArit;
	}

	public ExprLogica getExprLogica() {
		return exprLogica;
	}

	public Variables getVariables() {
		return variables;
	}

	public List<Expresion> getExpresionList() {
		return this.expresionList;
	}

	@Override
	public String toString() {
		return "Expresion{" +
				"expArit=" + expArit +
				", exprLogica=" + exprLogica +
				", expresionList=" + expresionList +
				", variables=" + variables +
				'}';
	}
}