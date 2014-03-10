import java.util.ArrayList;

public class GameData implements java.io.Serializable
{
    private static ArrayList<Location> locationHistory;
    private static transient boolean gameStarted = false;
    public static transient ArrayList<String> savedGames;
    // private static String gameName;
    private static ArrayList<Location> locations;

    public GameData()
    {
        locationHistory = new ArrayList<Location>();
        savedGames = new ArrayList<String>();
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
    
    /*public String getGameName()
    {
        return gameName;
    }*/
}