package compiler.semantic.symbol;

import compiler.syntax.nonTerminal.CabProcedure;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolProcedure.
 */

public class SymbolProcedure extends SymbolBase {

	private final CabProcedure cabProcedure;

	/**
	 * Constructor for SymbolProcedure.
	 *
	 * @param scope The declaration scope.
	 * @param name  The symbol name.
	 * @param type  The symbol type.
	 */
	public SymbolProcedure(ScopeIF scope,
	                       String name,
	                       CabProcedure cabProcedure,
	                       TypeIF type) {
		super(scope, name, type);
		this.cabProcedure = cabProcedure;
	}

	public CabProcedure getCabProcedure() {
		return cabProcedure;
	}
}
