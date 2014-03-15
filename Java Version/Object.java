public abstract class Object implements java.io.Serializable
{
    protected String name;
    protected String description;
    protected String state;

    public String getObjectDescription()
    {
        return description;
    }

    abstract public Object withDescription(String description);

/**/
    public String getObjectName() //slight redundancy?
    {
        return name;
    }
    
    public String getName()
    {
        return name;
    }
}