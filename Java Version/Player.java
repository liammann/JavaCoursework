
public class Player extends Character implements java.io.Serializable
{
    public Player(String name)
    {
        super(name);
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
        getInventory().addItemToInventory(weapon);
        return this;
    }
}