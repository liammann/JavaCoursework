import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Location implements java.io.Serializable
{
    private String description;
    private HashMap<String, Location> exits;
    private boolean isLocked = false;
    private ArrayList<Object> items;
    private ArrayList<Character> characters;

    public static Location create()
    {
        return new Location();
    }
    
    public Location()
    {
        exits = new HashMap<String, Location>();
        items = new ArrayList<Object>();
        characters = new ArrayList<Character>();
    }

    public Location addDescription(String description)
    {
        this.description = description;
        return this;
    }
    
    public void addCharacter(Character character)
    {
       characters.add(character);
    }
    
    public void removeCharacters()
    {
        characters.clear();
    }
    public String getLocationCharacters ()
    {
        if(characters.size() != 0) {
            String answer = "This room has the following characters ('fight'/'talk'):\n";

            for(Character character : characters) {
                answer += " - " + character.getName() + "\n";
            }

            return answer;
        }

        return "There are no characters in this room \n";

    }
    public ArrayList<Character> getArraryLocationCharacters ()
    {
        if(characters.size() != 0) {
            return characters;
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
    
    public void removeItem(Object item)
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