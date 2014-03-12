

public class FixedObject extends Object implements java.io.Serializable
{
	public FixedObject(String name, String description)
	{
		this.name = name;
		this.description = description;
		state = "fixed";
	}
}