package compiler.intermediate;

import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.semantic.ScopeIF;

/**
 * Class for temporal variables within the intermediate code.
 */

public class Temporal
    implements TemporalIF 
{
    private String  name    = null;
    private ScopeIF scope   = null;
    private int     address = 0;
           
    /**
     * Constructor for Temporal.
     * @param name The name.
     * @param scope The creation scope.
     */
    public Temporal (String name, 
                     ScopeIF scope)
    {
        super ();
        this.name = name;
        this.scope = scope;
    }
    
    
    /**
     * Constructor for Temporal.
     * @param name The name.
     * @param scope The creation scope.
     * @param address The address.
     */
    public Temporal (String name, 
                     ScopeIF scope,
                     int address)
    {
        this (name, scope);
        this.address = address;
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
     * Returns the creation scope.
     * @return Returns a scope.
     */
    @Override
    public final ScopeIF getScope ()
    {
        return scope;
    }

    /**
     * Returns the address.
     * @return Returns the address.
     */
    @Override
    public final int getAddress ()
    {
        return address;
    }

    /**
     * Sets The address.
     * @param address The address to set.
     */
    @Override
    public final void setAddress (int address)
    {
        this.address = address;
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
        if (!(other instanceof Variable))
        {
            return false;
        }
        
        final Temporal aTemporal = (Temporal) other;
        return ((scope == null) ? (aTemporal.scope == null) : (aTemporal.scope.equals (scope))) && 
               ((name  == null) ? (aTemporal.name  == null) : (aTemporal.name.equals (name)));
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
