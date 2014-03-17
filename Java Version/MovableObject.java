public class MovableObject extends Object implements java.io.Serializable
{
    protected int weight;    
    protected boolean weapon;    
    protected double weaponModifier;
    protected int passcode;
    protected int healthPotion;
    
    /**
     * This constructor calls for the creation of a new class instance
     * 
     * @param   name    the name to be used to reference the object
     */
    public static MovableObject create(String name)
    {
        return new MovableObject(name);
    }
    /**
     * This constructor creates a new MovableObject, setting its name and name
     * 
     * @param   the name to be used to reference the object
     */
    private MovableObject(String name)
    {
        this.name = name;
        this.weight = 0;
    }
    
    /**
     * This method sets the decriptive attribute of the object
     * 
     * @param   description     the string which descrives the object
     * @return                  the MovableObject
     */
    @Override
    public MovableObject withDescription(String description)
    {
        this.description = description;

        return this;
    }
    
    /**
     * This method sets the weight attribute of the object
     * 
     * @param   weight  how much the object is to weigh
     * @return          the MovableObject
     */
    public MovableObject andWeight(int weight)
    {
        this.weight = weight;

        return this;
    }

    /**
     * This sets the damage a weapon object can cause
     * 
     * @param   weaponModifier  the magnitude of its effect on health
     * @return                  the object itself
     */
    public MovableObject withWeaponModifier(double weaponModifier)
    {
        weapon = true;
        this.weaponModifier = weaponModifier;

        return this;
    }
    
    /**
     * This method sets the passcode of an object to be used as a key
     * 
     * @param   passcode    the code to identify the key to a single location
     * @return              the key MovableObject
     */
    public MovableObject andHasPasscode(int passcode)
    {
        this.passcode = passcode;

        return this;
    }
    
    /**
     * This method sets the amount of health a potion object should provide
     * 
     * @param   healthPotion    the amount of health that should be held by the potion
     * @return                  the potion MovableObject
     */
    public MovableObject andHealthPotion(int healthPotion)
    {
        this.healthPotion = healthPotion;
        return this;
    }
    
    /**
     * This method fetches the current value of the weight of the object
     * 
     * @return  how much the object has been set to weigh
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * This method fetches the amount of health provided by an object descirbed as a potion
     * 
     * @return  the health attribute of the potion object
     */
    public int getHealthPotion()
    {
        return healthPotion;
    }
    
    public double getWeaponModifier()
    {
        return weaponModifier;
    }
    
    public boolean checkWeapon()
    {
        return weapon;
    }
    
    public String getObjectName()
    {
        return this.name;
    }

    public int getPasscode()
    {
        if(passcode > 0 ){
            return passcode;
        }   
        return 0;
    }
}