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
    private int inventoryWeight = 0;
    private int inventoryWeightCapacity = 10;
    private LinkedHashMap<String, MovableObject> playerInventory;

    public Inventory()
    {
        playerInventory = new LinkedHashMap<String, MovableObject>();
    }

    public boolean addItemToInventory(MovableObject item)
    {
        if (inventoryWeight() + item.getWeight() > inventoryWeightCapacity) {
            return false;
        }
        
        playerInventory.put(item.getName(), item);

        return true;
    }
    
    public void dropFromInventory(String objectName)
    {
        inventoryWeight -= getFromInventoryByName(objectName).getWeight();
        playerInventory.remove(objectName);
    }
    
    public MovableObject getFromInventoryByName(String objectName)
    {
        return playerInventory.get(objectName);
    }
    
    public boolean containsObject(String name)
    {
        return playerInventory.containsKey(name);
    }

    public String currentInventory()
    {
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
        for(Map.Entry<String, MovableObject> item: playerInventory.entrySet()) {
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
}
