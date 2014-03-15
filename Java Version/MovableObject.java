public class MovableObject extends Object implements java.io.Serializable
{
    protected int weight;    
    protected boolean weapon;    
    protected double weaponModifier;
    protected int passcode;
    protected int healthPotion;
    
    public MovableObject(String name, String description, int weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;

    }
    
    public MovableObject(String name, String description, int weight, double weaponModifier)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;       
        this.weapon = true;
        this.weaponModifier = weaponModifier;

    }
    
    public MovableObject(String name, int keyPasscode)
    {
        this.name = name;
        this.passcode = keyPasscode;
    }
    public MovableObject(String name, double healthPotion)
    {
        this.name = name;
        this.healthPotion = (int) healthPotion;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
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
        return passcode;
    }
}