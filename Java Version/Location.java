import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map; // is this needed still?

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
        return new Location(); // do I need? Keep consistent with creating new characters
    }

    public Location()
    {
        exits = new HashMap<String, Location>();
        fixedObjects = new HashMap<String, FixedObject>();
        movableObjects = new HashMap<String, MovableObject>();
        enemies = new HashMap<String, Enemy>();
        friends = new HashMap<String, Friend>();
    }
    
    public boolean containsMovableObject(String name)
    {
        if(movableObjects.containsKey(name)) {
            return true;
        }
        
        return false;
    }
    
    public boolean containsFixedObject(String name)
    {
        if(fixedObjects.containsKey(name)) {
            return true;
        }
        
        return false;
    }

    public Location addDescription(String description)
    {
        this.description = description;

        return this;
    }
    
//     public HashMap getEnemies()
//     {
//         return enemies;
//     }
    
    public Enemy getEnemyByName(String enemyName)
    {
        return enemies.get(enemyName);
    }    
    
    public Enemy removeEnemyByName(String enemyName)
    {
        return enemies.remove(enemyName);
    }

    public Location andEnemy(Enemy enemy)
    {
       enemies.put(enemy.getName(), enemy);

       return this;
    }

    public void andFriend(String friendName, Friend friend)
    {
       friends.put(friendName, friend);
    }

    // Maybe segregate the friends from the enemies?
    public String getLocationCharacters()
    {
        boolean friendsB = false;
        boolean enemiesB = false;
        String answer = "This room has the following characters ('fight'/'talk'):\n";

        if(enemies.size() != 0) {
            enemiesB = true;
            
            for(String enemyName : enemies.keySet()) {
                answer += " - " + enemyName + "\n";
            }
        }

        if(friends.size() != 0) {
            friendsB = true;

            for(String friendName : friends.keySet()) {
               // answer += " - " + friendName + "\n";
            }
        }

        if(enemiesB || friendsB) {
            return answer;
        }else{
            return "There are no characters in this room \n";
        }
    }

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

    public void removeObject(String objectName)
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
        return "You are " + description + "\n";
    }

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
        
        return answer+"\n";
    }

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
        isLocked = true;
        return this;
    }
    public Location andPasscode(int passcode)
    {
        locationPasscode = passcode;
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