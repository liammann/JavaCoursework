import java.util.ArrayList;

/**
 * CommandActions is a class that delegates the entered command to invoking its
 * respective command method.
 * 
 * @author (Thomas Punt) 
 * @version (1.11)
 */
public class CommandActions
{
    private CommandWords commandWords;
    private GameData gameData;

    /**
     * Constructor for CommandActions class to initialise the class fields
     */
    public CommandActions()
    {
        commandWords = CommandWords.getInstance();
        gameData = GameData.getInstance();
    }

    /**
     * This method takes the delegated command keyword and invokes the respective function
     * 
     * @param  keyword   the command keyword entered
     * @return           the return value of the invoked function
     */
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
                answer = inventory();
                break;
        }

        return answer;
    }

    /**
     * This method takes the delegated command keyword and the single argument associated
     * with the command keyword, and invokes the respective function
     * 
     * @param  keyword     the command keyword entered
     * @param  argument    the argument spcified with the keyword
     * @return             the return value of the invoked function
     */
    public String invokeAction(String keyword, String argument)
    {
        String answer = "";

        switch(keyword) {
            case "go":
                answer = go(argument);
                break;
            case "back":
                answer = back(argument);
                break;
            case "manual":
                answer = commandWords.manual(argument);
                break;
            case "use":
                answer = use(argument);
                break;
            case "pickup":
                answer = pickup(argument);
                break;
            case "drop":
                answer = drop(argument);
                break;
            case "inspect":
                answer = inspect(argument);
                break;
            case "new":
                answer = newGame(argument);
                break;
        }

        return answer;
    }

    /**
     * This method takes the delegated command keyword and the arguments associated
     * with the command keyword, and invokes the respective function
     * 
     * @param  keyword     the command keyword entered
     * @param  arguments   the arguments specified with the keyword
     * @return             the return value of the invoked function
     */
    public String invokeAction(String keyword, ArrayList arguments)
    {
        String answer = "";

        switch(keyword) {
            case "talk":
                answer = talk(arguments);
                break;
            case "go":
                answer = go(arguments);
                break;
            case "fight":
                answer = fight(arguments);
                break;
            case "load":
                answer = loadGame(arguments);
                break;
            case "save":
                answer = saveGame(arguments);
                break;
        }

        return answer;
    }

    /**
     * This method is used to quit the game or pregame
     * 
     * @return     the word "quit"
     */
    private String quit()
    {
        return "quit";
    }

    /**
     * This method is used to give help to the player upon request
     * 
     * @return     the task of the game and how to use its commands
     */
    private String help()
    {
        return getTask() + "\nType 'manual' to view a list of commands, or 'manual {command}' to learn about a specific command.";
    }

    /**
     * This method is used to get the game's task
     * 
     * @return     the task of the game
     */
    private String getTask()
    {
        return "Your task is to...";
    }

    /**
     * This method is used to update the game location by going back one location
     * 
     * @return     a message of either an invalid location specified or the new location description
     */
    private String back()
    {
        return gameData.setNewLocation(1)
               + locationDetails();
    }

    /**
     * This method is used to update the game location by going back a specified number of locations
     * 
     * @param   retraceSteps    the number of steps to go back
     * @return                  a message of the number of locations going back and the new location description
     */
    private String back(String retraceSteps)
    {
        int steps = Integer.parseInt(retraceSteps);

        return gameData.setNewLocation(steps)
               + locationDetails();
    }

    /**
     * This method is used to pickup an object, given the object's name
     * 
     * @param   objectName    the name of the object to pickup
     * @return                a message of the attempt to pick up the object
     */
    private String pickup(String objectName)
    {
        if (gameData.getCurrentLocation().containsFixedObject(objectName)) {
            return "You cannot pick up fixed objects!";
        }

        if(!gameData.getCurrentLocation().containsMovableObject(objectName)) {
            return "No such object exists in this room.";
        }

        MovableObject obj = gameData.getCurrentLocation().getMovableObjectByName(objectName);
        if(gameData.getPlayerObject().getInventory().addItemToInventory(obj)) {
            gameData.getCurrentLocation().removeObject(objectName);

            return "You picked up " + objectName;
        }

        return "You don't have enough strength to carry that!";
    }   

    /**
     * This method is used to consume the specified potion (provided it is in their inventory)
     * which will update the current player's health
     * and drop the potion from the player's inventory
     * 
     * @param   potionName    the potion to be consumed
     * @return                a message of the number of locations going back and the new location description
     */
    private String use(String potionName)
    {
        if(gameData.getPlayerObject().getInventory().containsObject(potionName)) {
            int playerHealth = gameData.getPlayerObject().getHealth();
            int healthPoints = gameData.getPlayerObject().getInventory().getFromInventoryByName(potionName).getHealthPotion();
            
            gameData.getPlayerObject().updateHealth(playerHealth + healthPoints);
            gameData.getPlayerObject().getInventory().dropFromInventory(potionName);

            return "You added " + healthPoints + " health points \nThis gives you "
                   + gameData.getPlayerObject().getHealth() + " health points in total";
        }

        return "Use what?";
    }

    /**
     * This method is used to drop a specified MovableObject from the player's inventory
     * (provided it is in there). It will first update the location with the dropped object,
     * and will then remove the object from the player's inventory
     * 
     * @param   potionName    the name of the object to be dropped
     * @return                a message of whether the object was dropped or not
     */
    private String drop(String objectName)
    {
        if (!gameData.getPlayerObject().getInventory().containsObject(objectName)) {
            return "No such object exists in your inventory.";
        }
        
        gameData.getCurrentLocation().andHasObject(gameData.getPlayerObject().getInventory().getFromInventoryByName(objectName));
        gameData.getPlayerObject().getInventory().dropFromInventory(objectName);

        return "You dropped your " + objectName;
    }

    /**
     * This method is used to check the validity of the entered fight command
     * 
     * @param   arguments    the list of arguments supplied with the fight keyword
     * @return               a message that is determined upon the validity of the input command
     */
    private String fight(ArrayList arguments)
    {
        if(arguments.size() != 3 || !arguments.get(1).equals("with")) {
            return "Invalid syntax used for the 'fight' command.";
        }
            
        if(gameData.getCurrentLocation().getEnemyByName((String) arguments.get(0)).getName() ==  null) {
            return "The enemy entered does not exist!";
        }
       
        return "fight";
    }

    /**
     * This method is used to validate the specified location direction the player would
     * like to travel in, and then update player's location if the direction validates and
     * the location to go to is not locked
     * 
     * @param   direction    the direction to travel in
     * @return               a message that is determined upon the validity of the input command
     */
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

    /**
     * ...
     * 
     * @param   arguments    the arguments specified for the go keyword
     * @return               a message that is determined upon the validity of the input command
     */
    private String go(ArrayList arguments)
    {
        if(arguments.size() != 2 || !arguments.get(1).equals("unlock")) {
            return "Invalid syntax used for the 'go' command.";
        }
        
        String direction = (String) arguments.get(0);
        
        if (!gameData.getCurrentLocation().isValidExit(direction)) {
            return "Invalid exit!";
        }
        
        if(!gameData.getCurrentLocation().getExit(direction).isLocked()) {
            return "That exit is not locked!";
        }
        
        if(!gameData.getPlayerObject().getInventory().containsObject("key")) {
            return "You have no key in your inventory!";
        }
        
        int keyPasscode = gameData.getPlayerObject().getInventory().getFromInventoryByName("key").getPasscode();
        int locationPasscode = gameData.getCurrentLocation().getLocationNeighour(direction).getPasscode();

        if(keyPasscode != locationPasscode) {
            return "Your key doesn't fit that lock";
        }

        gameData.getCurrentLocation().getLocationNeighour(direction).unlock();

        return updateLocation(direction);
    }

    private String updateLocation(String direction)
    {
        gameData.setNewLocation(gameData.getCurrentLocation().getExit(direction));

        return locationDetails();
    }
    
    private String locationDetails()
    {
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

    private String inventory()
    {
        return gameData.getPlayerObject().getInventory().currentInventory();
    }
}