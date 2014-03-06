import java.util.ArrayList;

public class CommandActions
{
    private CommandWords commandWords;
    private GameData gameData;

    public CommandActions()
    {
        commandWords = new CommandWords();
        gameData = new GameData();
    }

    public String invokeAction(String keyword)
    {
        String answer = "";

        switch(keyword) {
            case "quit":
                answer = quit();
                break;
            case "help":
                answer = help();
                break;
            case "manual":
                answer = commandWords.manual();
                break;
            case "back":
                answer = back();
                break;
            case "inventory":
                // answer = inventory(); ???
                break;
            case "attack":
                // answer = attack(); ???
                break;
            case "defend":
                // answer = defend(); ???
                break;
        }

        return answer;
    }

    public String invokeAction(String keyword, String parameter)
    {
        String answer = "";

        switch(keyword) {
            case "go":
                answer = go(parameter);
                break;
            case "back":
                int param = Integer.parseInt(parameter);
                answer = back(param);
                break;
            case "manual":
                answer = commandWords.manual(parameter);
                break;
            case "pickup":
                answer = pickup(parameter);
                break;
        }

        return answer;
    }

    public String invokeAction(String keyword, ArrayList parameters)
    {
        String answer = "";

        switch(keyword) {
            case "jump":
                answer = jump(parameters);
                break;
        }

        return answer;
    }

    private String quit()
    {
        return "quit";
    }

    private String help()
    {
        return getTask() + "\nType 'manual' to view your list of commands, or 'manual {command}' to learn about a specific command.";
    }

    private String getTask()
    {
        return "Your task is to...";
    }

    private String back()
    {
        return "Going back 1 location: " + gameData.setNewLocation(1);
    }

    private String back(int retraceSteps)
    {
        return "Going back " + retraceSteps + " location(s): " + gameData.setNew        Location(retraceSteps);
    }

    private String pickup(String objectName)
    {
        // put object in inventory and remove object from location
        return "Item " + objectName + " has been picked up!";
    }

    private String go(String direction)
    {
        if(!gameData.getCurrentLocation().isValidExit(direction)) {
            return "Invalid exit!";
        }

        if(gameData.getCurrentLocation().getExit(direction).isLocked()) {
            return "That room is locked!";
        }
        
        return updateLocation(direction);
    }

    private String updateLocation(String direction)
    {
        gameData.getCurrentLocation().removeCharacter();
        gameData.setNewLocation(gameData.getCurrentLocation().getExit(direction));
        gameData.getCurrentLocation().addCharacter();
        return gameData.getCurrentLocation().getLongDescription();
    }

    private String jump(ArrayList parameters)
    {
        return "jumped";
        // jump is a dud method, so I won't bother to fill it in...
    }
}