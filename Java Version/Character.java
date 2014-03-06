

public class Character
{
	private String name;
	private int health; 
	private Location currentLocation;
	private int strength;

	public Character(String name, int strength, int health)
	{
		this.name = name;
		this.strength = strength;
		this.health = health;
	}

	public int getHealth()
	{
		return health;
	}

	public int getStrength()
	{
		return strength;
	}

	public void updateHealth(int newHealth)
	{
		health = newHealth;
	}

	public void setLocation(Location current)
	{
		currentLocation = current;
	}

	public Location getLocation()
	{
		return currentLocation;
	}
}