package compiler.semantic.type;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/**
 * Class for TypeProcedure.
 */

public class TypeProcedure extends TypeBase {

	private int size;

	/**
	 * Constructor for TypeProcedure.
	 *
	 * @param scope The declaration scope.
	 */
	public TypeProcedure(ScopeIF scope) {
		super(scope);
		this.size = 4;
	}

	/**
	 * Constructor for TypeProcedure.
	 *
	 * @param scope The declaration scope
	 * @param name  The name of the procedure.
	 */
	public TypeProcedure(ScopeIF scope, String name) {
		super(scope, name);
		this.size = 4;
	}

	/**
	 * Returns the size of the type.
	 *
	 * @return the size of the type.
	 */
	@Override
	public int getSize() {
		return size;
	}

	public void setSize(int size){
		this.size = size;
	}

	public void incSize(int size){
		this.size+=size;
	}
}
