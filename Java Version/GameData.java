import java.util.ArrayList;
import java.util.HashMap;

public class GameData implements java.io.Serializable
{
    private ArrayList<Location> locationHistory;
    private boolean gameStarted = false;
    private ArrayList<String> savedGames;
    private String gameName;
    private  ArrayList<Location> locations; // why?
    private static GameData instance = null;
    private HashMap<String, Player> players;
    //private String currentPlayer;
    
    private Player player1; // this could perhaps be moved to the Location class

    public static GameData getInstance()
    {
        if(instance == null) {
            instance = new GameData();
        }
        
        return instance;
    }
    
    private GameData()
    {
        locationHistory = new ArrayList<Location>();
        savedGames = new ArrayList<String>();
        locations = new ArrayList<Location>(); // why?
        players = new HashMap<String, Player>();

    }
    
    public void addLocation(Location location)
    {
        locations.add(location);
    }

    public Player getPlayerObject()//(String playerName)
    {
        return players.get("Player 1");
    }
    
    public Location getCurrentLocation()
    {
        return locationHistory.get(locationHistory.size()-1);
    }

    public void setNewLocation(Location location)
    {
        locationHistory.add(location);
        // return a description maybe?
    }

    public String setNewLocation(int backtrack)
    {
        int lastIndex = locationHistory.size() - 1;
        int backtrackIndex = lastIndex - backtrack;

        if(backtrackIndex < 0) {
            return "The location you are attempting to go back to does not exist!";
        }

        while(lastIndex > backtrackIndex) {
            --lastIndex;
            locationHistory.add(locationHistory.get(lastIndex));
        }

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
    
    public boolean isGameSave(String gameSave)
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
    
    public ArrayList<String> getSavedGames()
    {
        return savedGames;
    }
    
    public void addPlayer(Player player)
    {
        players.put(player.getName(), player);
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
