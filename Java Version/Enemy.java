/**
* The is a  enemy class. It allow you to add name, get strength and health for the enemy
* as well as select weapon which is inherited from inventory class. Enemy can also use move
* able objects from selections.
*
* @author  (Zain Ali)
* @version (1.1)
*/
public class Enemy extends Character implements java.io.Serializable
{
    /**
    * This constructor creates a new instance of an enemy character
    * 
    * @param    name    the command name entered
    */
    public Enemy(String name)
    {
        super(name);
    }
    
    
    /**
    * This is a method which sets stregth to the enemy.
    * 
    * @param    strength    the enemy's intended strength
    * @return               the enemy object
    *
    */
    public Enemy hasStrength(int strength)
    {
        this.strength = strength;

        return this;
    }

    /**
    * This is a method which sets the health of the enemy.
    * 
    * @param    health  the enemy's intended level of health
    * @return           the enemy object
    */
    public Enemy withHealth(int health)
    {
        this.health = health;
        return this;
    }
    
    /**
    * This method puts a new weapon into the enemy's inventry
    * 
    * @param    weapon  the weapon object the enemy is to have
    * @return           the enemy object
    */
    public Enemy andHasWeapon(MovableObject weapon)
    {
        this.getInventory().addItemToInventory(weapon);
        return this;
    }
    
    /**
    * This method puts new object into the enemy's inventry
    * @param    object  the object to be put into the enemy's inventory
    * @return           the enemy object
    */
    public Enemy andHasObject(MovableObject object)
    {
        this.getInventory().addItemToInventory(object);
        return this;
    }
}