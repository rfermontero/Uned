package compiler.semantic.type;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/**
 * Class for TypeSet.
 */

// TODO: Student work
//       Include properties to characterize sets

public class TypeSet
    extends TypeBase
{
    
    /**
     * Constructor for TypeSet.
     * @param scope The declaration scope.
     */
    public TypeSet (ScopeIF scope)
    {
        super (scope);
    }

    /**
     * Constructor for TypeSet.
     * @param scope The declaration scope.
     * @param name The name of the type.
     */
    public TypeSet (ScopeIF scope, String name)
    {
        super (scope, name);
    }
    
    /**
     * Returns the size of the type.
     * @return the size of the type.
     */
    @Override
    public int getSize ()
    {
        // TODO: Student work
        return 1;
    }
}
