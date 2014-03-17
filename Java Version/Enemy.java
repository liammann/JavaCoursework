public class Enemy extends Character implements java.io.Serializable
{
    public Enemy(String name)
    {
        super(name);
    }

    public Enemy hasStrength(int strength)
    {
        this.strength = strength;

        return this;
    }

    public Enemy withHealth(int health)
    {
        this.health = health;
        return this;
    }

    public Enemy andHasWeapon(MovableObject weapon)
    {
        this.getInventory().addItemToInventory(weapon);
        return this;
    }
    public Enemy andHasObject(MovableObject object)
    {
        this.getInventory().addItemToInventory(object);
        return this;
    }
}