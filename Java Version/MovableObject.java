

public class MovableObject extends Object
{
    protected int weight;
    
	public MovableObject(String name, String description, int weight)
	{
		this.name = name;
		this.description = description;
		state = "movable";
		this.weight = weight;
	}
	
	public int getWeight()
	{
	    return weight;
	}
}