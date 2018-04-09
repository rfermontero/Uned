package compiler.intermediate;

import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.ProcedureIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

/**
 * Class for procedures and functions in intermediate code.
 */

public class Procedure
    implements ProcedureIF 
{
    private String  name     = null;
    private ScopeIF scope    = null;
        
    /**
     * Constructor for Variable.
     * @param name The name.
     * @param scope The scope index.
     */
    public Procedure (String name, ScopeIF scope)
    {
        super ();
        this.name = name;
        this.scope = scope;
    }

    /**
     * Returns the name.
     * @return Returns the name.
     */
    @Override
    public final String getName ()
    {
        return name;
    }

    /**
     * Returns the scope.
     * @return Returns the scope.
     */
    @Override
    public final ScopeIF getScope ()
    {
        return scope;
    }

    /**
     * Returns the code label.
     * @return Returns the code label.
     */
    @Override
    public final LabelIF getCodeLabel ()
    {
        // TODO : Student Work
        return null;
    }

    /**
     * Compares this object with another one.
     * @param other the other object.
     * @return true if both objects has the same properties.
     */
    @Override
    public final boolean equals (Object other)
    {
        if (other == null) 
        {
            return false;
        }
        if (this == other)
        {
            return true;
        }
        if (!(other instanceof Procedure))
        {
            return false;
        }
        
        final Procedure aVariable = (Procedure) other;
        return ((scope   == null) ? (aVariable.scope == null) : (aVariable.scope.equals (scope))) && 
               ((name    == null) ? (aVariable.name  == null) : (aVariable.name.equals (name)));
    }

    /**
     * Returns a hash code for the object.
     */
    @Override
    public final int hashCode ()
    {
        return 31 * scope.hashCode() +
                  ((name == null)? 0 : name.hashCode ());
    } 

    /**
     * Return a string representing the object.
     * @return a string representing the object.
     */
    @Override
    public final String toString ()
    {    
        return name;
    }
}
