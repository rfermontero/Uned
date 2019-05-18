package compiler.semantic.symbol;

import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolVariable.
 */

public class SymbolParameter extends SymbolBase {

	private int address;
	private TemporalIF temporal;
	private SymbolProcedure enclosingSymbol;

	/**
	 * Constructor for SymbolParameter.
	 *
	 * @param scope The declaration scope.
	 * @param name  The symbol name.
	 * @param type  The symbol type.
	 */
	public SymbolParameter(ScopeIF scope,
	                       String name,
	                       TypeIF type) {
		super(scope, name, type);
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public TemporalIF getTemporal() {
		return temporal;
	}

	public void setTemporal(TemporalIF temporal) {
		this.temporal = temporal;
	}

	public SymbolProcedure getEnclosingSymbol() {
		return enclosingSymbol;
	}

	public void setEnclosingSymbol(SymbolProcedure enclosingSymbol) {
		this.enclosingSymbol = enclosingSymbol;
	}
}
