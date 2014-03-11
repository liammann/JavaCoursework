
public class Player extends Character
{


    public Player (String name, int strength, int health, MovableObject weapon)
    {
        super(name, strength, health);
        getInventory().addItemToInventory(weapon);

    }

}