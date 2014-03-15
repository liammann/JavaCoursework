import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class Location implements java.io.Serializable
{
    private String description;
    private HashMap<String, Location> exits;
    private boolean isLocked = false;
    private HashMap<String, Object> items;
    private HashMap<String, Enemy> enemies;
    private HashMap<String, Friend> friends;
    private HashMap<String, MovableObject> movableItems;

    public static Location create()
    {
        return new Location();
    }

    public Location()
    {
        exits = new HashMap<String, Location>();
        items = new HashMap<String, Object>();
        enemies = new HashMap<String, Enemy>();
        friends = new HashMap<String, Friend>();
    }

    public Location addDescription(String description)
    {
        this.description = description;

        return this;
    }

    public Enemy addEnemy(String enemyName, Enemy enemy)
    {
       enemies.put(enemyName, enemy);
       return enemy;
    }
    public Enemy getEnemy(String enemyName){
        return enemies.get(enemyName);
    }    
    
    public Enemy removeEnemy(String enemyName){
        return enemies.remove(enemyName);
    }

    public void addFriend(String friendName, Friend friend)
    {
       friends.put(friendName, friend);
    }

    public String getLocationCharacters()
    {
        boolean friendsB = false;
        boolean enemiesB = false;
        String answer = "This room has the following characters ('fight'/'talk'):\n";

        if(enemies.size() != 0) {
            enemiesB = true;
            
            for(Map.Entry<String, Enemy> enemy: enemies.entrySet()) {
                answer += " - " + enemy.getValue().getName() + "\n";
            }
        }

        if(friends.size() != 0) {
            friendsB = true;

            for(String friendName : friends.keySet()) {
                answer += " - " + friendName + "\n";
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

    public Location andItem(Object item) // implement a second parameter for quantity?
    {
        items.put(item.getName(), item);

        return this;
    }
    
    public void addMovableObject(String name, MovableObject object)
    {
        movableItems.put(name, object);
    }
    
    public void removeItem(String item)
    {
        items.remove(item);
    }
    
    public MovableObject getObjectByName(String itemName)
    {
        return movableItems.get(itemName);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + "\n" + showExits() + "\n";
    }

    public String getLocationItems()
    {
        if(items.size() != 0) {
            String answer = "This room has the following objects:\n";

            for(String itemName : items.keySet()) {
                answer += " - " + itemName + "\n";
            }

            return answer;
        }

        return "There are no items in this room \n";
    }

    public String showExits()
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

    public boolean isLocked()
    {
        return isLocked;
    }

    public void unlock()
    {
        isLocked = false;
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
    
    /*public Object getObject(String objectName)
    {
        //
    }*/
}