

public abstract class Object implements java.io.Serializable
{
    protected String name;
    protected String description;
    protected String state;

    public Object()
    {
        // meh
    }

    public String getObjectDescription()
    {
        return description;
    }

    public String getObjectName() //slight redundancy?
    {
        return name;
    }
    
    public String getName()
    {
        return name;
    }
}