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
            case "defend":
                 //answer = defend(); //???
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
            case "attack":
                answer = attack(parameter);
                break;
            case "fight":
                answer = fight(parameter);
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

    private String pickup(String item)
    {
        MovableObject thisObject = gameData.getCurrentLocation().getObjectByName(item);
        gameData.getPlayer1Object().getInventory().addItemToInventory(thisObject.getObjectName(), thisObject);
        gameData.getCurrentLocation().removeItem(item);
        return "You picked up " + item;
    }

    private String drop(String item)
    {
        MovableObject thisObject = gameData.getPlayer1Object().getInventory().getFromInventoryByName(item);
        gameData.getCurrentLocation().addMovableObject(item, thisObject);
        gameData.getPlayer1Object().getInventory().dropFromInventory(item);
        return "you dropped " + item;
    }

    private String fight(String who)
    {
        return "fight";
    }
    private String fight(ArrayList parameters)
    {
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

    private String updateLocation(String direction)
    {
        gameData.getCurrentLocation().removeCharacters();
        gameData.setNewLocation(gameData.getCurrentLocation().getExit(direction));

        return gameData.getCurrentLocation().getLongDescription()+                
                gameData.getCurrentLocation().getLocationCharacters()+
                gameData.getCurrentLocation().getLocationItems();
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
        
        if(!gameData.isValidGameSave((String) parameters.get(1))) {
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
    private String attack(String weapon)
    {
        if(weapon.equals("")) {
            return ""; // maybe redundant?
        }
        
        return weapon;
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
    
    private String inspect(String parameter)
    {
        // return gameData.getCurrentLocation().
        return "";
    }
}