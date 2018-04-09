package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SymbolConstant<T> extends SymbolBase {

    private final T value;

    /**
     * Constructor for SymbolConstant.
     *
     * @param scope The declaration scope.
     * @param name  The symbol name.
     * @param type  The symbol type.
     */
    public SymbolConstant(ScopeIF scope,
                          String name,
                          TypeIF type,
                          T value) {
        super(scope, name, type);
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
