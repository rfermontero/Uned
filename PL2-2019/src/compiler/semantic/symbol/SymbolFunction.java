package compiler.semantic.symbol;

import compiler.syntax.nonTerminal.StmSubprogram;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolFunction.
 */

public class SymbolFunction extends SymbolProcedure {

	/**
	 * Constructor for SymbolFunction.
	 *
	 * @param scope The declaration scope.
	 * @param name  The symbol name.
	 * @param type  The symbol type.
	 */
	public SymbolFunction(ScopeIF scope,
	                      String name,
	                      StmSubprogram stmSubprogram,
	                      TypeIF type) {
		super(scope, name, stmSubprogram, type);
	}

	public StmSubprogram getStmSubprogram() {
		return super.getStmSubprogram();
	}
}
