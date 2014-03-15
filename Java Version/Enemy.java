public class Enemy extends Character implements java.io.Serializable
{
    public Enemy(String name)
    {
        super(name);
    }
    
    @Override
    public Enemy hasStrength(int strength)
    {
        this.strength = strength;

        return this;
    }
    
    @Override
    public Enemy withHealth(int health)
    {
        this.health = health;
        return this;
    }
    
    public Enemy andHasWeapon(MovableObject weapon)
    {
        getInventory().addItemToInventory(weapon);
        return this;
    }
}