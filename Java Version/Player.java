
public class Player extends Character
{
    private MovableObject weapons;
    private Inventory inventory;

    public Player (String name, int strength, int health)
    {
        super(name, strength, health);
        this.inventory = new Inventory();
    }
    
    public String getName()
    {
        return name;
    }
    
    public int strength()
    {
        return strength;
    }
    
    public int health()
    {
        return health;
    }
    
    public Inventory getInventory()
    {
        return inventory;
    }
    

}