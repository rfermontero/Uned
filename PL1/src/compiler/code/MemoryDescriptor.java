package compiler.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import es.uned.lsi.compiler.code.MemoryDescriptorIF;

/**
 * Class for the Memory Descriptors.
 */

public class MemoryDescriptor 
    implements MemoryDescriptorIF 
{
    private final static String PREFIX_VALUE    = "#";
    private final static String PREFIX_REGISTER = ".";
    private final static String PREFIX_MEMORY   = "/";
    
    private Map<String, Set<String>> places;
    
    /**  
     * Constructor for MemoryDescriptor.
     */
    public MemoryDescriptor ()
    {
        super ();
        this.places = new HashMap<String, Set<String>> ();
    }
    
    /**
     * Returns the suitest place for the data holder.
     * @param dataholder the data holder.
     * @return the suitest place for the data holder.
     */
    @Override
    public final String getPlace (String dataHolder)
    {
        List<String> places = getPlaces (dataHolder);
        if (places == null) return null;        
        return getPlaces (dataHolder).get (0);
    }

    /**
     * Returns the places for the data holder.
     * @param dataholder the data holder.
     * @return the places for the data holder.
     */
    @Override
    public final List<String> getPlaces (String dataholder)
    {
        List<String> dPlaces = new ArrayList<String> ();
        Set<String>  aPlaces = places.get (dataholder);
        if (aPlaces == null) return dPlaces;
        
        Set<String> values    = new HashSet<String> ();
        Set<String> registers = new HashSet<String> ();
        Set<String> addresses = new HashSet<String> ();
        for (String aPlace : aPlaces) {
            if (aPlace.startsWith (PREFIX_VALUE))    values.add (aPlace);
            if (aPlace.startsWith (PREFIX_REGISTER)) registers.add (aPlace);
            if (aPlace.startsWith (PREFIX_MEMORY))   addresses.add (aPlace);
        }
        dPlaces.addAll (values);
        dPlaces.addAll (registers);
        dPlaces.addAll (addresses);
        return dPlaces;
    }

    /**
     * Adds a new place for the data holder.
     * @param dataholder the data holder.
     * @param place the place to add.
     */
    @Override
    public final void addPlace (String dataholder, String place)
    {
        Set<String> aPlaces = places.get (dataholder);
        if (aPlaces == null) {
            aPlaces = new HashSet<String> ();
            places.put (dataholder, aPlaces);
        }
        if ((place.startsWith (PREFIX_VALUE))    ||
            (place.startsWith (PREFIX_REGISTER)) ||
            (place.startsWith (PREFIX_MEMORY)))
            aPlaces.add (place);
    }

    /**
     * Removes a place for the data holder.
     * @param dataholder the data holder.
     * @param place The place to remove.
     */
    @Override
    public final void removePlace (String dataholder, String place)
    {
        Set<String> aPlaces = places.get (dataholder);
        if (aPlaces != null) {
            aPlaces.remove (place);
            if (aPlaces.isEmpty ())
                places.remove (dataholder);            
        }
    }
    
    /**
     * Removes all places for the data holder.
     * @param dataholder the data holder.
     */
    @Override
    public final  void clearPlaces (String dataholder)
    {
        places.remove (dataholder);
    }

    /**
     * Indicates whether a place store a data holder.
     * @param dataholder the data holder.
     * @param place the place.
     * @return true if the place store a data holder.
     */
    @Override
    public final boolean containsPlace (String dataholder, String place)
    {
        return places.containsKey (dataholder);
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
        if (!(other instanceof MemoryDescriptor))
        {
            return false;
        }
        
        final MemoryDescriptor aMemoryDescriptor = (MemoryDescriptor) other;
        return places.equals (aMemoryDescriptor.places);
       }

    /**
     * Returns a hash code for the object.
     */
    @Override
    public final int hashCode ()
    {
        return 31 * places.hashCode ();
    }
    
    /**
     * Returns a string representing the object.
     * @return a string representing the object.
     */
    @Override
    public final String toString ()
    {        
        StringBuffer strBuffer = new StringBuffer ();
        strBuffer.append ("DescriptorMemory - [");
        strBuffer.append ("places = ");
        strBuffer.append (places);
        strBuffer.append ("]");
        
        return strBuffer.toString();
    }
}
