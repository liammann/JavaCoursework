public class MovableObject extends Object implements java.io.Serializable
{
    protected int weight;    
    protected boolean weapon;    
    protected double weaponModifier;
    protected int passcode;
    
    public static MovableObject create(String name)
    {
        return new MovableObject(name);
    }
    
    private MovableObject(String name)
    {
        this.name = name;
    }
    
    @Override
    public MovableObject withDescription(String description)
    {
        this.description = description;

        return this;
    }
    
    public MovableObject andWeight(int weight)
    {
        this.weight = weight;

        return this;
    }

    public MovableObject withWeaponModifier(double weaponModifier)
    {
        weapon = true;
        this.weaponModifier = weaponModifier;

        return this;
    }
    
    public MovableObject andHasPasscode(int passcode)
    {
        this.passcode = passcode;

        return this;
    }

    public int getWeight()
    {
        return weight;
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
        return passcode;
    }
}