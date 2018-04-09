package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

import java.util.List;


public class SymbolFunction extends SymbolProcedure {

    private final List<SymbolParameter> parameters;
    private final TypeIF returnType;

    public SymbolFunction(ScopeIF scope,
                          String name,
                          TypeIF returnType,
                          List<SymbolParameter> parameters,
                          TypeIF type) {
        super(scope, name, type);
        this.parameters = parameters;
        this.returnType = returnType;
    }

    public TypeIF getReturnType() {
        return returnType;
    }

    public List<SymbolParameter> getParameters() {
        return parameters;
    }
}
