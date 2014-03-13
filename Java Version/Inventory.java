import java.util.Set; // what does this do?
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * Write a description of class inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Inventory implements java.io.Serializable
{
    // instance variables - replace the example below with your own
    private int inventoryWeight = 0;
    private int inventoryWeightCapacity = 10;
    private HashMap<String, MovableObject> playerInventory;
    
    /**
     * Constructor for objects of class inventory
     */
    public Inventory()
    {
        playerInventory = new HashMap<String, MovableObject>();
    }
    
    // We should not be outputting to the console from this class! Return a String instead.
    public void addItemToInventory(String itemName, MovableObject item)
    {
        if (inventoryWeight() <= inventoryWeightCapacity)
        {
            playerInventory.put(itemName, item);
            //System.out.println(item.getObjectName() + " has been put in your bag.");
        } else {
            System.out.println("Your bag's too heavy already. Try dropping something before picking this up.");
        }
    }
    
    public void dropFromInventory(String item)
    {
        playerInventory.remove(item);
    }
    
    public MovableObject getFromInventoryByName(String item)
    {
        return playerInventory.get(item);
    }
    
    public String currentInventory(){

        String returntxt = "You currently have: \n";
        for(Map.Entry<String, MovableObject> item: playerInventory.entrySet()) {
        
            returntxt += "  - " + item.getValue().getObjectDescription() + "\n";
        }
        return returntxt;
    }
    
    public int inventorySize()
    {
        return playerInventory.size();
    }
    
    public int inventoryWeight()
    {
        for(Map.Entry<String, MovableObject> item: playerInventory.entrySet())
        {
            inventoryWeight += item.getValue().getWeight();
        }
        return inventoryWeight;
    }
    public MovableObject getWeapon(String weaponName){
        return playerInventory.get(weaponName);
    }
    /*
    public void setObjectName(String name, MovableObject object)
    {
        movableObjectNames.put(name, object);
    }
    
    public MovableObject getObjectByName(String object)
    {
        return movableObjectNames.get(object);
    }
    */
}
