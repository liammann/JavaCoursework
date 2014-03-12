

public abstract class Object implements java.io.Serializable
{
	protected String name;
	protected String description;
	protected String state;

	public Object()
	{
		// meh
	}

	public String getObjectName()
	{
		return name;
	}

	public String getObjectDescription()
	{
		return description;
	}
	
	public String getName()
	{
	    return name;
	}
}