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
    private int inventoryWeight = 0;
    private int inventoryWeightCapacity = 10;
    
    /**
     * Constructor for objects of class inventory
     */
    public Inventory()
    {
        playerInventory = new ArrayList<Item>();
    }
    
    public void addItemToInventory(Item item)
    {
        if (inventoryWeight() <= inventoryWeightCapacity)
        {
            playerInventory.add(item);
            System.out.println(item + " has been put in your bag.");
        } else {
            System.out.println("Your bag's too heavy already. Try dropping something before picking this up.");
        }
    }
    
    public void dropFromInventory(int index)
    {
        playerInventory.remove(index);
    }
    
    public String currentInventory(){

        String returntxt = "You currently have: \n";
        for (Item item : playerInventory)
        {
            returntxt += "  - " + item.getDescription() + "\n";
        }
        return returntxt;
    }
    
    public int inventorySize()
    {
        return playerInventory.size();
    }
    
    public int inventoryWeight()
    {
        for (Item item: playerInventory)
        {
            inventoryWeight += item.getWeight();
        }
        return inventoryWeight;
    }
}
