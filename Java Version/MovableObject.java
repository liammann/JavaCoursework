public class MovableObject extends Object implements java.io.Serializable
{
    protected int weight;    
    protected boolean weapon;    
    protected double weaponModifier;
    
	public MovableObject(String name, String description, int weight)
	{
		this.name = name;
		this.description = description;
		state = "movable";
		this.weight = weight;
		//gameData.setObjectName(name, this); // What for? - A: look at CommandActions class
	}	
	public MovableObject(String name, String description, int weight, double weaponModifier)
	{
		this.name = name;
		this.description = description;
		state = "movable";
		this.weight = weight;		
		this.weapon = true;
		this.weaponModifier = weaponModifier;
		//gameData.setObjectName(name, this); // What this for?
	}
	
	public int getWeight()
	{
	    return weight;
	}	
	public double getWeaponModifier()
	{
	    return weaponModifier;
	}
	public boolean checkWeapon()
	{
	    return weapon;
	}
}