package compiler.semantic.type;

import es.uned.lsi.compiler.semantic.ScopeIF;

public class TypeFunction extends TypeProcedure {

    public TypeFunction(ScopeIF scope, String name) {
        super(scope, name);
    }

    /**
     * Returns the size of the type.
     *
     * @return the size of the type.
     */
    @Override
    public int getSize() {
        return 1;
    }
}
