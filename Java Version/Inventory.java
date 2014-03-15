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
    private LinkedHashMap<String, MovableObject> inventory;

    public Inventory()
    {
        inventory = new LinkedHashMap<String, MovableObject>();
    }

    public boolean addItemToInventory(MovableObject item)
    {
        if (inventoryWeight() + item.getWeight() > inventoryWeightCapacity) {
            return false;
        }
        
        inventory.put(item.getName(), item);

        return true;
    }
    
    public void dropFromInventory(String objectName)
    {
        inventoryWeight -= getFromInventoryByName(objectName).getWeight();
        inventory.remove(objectName);
    }
    
    public MovableObject getFromInventoryByName(String objectName)
    {
        return inventory.get(objectName);
    }
    
    public boolean containsObject(String name)
    {
        return inventory.containsKey(name);
    }

    public String currentInventory()
    {
        String returntxt = "You currently have: \n";

        for(Map.Entry<String, MovableObject> item: inventory.entrySet()) {
            returntxt += "  - " + item.getValue().getObjectDescription() + "\n";
        }

        return returntxt;
    }
    
    public int inventorySize()
    {
        return inventory.size();
    }
    
    public int inventoryWeight()
    {
        for(Map.Entry<String, MovableObject> item: inventory.entrySet()) {
            inventoryWeight += item.getValue().getWeight();
        }

        return inventoryWeight;
    }
    
    public MovableObject getWeapon(String weaponName)
    {
        return inventory.get(weaponName);
    }
    
    public MovableObject getWeapon()
    {
        String firstKey = inventory.keySet().iterator().next();

        return getWeapon(firstKey);
    }
}
