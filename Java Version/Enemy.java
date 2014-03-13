public class Enemy extends Character implements java.io.Serializable
{
    private MovableObject weapon;

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
    
    public Enemy andHasWeapon(String weaponName, MovableObject weapon)
    {
        this.weapon = weapon;
        getInventory().addItemToInventory(weaponName, weapon);
        return this;
    }

    public MovableObject getWeapon()
    {
        return weapon;
    }
}