public class Player extends Character implements java.io.Serializable
{
    public static Player create(String name)
    {
        return new Player(name);
    }

    private Player(String name)
    {
        super(name);
    }

    public Player hasStrength(int strength)
    {
        this.strength = strength;

        return this;
    }

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