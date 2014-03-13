
public class Player extends Character implements java.io.Serializable
{
    private MovableObject weapon;
    private String weaponName;
    
    public Player (String name)
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

    public Player andHasWeapon(String weaponName, MovableObject weapon)
    {
        this.weapon = weapon;        
        this.weaponName = weaponName;
        getInventory().addItemToInventory(weaponName, weapon);
        return this;
    }

}