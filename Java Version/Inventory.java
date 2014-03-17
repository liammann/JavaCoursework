import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class Inventory implements java.io.Serializable
{
    private int inventoryWeight = 0;
    private int inventoryWeightCapacity = 10;
    private HashMap<String, MovableObject> inventory;
    
    public Inventory()
    {
        inventory = new HashMap<String, MovableObject>();
    }
    
    public HashMap<String, MovableObject> getArrayInventory(){
        return inventory;
    }

    /**
     * This method adds an object with its name to the inventory hashmap
     * 
     * @param   item    object to be added
     * @return  boolean of whether movableobject was added or not
     */
    public boolean addItemToInventory(MovableObject item)
    { // ADD ITEMS TO ENEMIES SUCH AS KEYS 
        
        if (inventoryWeight() + item.getWeight() > inventoryWeightCapacity) {
            return false;
        }
        
        inventory.put(item.getName(), item);
        return true;
    }
    
    /**
     * This method removes an object from the inventory
     * 
     * @param  objectName   the name of the object and name to be removed
     */
    public void dropFromInventory(String objectName)
    {
        inventoryWeight -= getFromInventoryByName(objectName).getWeight();
        inventory.remove(objectName);
    }
    
    /**
     * This method removes an object from the inventory
     * 
     * @param  object   the object to be removed with its name
     */ 
    public void dropFromInventoryByObject(MovableObject object) // liam - why do we need this?

    {
        inventoryWeight -= object.getWeight();
        inventory.remove(object.getObjectName());
    }
    
    /**
     * This method returns the object connected to its given name
     * 
     * @param   objectName  the name of the needed object
     * @return  the needed object
     */
    public MovableObject getFromInventoryByName(String objectName)
    {
        return inventory.get(objectName);
    }
    
    /**
     * This method searches the inventory for a key with a given keycode
     * 
     * @param   passcode    the keycode of the needed object
     * @return  MovableObject   the needed object
     * @return  null
     */
    public MovableObject getFromInventoryByNamePass(int passcode)
    {

       for(MovableObject item : inventory.values()) {
           if(item.getPasscode() == passcode){
               return item;
            }
       }
       return null;
    }
    
    /**
     * This method checks the avaiability of an object in the inventory
     * 
     * @param   name    the name of the queried object
     * @return  boolean of whether the object is in the inventory
     */
    public boolean containsObject(String name)
    {
        return inventory.containsKey(name);
    }
    
    /**
     * This method compiles a list of the names of all objects in the inventory
     * 
     * @return  a list of everything in the inventory
     */
    public String currentInventory()
    {
        String returntxt = "You currently have:\n";

        for(MovableObject item: inventory.values()) {
            returntxt += " - " + item.getObjectDescription() + "\n";
        }

        return returntxt;
    }
    
    /**
     * This method counts the number of objects in the inventory
     * 
     * @return  the number of objects in the inventory
     */
    public int inventorySize()
    {
        return inventory.size();
    }
    
    /**
     * This method sums the weights of all objects in the inventory
     * 
     * @return  the weight of the 
     */
    public int inventoryWeight()
    {
        for(MovableObject item: inventory.values()) {
            inventoryWeight += item.getWeight();
        }

        return inventoryWeight;
    }
    
    /**
     * This method finds the object with a particular name
     * 
     * @param   weaponName  the name of the needed object
     * @return  the needed object
     */
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
