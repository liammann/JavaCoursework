
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
    
    /**
     * This method gets the character's name
     * 
     * @return  the name of the character in string form
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * This method gets the chracter's state of health
     * 
     * @return  the health of the character
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * This method gets the chracter's amount of strength
     * 
     * @return  the strength of the character
     */
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