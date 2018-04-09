package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SymbolParameter extends SymbolBase {

    public SymbolParameter(ScopeIF scope,
                           String name,
                           TypeIF type) {
        super(scope, name, type);
    }
}
