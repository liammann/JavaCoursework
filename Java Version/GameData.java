import java.util.ArrayList;
import java.util.HashMap;

public class GameData implements java.io.Serializable
{
    private static ArrayList<Location> locationHistory;
    private static transient boolean gameStarted = false;
    private static transient ArrayList<String> savedGames;
    private static String gameName;
    protected static ArrayList<Location> locations;
    private static GameData instance = null;

    protected static Player player1; // this could perhaps be moved to the Location class

    
    public GameData()
    {
        locationHistory = new ArrayList<Location>();
        savedGames = new ArrayList<String>();

    }

    public static GameData getInstance()
    {
        if(instance == null)
        {
            instance = new GameData();
        }
        
        return instance;
    }

    public Player getPlayer1Object()
    {
        return player1;
    }
    
    public Location getCurrentLocation()
    {
        return locationHistory.get(locationHistory.size()-1);
    }

    public void setNewLocation(Location location)
    {
        locationHistory.add(location);
        //return a description maybe?
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
    
//     public void addFriend(String name, Friend friend)
//     {
//         friends.put(name, friend);
//     }
//     
//     public Friend getFriend(String name)
//     {
//         return friends.get(name);
//     }
    
//     public ArrayList<Friend> getAllFriends()
//     {
//         return new ArrayList<Friend>(friends.values());
//     }
}
