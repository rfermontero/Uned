package compiler.intermediate;

import es.uned.lsi.compiler.intermediate.LabelIF;

/**
 * Class for Labels within intermediate code.
 */

public class Label
    implements LabelIF 
{
    private String name = null;
    
    /**
     * Constructor for Label.
     * @param name The name.
     */
    public Label (String name)
    {
        this.name = name;
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
        if (!(other instanceof Label))
        {
            return false;
        }
        
        final Label aLabel = (Label) other;
        return ((name == null) ? aLabel.name == null : name.equals (aLabel.name));
    }

    /**
     * Returns a hash code for the object.
     */
    @Override
    public final int hashCode ()
    {
        return 31 * ((name == null)? 0 : name.hashCode ());
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
