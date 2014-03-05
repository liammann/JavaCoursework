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
    private ArrayList<Item> playerInventory;
    /**
     * Constructor for objects of class inventory
     */
    public Inventory()
    {
        playerInventory = new ArrayList<Item>();
    }
    
    public void addItemToInventory(Item item)
    {
        playerInventory.add(item);
    }
    public void removeItemFromInventory(int index)
    {
        playerInventory.remove(index);
    }
    public String currentInventory()
    {
        String returntxt = "You currently have: \n";
        for (Item item : playerInventory)
        {
            returntxt += "  - " + item.getDescription() + "\n";
        }
        return returntxt;
    }
}
