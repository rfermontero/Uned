package compiler.code;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import es.uned.lsi.compiler.code.RegisterDescriptorIF;
import es.uned.lsi.compiler.intermediate.OperandIF;

/**
 * Class for the Register Descriptors.
 */

public class RegisterDescriptor 
    implements RegisterDescriptorIF
{
    private Map<String, Set<OperandIF>> descriptor;
    
    /**
     * Constructor for RegisterDescriptor.
     */
    public RegisterDescriptor ()
    {
        super ();
        this.descriptor = new HashMap< String, Set<OperandIF>> ();
    }

    /**
     * Returns the data holders for a register.
     * @param register the register.
     * @return the data holders for a register.
     */
    @Override
    public final Set<OperandIF> getDataHolders (String register)
    {
        return descriptor.get (register);
    }

    /**
     * Adds a new data holder to a register.
     * @param register the register.
     * @param holder the data holder.
     */
    @Override
    public final void addDataHolder (String register, OperandIF holder)
    {
        Set<OperandIF> holders = descriptor.get (register);
        if (holders == null) {
            holders = new HashSet<OperandIF> ();
            descriptor.put (register, holders);
        }          
        holders.add (holder);
    }

    /**
     * Remove a data holder from a register.
     * @param register the register.
     * @param holder the data holder.
     */
    @Override
    public final void removeDataHolder (String register, OperandIF holder)
    {
        Set<OperandIF> holders = descriptor.get (register);
        if (holders == null) return;         
        holders.remove (holder);
    }
    
    /**
     * Remove all data holder from a register.
     * @param register the register.
     */
    @Override
    public final void clearDataHolders (String register)
    {
        descriptor.remove (register);
    }

    /**
     * Indicates whether a holder is contained in a register.
     * @param register the register.
     * @param holder the data holder. 
     * @return true if the holder is contained in a register.
     */
    @Override
    public final boolean containsDataHolder (String register, OperandIF holder)
    {
        return getDataHolders (register).contains (holder);
    }
    
    /**
     * Compares this object with another one.
     * @param other the other object.
     * @return true if both objects are equals.
     */
    @Override
    public final boolean equals (Object other)
    {
        if (this == null)
        {
            return false;
        }
        if (this == other)
        {
            return true;
        }
        if (!(other instanceof RegisterDescriptor))
        {
            return false;
        }
        
        final RegisterDescriptor aDescriptorRegister = (RegisterDescriptor) other;
        return descriptor.equals (aDescriptorRegister.descriptor);
       }

    /**
     * Returns a hash code for the object.
     */
    @Override
    public final int hashCode ()
    {
        return 31 * descriptor.hashCode ();
    }
    
    /**
     * Returns a string representing the object.
     * @return a string representing the object.
     */
    @Override
    public final String toString ()
    {        
        StringBuffer strBuffer = new StringBuffer ();
        strBuffer.append ("DescriptorRegister - [");
        strBuffer.append ("descriptor = ");
        strBuffer.append (descriptor);
        strBuffer.append ("]");
        
        return strBuffer.toString();
    }   
}
