
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
    
    /**
     * This method sets the health of the character
     * 
     * @param   newHealth   the new amount of health
     * @return  void
     */
    public void updateHealth(int newHealth)
    {
        health = newHealth;
    }
    
    /**
     * This method returns the character's inventory
     * 
     * @return  the list of objects in their inventory
     */
    public Inventory getInventory()
    {
        return inventory;
    }
}