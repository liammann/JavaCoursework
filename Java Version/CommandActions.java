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
                // answer = pickup(parameter); TO DO
                break;
<<<<<<< HEAD
            case "drop":
                //answer = drop(parameter);  TO DO
=======
>>>>>>> FETCH_HEAD
            case "attack":
                answer = attack(parameter);
                break;
            case "fight":
                answer = fight(parameter);
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

<<<<<<< HEAD
    /*private String pickup(String item)
    {
        // put object in inventory and remove object from location
        gameData.addItemToInventory(item);
        gameData.getCurrentLocation().removeItem(item);
        //return "Item " + item.getObjectName() + " has been picked up!";
    }
    
    private String drop(String item)
    {
        //
    }*/
    
    private String fight(String who) 
=======
    private String pickup(String item)
    {
        // put object in inventory and remove object from location
        //if (GameData.getCurrentLocation().contains(item))
        //{
        //    playerInventory.addItemToInventory(item);
        //    Inventory.removeItem(item);
        //    return "Item " + objectName + " has been picked up!";
        //}
        return "mummy";
    }
     private String fight(String who) 
>>>>>>> FETCH_HEAD
    {
        for(Character character : gameData.getCurrentLocation().getArraryLocationCharacters()) {
            if(character.getName().equals(who)){
                Combat fight = new Combat(character);
                boolean playerWins = fight.startFight();
                return "You have Defeated "+who;
            }

        }
        return "Fight Who?";
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
            return "Invalid syntax used...2";
        }
        
        return "new";
    }
    
    private String loadGame(ArrayList parameters)
    {
        if(!parameters.get(0).equals("game")) {
            return "Invalid syntax used...";
        }
        
        if(!gameData.isValidGameSave((String) parameters.get(1))) {
            return "Invalid game save!";
        }
        
        return "load";
        
    }
    
    
    private String saveGame(ArrayList parameters)
    {
        if(!parameters.get(0).equals("game") || !parameters.get(1).equals("as")) {
            return "Invalid syntax used...";
        }
        
        // gameData.saveGame();
        return "The game has successfully been saved as " + parameters.get(2);
    }
    private String attack(String weapon)
    {
        if(weapon.equals("")) {
            return "";
        }
        
        return weapon;
    }
}