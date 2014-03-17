
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
     * This constructor calls for the creation of a new instance of player
     * 
     * @param   name    the intended name of the player
     * @return          a new player object
     */
    public static Player create(String name)
    {
        return new Player(name);
    }
    
    /**
     * This constructor creates a new instance of player
     * 
     * @param   name    the intended name of the player
     * @return  null
     */
    private Player(String name)
    {
        super(name);
    }

    /**
    * This is a method which sets the strength of the player.
    * 
    * @param    strength    the amount of strength
    * @return               the player object
    */
    public Player hasStrength(int strength)
    {
        this.strength = strength;

        return this;
    }

    /**
    * This is a method which sets the amount of health the user has
    * 
    * @param    health  the quantity of health the player should have
    * @return           the player object
    */
    public Player withHealth(int health)
    {
        this.health = health;
        
        return this;
    }

        /**
    * This method puts a weapon in the players inventory
    * 
    * @param    weapon  the weapon the player should have
    * @return           the player object
    */
    public Player andHasWeapon(MovableObject weapon)
    {
        getInventory().addItemToInventory(weapon);
        return this;
    }
}