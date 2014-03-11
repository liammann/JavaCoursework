
public class Player extends Character
{
    private MovableObject weapons;

    public Player (String name, int strength, int health)
    {
        super(name, strength, health);

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