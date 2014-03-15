/*
 * Why's this not an abstract class?
 */

public abstract class Character implements java.io.Serializable
{
    protected String name;
    protected int health; 
    protected int strength;
    private Inventory inventory;

    public Character(String name)
    {
        this.name = name;
        inventory = new Inventory();
    }
    
    abstract public Character hasStrength(int strength);
    abstract public Character withHealth(int health);
    
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

    public Inventory getInventory()
    {
        return inventory;
    }
}