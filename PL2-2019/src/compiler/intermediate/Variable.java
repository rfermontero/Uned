package compiler.intermediate;

import compiler.semantic.symbol.SymbolParameter;
import compiler.semantic.symbol.SymbolProcedure;
import compiler.semantic.symbol.SymbolVariable;
import es.uned.lsi.compiler.intermediate.VariableIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;

/**
 * Class for variables in intermediate code.
 */

public class Variable implements VariableIF {

	private String name;
	private ScopeIF scope;
	private SymbolIF symbol;
	private int offset;
	private boolean isParameter;
	private SymbolProcedure enclosingSymbol;

	/**
	 * Constructor for Variable.
	 *
	 * @param name  The name.
	 * @param scope The scope index.
	 */
	public Variable(String name, ScopeIF scope) {
		super();
		this.name = name;
		this.scope = scope;
		this.symbol = scope.getSymbolTable().getSymbol(name);
	}

	/**
	 * Constructor for Variable.
	 *
	 * @param name  The name.
	 * @param scope The scope index.
	 */
	public Variable(String name, SymbolIF symbol) {
		super();
		this.name = name;
		this.scope = symbol.getScope();
		this.symbol = symbol;
	}

	public Variable(SymbolIF symbol) {
		this(symbol.getName(), symbol.getScope());
		this.symbol = symbol;
	}

	/**
	 * Returns the name.
	 *
	 * @return Returns the name.
	 */
	@Override
	public final String getName() {
		return name;
	}

	/**
	 * Returns the scope.
	 *
	 * @return Returns the scope.
	 */
	@Override
	public final ScopeIF getScope() {
		return scope;
	}

	/**
	 * Returns the address.
	 *
	 * @return Returns the address.
	 */
	@Override
	public final int getAddress() {
		if (symbol instanceof SymbolVariable) {
			return ((SymbolVariable) symbol).getAddress();
		} else {
			return ((SymbolParameter) symbol).getAddress();
		}
	}

	/**
	 * @return the enclosingSymbol
	 */
	public SymbolProcedure getEnclosingSymbol() {
		return enclosingSymbol;
	}
	/**
	 * @param enclosingSymbol the enclosingSymbol to set
	 */
	public void setEnclosingSymbol(SymbolProcedure enclosingSymbol) {
		this.enclosingSymbol = enclosingSymbol;
	}

	/**
	 * Indicates whether the address is a global address.
	 *
	 * @return true if the address is a global address.
	 */
	@Override
	public final boolean isGlobal() {
		return scope.getLevel() == 0;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the isParameter
	 */
	public boolean isParameter() {
		return isParameter;
	}

	/**
	 * @param isParameter the isParameter to set
	 */
	public void setParameter(boolean isParameter) {
		this.isParameter = isParameter;
	}

	/**
	 * Compares this object with another one.
	 *
	 * @param other the other object.
	 * @return true if both objects has the same properties.
	 */
	@Override
	public final boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (this == other) {
			return true;
		}
		if (!(other instanceof Variable)) {
			return false;
		}

		final Variable aVariable = (Variable) other;
		return ((scope == null) ? (aVariable.scope == null) : (aVariable.scope.equals(scope))) &&
				((name == null) ? (aVariable.name == null) : (aVariable.name.equals(name)));
	}

	/**
	 * Returns a hash code for the object.
	 */
	@Override
	public final int hashCode() {
		return 31 * scope.hashCode() +
				((name == null) ? 0 : name.hashCode());
	}

	/**
	 * Return a string representing the object.
	 *
	 * @return a string representing the object.
	 */
	@Override
	public final String toString() {
		return name;
	}
}
