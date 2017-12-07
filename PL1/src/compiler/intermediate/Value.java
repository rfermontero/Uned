package compiler.intermediate;

import es.uned.lsi.compiler.intermediate.ValueIF;

/**
 * Class for literal values within intermediate code.
 */

public class Value
    implements ValueIF 
{
    private Object value;
      
    /**
     * Constructor for Value.
     * @param value The value.
     */
    public Value (Object value)
    {
        super ();
        this.value = value;
    }

    /**
     * Returns the value.
     * @return Returns the value.
     */
    @Override
    public final Object getValue ()
    {
        return value;
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
        if (!(other instanceof Value))
        {
            return false;
        }
        
        final Value aValue = (Value) other;
        return (value.equals (aValue.value));
    }

    /**
     * Returns a hash code for the object.
     */
    @Override
    public final int hashCode ()
    {
        return 31 * value.hashCode ();
    } 

    /**
     * Return a string representing the object.
     * @return a string representing the object.
     */
    @Override
    public final String toString ()
    {        
        return value.toString();
    }
}
