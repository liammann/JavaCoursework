
public class Player extends Character implements java.io.Serializable
{
    private MovableObject weapon;
    
    public Player (String name)
    {
        super(name);
        getInventory().addItemToInventory(weapon);
    }
    
    @Override
    public Player hasStrength(int strength)
    {
        this.strength = strength;

        return this;
    }
    
    @Override
    public Player withHealth(int health)
    {
        this.health = health;
        
        return this;
    }
    
    public Player andHasWeapon(MovableObject weapon)
    {
        this.weapon = weapon;

        return this;
    }

}