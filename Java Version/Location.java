import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Location
{
	private String description;
	private HashMap<String, Location> exits;
	private boolean isLocked;
	private ArrayList<Object> items;
	private ArrayList<Character> characters;

	public Location(String description)
	{
		this.description = description;
		exits = new HashMap<String, Location>();
		items = new ArrayList<Object>();
		characters = new ArrayList<Character>();
	}

	public void setExit(String direction, Location neighbour)
	{
		exits.put(direction, neighbour);
	}

	public void setItem(Object item)
	{
		items.add(item);
	}

	public String getShortDescription()
	{
		return description;
	}

	public String getLongDescription()
	{
		return "You are " + description + "\n" + showExits();
	}

	public String getItems()
	{
		if(items.size() != 0) {
			String answer = "This room has the following objects:\n";

			for(Object item : items) {
				answer += " - " + item.getObjectDescription() + "\n";
			}

			return answer;
		}

		return "There are no items in this room";
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

	public void setLock()
	{
		isLocked = true;
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