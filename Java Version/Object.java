public abstract class Object implements java.io.Serializable
{
    protected String name;
    protected String description;
    protected String state;

    abstract public Object withDescription(String description);

    public String getObjectDescription()
    {
        return description;
    }

    public String getName()
    {
        return name;
    }
}