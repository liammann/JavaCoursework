import java.util.ArrayList;
import java.util.HashMap;

public class GameData implements java.io.Serializable
{
    private static ArrayList<Location> locationHistory;
    private static transient boolean gameStarted = false;
    private static transient ArrayList<String> savedGames;
    private static String gameName;
    private static ArrayList<Location> locations;
    private static HashMap<String, MovableObject> movableObjectNames;

    protected static ArrayList<Enemy> bots;
    protected static ArrayList<Location> places;    
    protected static HashMap<String, Friend> friends;
    protected static Player player1; 
    protected static CommandsParser commandsParser;

    public GameData()
    {
        locationHistory = new ArrayList<Location>();
        savedGames = new ArrayList<String>();
        friends = new HashMap<String, Friend>();
    }

    public Player getPlayer1Object()
    {
        return player1;
    }
    
    public Location getCurrentLocation()
    {
        return locationHistory.get(locationHistory.size()-1);
    }

    public String setNewLocation(Location location)
    {
        locationHistory.add(location);
        // output what the new location has been set to?
        // It means changing game class (welcome() method)        
        return "";
    }

    public String setNewLocation(int backtrack)
    {
        int backtrackIndex = locationHistory.size() - backtrack - 1;
        
        if(backtrackIndex < 0) {
            return "The location you are attempting to go back to does not exist!";
        }
        
        locationHistory.add(locationHistory.get(backtrackIndex));
        return getCurrentLocation().getLongDescription();
    }
    
    public void startGame()
    {
        gameStarted = true;
    }
    
    public boolean hasGameStarted()
    {
        return gameStarted;
    }
    
    public void addGameSave(String gameSave)
    {
        savedGames.add(gameSave);
    }
    
    public boolean isValidGameSave(String gameSave)
    {
        return savedGames.contains(gameSave);
    }
    
    public void setName(String gameName)
    {
        this.gameName = gameName;
    }
    
    public String getName()
    {
        return gameName;
    }
    
    public void setObjectName(String name, MovableObject object)
    {
        movableObjectNames.put(name, object);
    }
    
    public MovableObject getObjectFromName(String object)
    {
        return movableObjectNames.get(object);
    }
    
    public void addFriend(String name, Friend friend)
    {
        friends.put(name, friend);
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
    
    public ArrayList<Friend> getAllFriends()
    {
        return new ArrayList<Friend>(friends.values());
    }
}
