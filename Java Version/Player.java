
/**
* The is a  Player class. It allow you to add name, get strength and health for the player
* as well as select weapon which is inherited from inventory class.
*
* @author  (Zain Ali)
* @version (1.1)
*/
public class Player extends Character implements java.io.Serializable
{
    /**
     * This is a method which takes command from the user and invokes the function
     * 
     * @param       name  the command name entered
     * @return      the return value of the invoked function
     */
    public static Player create(String name)
    {
        return new Player(name);
    }

    /**
    * This is a constructor. Super is a special type of keyword and it is used to access the class
    * 
    * @param         name  the command name entered
    */
    private Player(String name)
    {
        super(name);
    }

    /**
    * This is a method which gives strength to player.
    * 
    * @param        strength command uses int data type
    * @return       it refers to the constructor of the same class where I am writing this code.
    */
    public Player hasStrength(int strength)
    {
        this.strength = strength;

        return this;
    }

    /**
    * This is a method which shows that the player uses health
    * 
    * @param         health command uses int data type
    * @ return       it refers to the constructor of the same class where I am writing this code.
    */
    public Player withHealth(int health)
    {
        this.health = health;
        
        return this;
    }

        /**
    * This is method is inherit from Inventory class where it allow the player to get the weapons.
    * 
    * @param         it uses MovableObject weapons
    * @ return       it refers to the constructor of the same class where I am writing this code.
    */
    public Player andHasWeapon(MovableObject weapon)
    {
        getInventory().addItemToInventory(weapon);
        return this;
    }
}