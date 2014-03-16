import java.util.HashMap;

public class Inventory implements java.io.Serializable
{
    private int inventoryWeight = 0;
    private int inventoryWeightCapacity = 10;
    private HashMap<String, MovableObject> inventory;

    public Inventory()
    {
        inventory = new HashMap<String, MovableObject>();
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
        String returntxt = "You currently have:\n";

        for(MovableObject item: inventory.values()) {
            returntxt += " - " + item.getObjectDescription() + "\n";
        }

        return returntxt;
    }

    public int inventorySize()
    {
        return inventory.size();
    }

    public int inventoryWeight()
    {
        for(MovableObject item: inventory.values()) {
            inventoryWeight += item.getWeight();
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
