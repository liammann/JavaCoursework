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
    * This is a method which takes command from the user and invokes the function
    * Super is a special type of keyword and it is used to access the class
    * 
    * @param            name  the command name entered
    */
    public Enemy(String name)
    {
        super(name);
    }
    
    
    /**
    * This is a method which gives stregth to the enemy.
    * 
    * @param         strength command uses int data type
    * @return        it refers to the constructor of the same class where I am writing this code. It also means that it returns the enemy's strength
    *
    */
    public Enemy hasStrength(int strength)
    {
        this.strength = strength;

        return this;
    }

    /**
    * This is a method which gives health to the enemy.
    * 
    * @param         health command uses int data type to provide health to the enemy
    * @return        it refers to the constructor of the same class where I am writing this code. It also means that it returns the enemy's health
    */
    public Enemy withHealth(int health)
    {
        this.health = health;
        return this;
    }
    
    /**
    * This method puts new object into the enemy's inventry class
    * @param         Its the moveable object
    * @return        it refers to the constructor of the same class where I am writing this code. It also means that it returns the enemy's weapons.
    */
    public Enemy andHasObject(MovableObject object)
    {
        this.getInventory().addItemToInventory(object);
        return this;
    }
}