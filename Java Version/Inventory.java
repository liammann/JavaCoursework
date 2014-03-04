import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * Write a description of class inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Inventory
{
    // instance variables - replace the example below with your own
    private ArrayList<Item> playerInventory = new ArrayList<Item>();
    /**
     * Constructor for objects of class inventory
     */
    public Inventory()
    {
      
    }
    
    public void addItemToInventory(Item item)
    {
        playerInventory.add(item);
        
    }
    public String currentInventory(){

        String returntxt = "You currently have: \n";
        for (Item item : playerInventory)
        {
            returntxt += "  - " + item.getDescription() + "\n";
        }
        return returntxt;
    }
}
