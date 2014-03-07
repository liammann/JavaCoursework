import java.util.ArrayList;

public class GameData
{
    private static ArrayList<Location> locationHistory;

    public GameData()
    {
        locationHistory = new ArrayList<Location>();
    }

    public Location getCurrentLocation()
    {
        return locationHistory.get(locationHistory.size()-1);
    }

    public String setNewLocation(Location location)
    {
        locationHistory.add(location);
        return ""; // output what the new location has been set to? means changing game class (welcome() method)
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
}