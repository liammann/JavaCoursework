public abstract class Object implements java.io.Serializable
{
    protected String name;
    protected String description;

    abstract public Object withDescription(String description);
    
    /**
     * This method gets the string description of the object
     * 
     * @return  the description of the object
     */
    public String getObjectDescription()
    {
        return description;
    }
    
    /**
     * This method gets the name of the object
     * 
     * @return  the description of the object
     */
    public String getName()
    {
        return name;
    }
}
