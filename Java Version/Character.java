/**
 * Write a description of class character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character
{
    // instance variables - replace the example below with your own
    private String name;
    public int health;
    private Room currentLocation;
    private int strength;
    

    /**
     * Constructor for objects of class character
     */
    public Character(String name, int strength, int health)
    {
        // initialise instance variables
        this.health = health;
        this.strength = strength;
        this.name = name;


    }
    public int getHealth()
    {
        return health;
        
    }
    public int getStrength()
    {
        return strength;
        
    }
    
    public void updateHealth(int newHealth)
    {
        health = newHealth;
    }
    
    public void setLocation(Room current)
    {
        currentLocation = current;
    }
    public Room getLocation()
    {
        return currentLocation;
        
    }
    
   

}
