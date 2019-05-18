package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolVariable.
 */

public class SymbolVariable extends SymbolBase {

	private Integer address;
	private int value;
	private SymbolProcedure enclosingSymbol = null;

	public SymbolVariable(ScopeIF scope,
	                      String name,
	                      TypeIF type) {
		super(scope, name, type);
	}

	public void setAddress(Integer address) {
		this.address = address;
	}

	public Integer getAddress() {
		return address;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public SymbolProcedure getEnclosingSymbol() {
		return enclosingSymbol;
	}

	public void setEnclosingSymbol(SymbolProcedure enclosingSymbol) {
		this.enclosingSymbol = enclosingSymbol;
	}

}
