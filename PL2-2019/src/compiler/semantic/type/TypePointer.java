package compiler.semantic.type;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/**
 * Class for TypePointer.
 */

// TODO: Student work
//       Include properties to characterize pointer type

public class TypePointer
    extends TypeBase
{
    
    /**
     * Constructor for TypePointer.
     * @param scope The declaration scope.
     */
    public TypePointer (ScopeIF scope)
    {
        super (scope);
    }

    /**
     * Constructor for TypePointer.
     * @param scope The declaration scope
     * @param name The name of the type.
     */
    public TypePointer (ScopeIF scope, String name)
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
