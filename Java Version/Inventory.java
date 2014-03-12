import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * Write a description of class inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Inventory implements java.io.Serializable
{
    // instance variables - replace the example below with your own
    private ArrayList<MovableObject> playerInventory;
    private int inventoryWeight = 0;
    private int inventoryWeightCapacity = 10;
    private static HashMap<String, MovableObject> movableObjectNames;
    
    /**
     * Constructor for objects of class inventory
     */
    public Inventory()
    {
        playerInventory = new ArrayList<MovableObject>();
        movableObjectNames = new HashMap<String, MovableObject>();
    }
    
    public void addItemToInventory(MovableObject item)
    {
        if (inventoryWeight() <= inventoryWeightCapacity)
        {
            playerInventory.add(item);
            System.out.println(item.getObjectName() + " has been put in your bag.");
        } else {
            System.out.println("Your bag's too heavy already. Try dropping something before picking this up.");
        }
    }
    
    public void dropFromInventory(MovableObject index)
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
    public ArrayList<MovableObject> playerWeapons(){
        ArrayList<MovableObject> playerWeapons = new ArrayList<MovableObject>(); 
        
        for (MovableObject item : playerInventory)
        {
            if(item.checkWeapon()){
                playerWeapons.add(item);
            }
        }
        return playerWeapons;
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
