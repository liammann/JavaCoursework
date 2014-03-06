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
    private ArrayList<MovableObject> playerInventory;
    private int inventoryWeight = 0;
    private int inventoryWeightCapacity = 10;
    
    /**
     * Constructor for objects of class inventory
     */
    public Inventory()
    {
        playerInventory = new ArrayList<MovableObject>();
    }
    
    public void addItemToInventory(MovableObject item)
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
        for (MovableObject item : playerInventory)
        {
            returntxt += "  - " + item.getObjectDescription() + "\n";
        }
        return returntxt;
    }
    
    public int inventorySize()
    {
        return playerInventory.size();
    }
    
    public int inventoryWeight()
    {
        for (MovableObject item: playerInventory)
        {
            inventoryWeight += item.getWeight();
        }
        return inventoryWeight;
    }
}
