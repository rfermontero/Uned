package compiler.semantic.type;

import compiler.CompilerContext;

import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/**
 * Class for TypeSimple.
 */

public class TypeSimple extends TypeBase {

    /**
     * Constructor for TypeSimple.
     *
     * @param scope The declaration scope.
     */
    public TypeSimple(ScopeIF scope, String name) {
        super(scope, name);
    }

    /**
     * Returns the size of the type.
     *
     * @return the size of the type.
     */
    @Override
    public int getSize() {
        ExecutionEnvironmentIF environment = CompilerContext.getExecutionEnvironment();
        return environment.getTypeSize(this);
    }

}
