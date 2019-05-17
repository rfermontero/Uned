package compiler.semantic.type;

import compiler.syntax.nonTerminal.ExpTipo;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/**
 * Class for TypeArray.
 */

public class TypeArray extends TypeBase {

	private final ExpTipo expTipo;

	/**
	 * Constructor for TypeArray.
	 *
	 * @param scope The declaration scope.
	 * @param name  The name of the type.
	 */
	public TypeArray(ScopeIF scope, String name, ExpTipo expTipo) {
		super(scope, name);
		this.expTipo = expTipo;
	}

	/**
	 * Returns the size of the type.
	 *
	 * @return the size of the type.
	 */
	@Override
	public int getSize() {
		return expTipo.getRigth().getValue();
	}

	public ExpTipo getExpTipo() {
		return expTipo;
	}
}
