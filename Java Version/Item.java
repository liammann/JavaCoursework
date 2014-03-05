
/**
 * Write a description of class Items here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String description;    
    private String type;

    /**
     * Constructor for objects of class Items
     */
    public Item(String description, String type)
    {
        // initialise instance variables
        this.description = description;
        this.type = type;
        
        
    }
    
    public String getDescription()
    {
        return description;
    }
}
