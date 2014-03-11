/*
 * Why's this not an abstract class?
 */

public class Character
{
    protected String name;
    protected int health; 
    protected Location currentLocation;
    protected int strength;
    protected Inventory inventory;

    public Character(String name, int strength, int health)
    {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.inventory = new Inventory();
    }
    
    public String getName()
    {
        return name;
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