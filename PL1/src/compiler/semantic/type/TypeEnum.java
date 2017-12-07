package compiler.semantic.type;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/**
 * Class for TypeEnum.
 */

// TODO: Student work
//       Include properties to characterize enumeration type

public class TypeEnum
    extends TypeBase
{    
    
    /**
     * Constructor for TypeEnum.
     * @param scope The declaration scope.
     */
    public TypeEnum (ScopeIF scope)
    {
        super (scope);
    }

    /**
     * Constructor for TypeEnum.
     * @param scope The declaration scope
     * @param name The name of the type.
     */
    public TypeEnum (ScopeIF scope, String name)
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
