import java.util.ArrayList;
import java.util.HashMap;

public class Location implements java.io.Serializable
{
    private String description;
    private HashMap<String, Location> exits;
    private boolean isLocked = false;
    private HashMap<String, FixedObject> fixedObjects;
    private HashMap<String, MovableObject> movableObjects;
    private HashMap<String, Enemy> enemies;
    private HashMap<String, Friend> friends;
    private int locationPasscode;

    public static Location create()
    {
        return new Location();
    }

    public Location()
    {
        exits = new HashMap<String, Location>();
        fixedObjects = new HashMap<String, FixedObject>();
        movableObjects = new HashMap<String, MovableObject>();
        enemies = new HashMap<String, Enemy>();
        friends = new HashMap<String, Friend>();
    }

    /**
     * questions if the location contains a specific movable object
     * 
     * @param   name    the name of the object to look for
     * @return          whether the object is in the location
     */
    public boolean containsMovableObject(String name)
    {
        if(movableObjects.containsKey(name)) {
            return true;
        }
        
        return false;
    }

    /**
     * questions if the location contains a specific immovable object
     * 
     * @param   name    the name of the object to look for
     * @return          whether the object is in the location
     */
    public boolean containsFixedObject(String name)
    {
        if(fixedObjects.containsKey(name)) {
            return true;
        }
        
        return false;
    }

    /**
     * sets the descriptive attribute of the location
     * 
     * @param   description     the string that describes the location
     * @return  the location
     */
    public Location addDescription(String description)
    {
        this.description = description;

        return this;
    }
    
    /**
     * This method fetches an enemy with their name as reference
     * 
     * @param   enemyName   the name of the enemy
     * @return              the enemy
     */
    public Enemy getEnemyByName(String enemyName)
    {
        return enemies.get(enemyName);
    }    
    
    /**
     * This method fetches a friend with their name as reference
     * 
     * @param   friendName  the name of the friend character
     * @return              the friend
     */
    public Friend getFriendByName(String friendName)
    {
        return friends.get(friendName);
    }    
    
    /**
     * This method deletes an enemy from the location with their name as reference
     * 
     * @param   enemyName   the name of the enemy
     * @return              the enemy
     */
    public void removeEnemyByName(String enemyName)
    {
        enemies.remove(enemyName);
    }

    /**
     * adds an enemy to the location
     * 
     * @param   enemy   the enemy to add
     * @return          the location
     */
    public Location andEnemy(Enemy enemy)
    {
       enemies.put(enemy.getName(), enemy);

       return this;
    }

    /**
     * adds a friend to the location
     * 
     * @param   friend  the friend to add
     */
    public void andFriend(Friend friend)
    {
       friends.put(friend.getName(), friend);
    }

    /**
     * outputs a string listing the names of all characters in the location
     * 
     * @return          a list of characters
     */
    public String getLocationCharacters()
    {
        boolean friendsB = false;
        boolean enemiesB = false;
        String answer = "\nThis room has the following characters:\n";

        answer += "    Friends:";
        if(friends.size() != 0) {
            for(String friendName : friends.keySet()) {
                answer += "\n\t - " + friendName;
            }
        }else{
            answer += "\n\tnone";
        }

        answer += "\n    Enemies:";
        if(enemies.size() != 0) {
            for(String enemyName : enemies.keySet()) {
                answer += "\n\t - " + enemyName;
            }
        }else{
            answer += "\n\tnone";
        }

        return answer + "\n";
    }
    /**
     * This method is used to set the exists for each locations 
     * 
     * @param       direction        what direction the exit is     
       * @param     neighbour      what direction the exit is
     * @return           location object
     */
    public Location withExit(String direction, Location neighbour)
    {
        exits.put(direction, neighbour);

        return this;
    }
    
    public Location getLocationNeighour(String direction)
    {
        return exits.get(direction);
    }

    public Location andHasObject(FixedObject object)
    {
        fixedObjects.put(object.getName(), object);

        return this;
    }
    
    public Location andHasObject(MovableObject object)
    {
        movableObjects.put(object.getName(), object);

        return this;
    }

    public void removeObject(String objectName) //ByName
    {
        movableObjects.remove(objectName);
    }
    
    public MovableObject getMovableObjectByName(String objectName)
    {
        return movableObjects.get(objectName);
    }
    
    public FixedObject getFixedObjectByName(String objectName)
    {
        return fixedObjects.get(objectName);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description;
    }

     /**
     * getLocationItems Method 
     *
     * Get location items either fixed or movable objects.
     * return none if none exist 
     * 
     * Called by Game and CommandAction classes
     * 
     */
    public String getLocationItems()
    {
        String answer = "This room has the following objects:\n";
        
        answer += "    Fixed objects:";
        if(fixedObjects.size() != 0) {
            for(String fObjectName : fixedObjects.keySet()) {
                answer += "\n\t - " + fObjectName;
            }
        }else{
            answer += "\n\tnone";
        }
        
        answer += "\n    Movable objects:";
        if(movableObjects.size() != 0) {
            for(String mObjectName : movableObjects.keySet()) {
                answer += "\n\t - " + mObjectName;
            }
        }else{
            answer += "\n\tnone";
        }
        
        return answer + "\n";
    }
     /**
     * getExits Method 
     *
     * Get exits for current location
     * will always return something because every location has a exit
     * 
     * Called by Game and CommandAction classes
     * 
     */
    public String getExits()
    {
        String exitPoints = "Exits:";

        for(String exit : exits.keySet()) {
            exitPoints += " " + exit;
        }

        return exitPoints;
    }

    public boolean isValidExit(String direction)
    {
        return exits.containsKey(direction);
    }

    public Location getExit(String direction)
    {
        return exits.get(direction);
    }

    public Location andIsLocked()
    {
        isLocked = true; // no need for this? set in passcode
        return this;
    }
   
    public boolean isLocked()
    {
        return isLocked;
    }
    
    public void unlock()
    {
        isLocked = false;
    }
    
    /**
     * andPasscode Method 
     * 
     * Add passcode integer to location so it can only be unlock by a key (MovableObject) with the same passcode. 
     */
    public Location andPasscode(int passcode)
    {
        isLocked = true;
        locationPasscode = passcode;
        return this;
    }
    
    /**
     * getPasscode Method 
     * 
     * Get passcode integer for current location object. 
     */
    public int getPasscode(){
        return locationPasscode;
        
    }
    
    public boolean isValidFriend(String name)
    {
        if(!friends.containsKey(name)) {
            return false;
        }
        
        return true;
    }
    
    public Friend getFriend(String name)
    {
        return friends.get(name);
    }
}