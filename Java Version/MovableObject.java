

public class MovableObject extends Object
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
	}	
	public MovableObject(String name, String description, int weight, boolean weapon, double weaponModifier)
	{
		this.name = name;
		this.description = description;
		state = "movable";
		this.weight = weight;		
		this.weapon = weapon;
		this.weaponModifier = weaponModifier;
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