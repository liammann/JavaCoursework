import java.util.ArrayList;

public class CommandActions
{
    private CommandWords commandWords;
    private GameData gameData;

    public CommandActions()
    {
        commandWords = CommandWords.getInstance();
        gameData = GameData.getInstance();
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
            case "drop":
                answer = drop(parameter);
                break;
            case "inspect":
                answer = inspect(parameter);
                break;
            case "new":
                answer = newGame(parameter);
                break;
        }

        return answer;
    }

    public String invokeAction(String keyword, ArrayList parameters)
    {
        String answer = "";

        switch(keyword) {
            case "talk":
                answer = talk(parameters);
                break;
            case "go":
                answer = go(parameters);
                break;
            case "fight":
                answer = fight(parameters);
                break;
            case "load":
                answer = loadGame(parameters);
                break;
            case "save":
                answer = saveGame(parameters);
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
        return "Going back " + retraceSteps + " location(s): " + gameData.setNewLocation(retraceSteps);
    }

    private String pickup(String objectName)
    {
        if (gameData.getCurrentLocation().containsFixedObject(objectName)) {
            return "You cannot pick up fixed objects!";
        }

        if(!gameData.getCurrentLocation().containsMovableObject(objectName)) {
            return "No such object exists in this room.";
        }

        if(gameData.getPlayerObject().getInventory().addItemToInventory(gameData.getCurrentLocation().getMovableObjectByName(objectName))) {
            gameData.getCurrentLocation().removeObject(objectName);

            return "You picked up " + objectName;
        }

        return "You don't have enough strength to carry that!";
    }

    private String drop(String objectName)
    {
        if (!gameData.getPlayerObject().getInventory().containsObject(objectName)) {
            return "No such object exists in your inventory.";
        }
        
        gameData.getCurrentLocation().andHasObject(gameData.getPlayerObject().getInventory().getFromInventoryByName(objectName));
        gameData.getPlayerObject().getInventory().dropFromInventory(objectName);

        return "You dropped your " + objectName;
    }

    private String fight(ArrayList parameters)
    {
        if(parameters.size() != 3 || !parameters.get(1).equals("with")) {
            return "Invalid syntax used for the 'fight' command.";
        }
            
        if(!gameData.getCurrentLocation().getEnemies().containsKey(parameters.get(0))) {
            return "The enemy entered does not exist!";
        }

        return "fight";
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
    
    private String go(ArrayList parameters)
    {    
        String direction = (String) parameters.get(0);
        
        if (!gameData.getCurrentLocation().isValidExit(direction)) {
            return "Invalid exit!";
        }
        
        if(gameData.getPlayerObject().getInventory().getFromInventoryByName("key") != null) {
            if(gameData.getPlayerObject().getInventory().getFromInventoryByName("key").getPasscode() == gameData.getCurrentLocation().getLocationNeighour(direction).getPasscode()) {
                gameData.getCurrentLocation().getLocationNeighour(direction).unlock();
            }else{
                return "That key doesn't fit that lock";
            }             
        } else if(gameData.getCurrentLocation().getExit(direction).isLocked()) {
            return "That room is locked!";
        }

        return updateLocation(direction);
    }

    private String updateLocation(String direction)
    {
        gameData.setNewLocation(gameData.getCurrentLocation().getExit(direction));

        return gameData.getCurrentLocation().getLongDescription()
               + gameData.getCurrentLocation().getLocationCharacters()
               + gameData.getCurrentLocation().getLocationItems()
               + gameData.getCurrentLocation().getExits();
    }
    
    private String newGame(String parameter)
    {
        if(!parameter.equals("game")) {
            return "Invalid syntax used for the 'new' command.";
        }
        
        return "new";
    }
    
    private String loadGame(ArrayList parameters)
    {
        if(!parameters.get(0).equals("game")) {
            return "Invalid syntax used for the 'load' command.";
        }
        
        if(!gameData.isGameSave((String) parameters.get(1))) {
            return "Invalid game save selected.";
        }

        gameData.setName((String) parameters.get(1));
        
        return "load";
    }
    
    
    private String saveGame(ArrayList parameters)
    {
        if(!parameters.get(0).equals("game") || !parameters.get(1).equals("as")) {
            return "Invalid syntax used for the 'save' command.";
        }
        
        String gameSaveName = (String) parameters.get(2);
        
        if(!gameSaveName.matches("[a-zA-Z]+")) {
            return "Only alphanumerical characters are allowed in the game save name.";
        }
        
        gameData.setName(gameSaveName);

        return "save";
    }
    
    private String talk(ArrayList parameters)
    {
        if(!parameters.get(0).equals("to")) {
            return "Invalid syntax used for the 'talk' command.";
        }
        
        String nameOfPerson = (String) parameters.get(1);
        
        if(!gameData.getCurrentLocation().isValidFriend(nameOfPerson)) {
            return "The friend specified is invalid!";
        }
        
        return gameData.getCurrentLocation().getFriend(nameOfPerson).response();
    }
    
    private String inspect(String objectName)
    {
        if(gameData.getCurrentLocation().containsMovableObject(objectName)) {
            return gameData.getCurrentLocation().getMovableObjectByName(objectName).getObjectDescription();
        }
        
        if(gameData.getCurrentLocation().containsFixedObject(objectName)) {
            return gameData.getCurrentLocation().getFixedObjectByName(objectName).getObjectDescription();
        }
        
        return "The object specified does not exist!";
    }
}