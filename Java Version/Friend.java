/**
* The is a  Friend class. It helps the player in game by giving hints and also helping him get objects and fighting enemies.
* as well as select weapon which is inherited from inventory class.
*
* @version (1.1)
*/
public class Friend extends Character implements java.io.Serializable
{
    private String hint = null;
 
    /**
    * This constructor calls for the creationg of a friend
    * 
    * @param    name    the name for the friend
    * @return           a new friend object
    */
    public static Friend create(String name)
    {
        return new Friend(name);
    }
    
     /**
    * this constructor creates a new instance of the friend class setting the name
    * 
    * @param    name    the value for the name attribute
    */
    private Friend(String name)
    {
        super(name);
    }

    /**
    * This is a method which creates a hint for the friend to give to the player
    * 
    * @param    hint    the string to be provided to the player by the friend
    * @return           the friend
    */
    public Friend withHint(String hint)
    {
        this.hint = hint;
        return this;
    }
    
    
    /**
     * This method puts new weapons into the friend's inventry
     * 
     * @param   object  the moveable object
     * @return        it refers to the constructor of the same class where I am writing this code. It also means that it returns the friend's weapons.
     */
    public Friend andHasObject(MovableObject object)
    {
        this.getInventory().addItemToInventory(object);
        return this;
    }
  
    /**
    * This is hint method
    * 
    * @return       It returns the hint
    */
    public String getHint()
    {
        return hint;
    } 
    
    /**
    * This is a response method which gives hint to the player, when player type "help" command, this is where the friend response back to the player.
    * if there is a hint for the player then it returns hint. Otherwise, it doesn't return hint. 
    * 
    * @param         health command uses int data type to provide health to the enemy
    * @return        it refers to the constructor of the same class where I am writing this code. It also means that it returns hint with a message.
    */
    public String response()
    {
        if(hint == null) {
            return this.name + " says: sorry I have got no hints for you.";
        }
        
        return this.name + " says: hello I have got a hint for you:\n" + getHint();
    }
 }