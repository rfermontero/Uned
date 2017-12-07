package compiler.semantic.type;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/**
 * Class for TypeUnion.
 */

// TODO: Student work
//       Include properties to characterize unions

public class TypeUnion
    extends TypeBase
{   
       
    /**
     * Constructor for TypeUnion.
     * @param scope The scope.
     */
    public TypeUnion (ScopeIF scope)
    {
        super (scope);
    }

    /**
     * Constructor for TypeUnion.
     * @param scope The scope
     * @param name The name.
     */
    public TypeUnion (ScopeIF scope, String name)
    {
        super (scope, name);
    }

    /**
     * Constructor for TypeRecord.
     * @param record The record to copy.
     */
    public TypeUnion (TypeUnion record)
    {
        super (record.getScope (), record.getName ());
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
