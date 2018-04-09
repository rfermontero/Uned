package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SymbolVariable<T> extends SymbolBase {

    private T value;

    /**
     * Constructor for SymbolVariable.
     *
     * @param scope The declaration scope.
     * @param name  The symbol name.
     * @param type  The symbol type.
     */
    public SymbolVariable(ScopeIF scope,
                          String name,
                          TypeIF type) {
        super(scope, name, type);
    }

    /**
     * Constructor for SymbolVariable.
     *
     * @param scope The declaration scope.
     * @param name  The symbol name.
     * @param type  The symbol type.
     */
    public SymbolVariable(ScopeIF scope,
                          String name,
                          TypeIF type,
                          T value) {
        super(scope, name, type);
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
