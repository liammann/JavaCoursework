
public class FixedObject extends Object implements java.io.Serializable
{	
	public static FixedObject create(String name)
	{
	    return new FixedObject(name);
	}
	
	private FixedObject(String name)
	{
		this.name = name;
	}
	
	@Override
	public FixedObject withDescription(String description)
	{
	    this.description = description;

	    return this;
	}
}