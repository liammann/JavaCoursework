
public class FixedObject extends Object implements java.io.Serializable
{   
    /**
     * This constructor calls for the creation of a new fixed object
     * 
     * @param   name    the string for the value of the objects name attribute
     * @return          a new fixed object
     */
    public static FixedObject create(String name)
    {
        return new FixedObject(name);
    }
    
    /**
     * This constructor sets the name of the object
     * 
     * @param   name    the string for the value of the objects name attribute
     * @return          
     */
    private FixedObject(String name)
    {
        this.name = name;
    }
    
    /**
     * This method sets up the description of the fixed object
     * 
     * @param   description     the description of that the object is
     * @return                  the object
     */
    @Override
    public FixedObject withDescription(String description)
    {
        this.description = description;

        return this;
    }
}