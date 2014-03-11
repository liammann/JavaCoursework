

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
		gameData.setObjectName(name, this);
	}	
	public MovableObject(String name, String description, int weight, double weaponModifier)
	{
		this.name = name;
		this.description = description;
		state = "movable";
		this.weight = weight;		
		this.weapon = true;
		this.weaponModifier = weaponModifier;
		gameData.setObjectName(name, this);
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