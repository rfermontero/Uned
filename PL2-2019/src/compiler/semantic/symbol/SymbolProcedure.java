package compiler.semantic.symbol;

import compiler.syntax.nonTerminal.StmSubprogram;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolProcedure.
 */

// TODO: Student work
//       Include properties to characterize procedure calls

public class SymbolProcedure
		extends SymbolBase {

	private final StmSubprogram stmSubprogram;

	/**
	 * Constructor for SymbolProcedure.
	 *
	 * @param scope The declaration scope.
	 * @param name  The symbol name.
	 * @param type  The symbol type.
	 */
	public SymbolProcedure(ScopeIF scope,
	                       String name,
	                       StmSubprogram stmSubprogram,
	                       TypeIF type) {
		super(scope, name, type);
		this.stmSubprogram = stmSubprogram;
	}

	public StmSubprogram getStmSubprogram() {
		return stmSubprogram;
	}
}
