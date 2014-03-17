import java.util.ArrayList;
import java.util.HashMap;

public class GameData implements java.io.Serializable
{
    private ArrayList<Location> locationHistory;
    private boolean gameStarted = false;
    private ArrayList<String> savedGames;
    private String gameName;
    private  ArrayList<Location> locations; // why? Liam - it used by addLocation but dont know why
    private static GameData instance = null;
    private HashMap<String, Player> players;

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
        locations = new ArrayList<Location>(); // why? Liam - it used by addLocation but dont know why
        players = new HashMap<String, Player>();
    }

    /**
     * This method is used to add a location to the locations field ArrayList
     * 
     * @param   location    the location object to be added
     */
    public void addLocation(Location location)
    {
        locations.add(location);
    }

    /**
     * getPlayerObject 
     * Used for fight method in Game class
     */
    public Player getPlayerObject() //(String playerName)
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

        return "Going back " + backtrack + " location(s): ";
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
}
