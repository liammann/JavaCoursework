import java.util.Set; // what does this do?
import java.util.LinkedHashMap;
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
    private LinkedHashMap<String, MovableObject> playerInventory;
    
    /**
     * Constructor for objects of class inventory
     */
    public Inventory()
    {
        playerInventory = new LinkedHashMap<String, MovableObject>();
    }
    
    // We should not be outputting to the console from this class! Return a String instead.
    public boolean addItemToInventory(MovableObject item)
    {
        if (inventoryWeight() + item.getWeight() <= inventoryWeightCapacity) {
            playerInventory.put(item.getName(), item);
            return true;
        }
        
        return false;
    }
    
    public void dropFromInventory(String item)
    {
        playerInventory.remove(item);
    }
    
    public MovableObject getFromInventoryByName(String item)
    {
        return playerInventory.get(item);
    }
    
    public boolean containsObject(String name)
    {
        return playerInventory.containsKey(name);
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
    
    public MovableObject getWeapon(String weaponName)
    {
        return playerInventory.get(weaponName);
    }
    
    public MovableObject getWeapon()
    {
        return playerInventory.get(0);
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
