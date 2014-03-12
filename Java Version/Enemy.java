

public class Enemy extends Character implements java.io.Serializable
{   
    private MovableObject weapon;
    public Enemy(String name, int strength, int health)
    {
        super(name, strength, health);
        
    }
    public Enemy(String name, int strength, int health, MovableObject weapon)
    {
        super(name, strength, health);
        this.weapon = weapon;
        
    }
    public MovableObject getWeapon(){
        return weapon;
    }
}