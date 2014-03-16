
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
    
    /**
     * Return inventory object 
     * Used in fight method for getting enemies inventories 
     */
    public Inventory getInventory()
    {
        return inventory;
    }
}