
public class Player extends Character implements java.io.Serializable
{
    public Player (String name, int strength, int health, MovableObject weapon)
    {
        super(name, strength, health);
        getInventory().addItemToInventory(weapon);

    }
    
    

}