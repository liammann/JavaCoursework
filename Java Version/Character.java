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
    private int health;
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
    private int getHealth()
    {
        return health;
        
    }
    
    private void updateHealth(int add)
    {
        health = health + add;
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
