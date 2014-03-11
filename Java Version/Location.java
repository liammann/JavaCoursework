import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Location implements java.io.Serializable
{
    private String description;
    private HashMap<String, Location> exits;
    private boolean isLocked = false;
    private ArrayList<Object> items;
    private ArrayList<Enemy> enemies;
    private ArrayList<Friend> friends;
    public static Location create()
    {
        return new Location();
    }
    
    public Location()
    {
        exits = new HashMap<String, Location>();
        items = new ArrayList<Object>();
        enemies = new ArrayList<Enemy>();
        friends = new ArrayList<Friend>();
    }

    public Location addDescription(String description)
    {
        this.description = description;
        return this;
    }
    
    public void addEnemy(Enemy enemy)
    {
       enemies.add(enemy);
    }
    public void addFriend(Friend friend)
    {
       friends.add(friend);
    }
    
    public void removeCharacters(){
        removeEnemies();
    }
    public void removeEnemies()
    {
        enemies.clear();
    }
    public String getLocationCharacters ()
    {
        if(enemies.size() != 0) {
            String answer = "This room has the following characters ('fight'/'talk'):\n";

            for(Enemy enemy : enemies) {
                answer += " - " + enemy.getName() + "\n";
            }

            return answer;
        }

        return "There are no characters in this room \n";

    }
    public ArrayList<Enemy> getArraryLocationEnemy ()
    {
        if(enemies.size() != 0) {
            return enemies;
        }
        return null;

    }
    public Location withExit(String direction, Location neighbour)
    {
        exits.put(direction, neighbour);
        return this;
    }

    public Location andItem(Object item) // implement a second parameter for quantity?
    {
        items.add(item);
        return this;
    }
    
    public void removeItem(MovableObject item)
    {
        items.remove(item);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + "\n" + showExits()+"\n";
    }

    public String getLocationItems()
    {
        if(items.size() != 0) {
            String answer = "This room has the following objects:\n";

            for(Object item : items) {
                answer += " - " + item.getObjectDescription() + "\n";
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
}