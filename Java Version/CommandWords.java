import java.util.ArrayList;
import java.util.HashMap;

/**
 * CommandWords is a class that stores every command word in the game. It categorises
 * command words by the number of parameters they can have, and also holds the
 * documentation description of each command word. It is a Singleton class because only
 * one instance of it is required in memory
 * 
 * @author (Thomas Punt) 
 * @version (1.01)
 */
public class CommandWords
{
    private ArrayList<ArrayList<String>> validCommands;
    private ArrayList<String> preGameCommands;
    private HashMap<String, String> commandWordsDocumentation;
    private static CommandWords instance = null;

    /**
     * This method is used to ensure that only one object of the CommandWords class
     * is in memory at any single time (controlled instantiation). It checks to see
     * if the staticly set field 'instance' is null; if so, create a new CommandWords
     * object, if not, then return the current instance (held in the 'instance' field).
     * 
     * @return           the instance of the CommandWords class
     */
    public static CommandWords getInstance()
    {
        if(instance == null) {
            instance = new CommandWords();
        }

        return instance;
    }

    /**
     * Constructor for CommandWords class to initialise the class fields
     */
    private CommandWords()
    {
        validCommands = new ArrayList<ArrayList<String>>();
        commandWordsDocumentation = new HashMap<String, String>();
        preGameCommands = new ArrayList<String>();

        validCommands.add(new ArrayList<String>());
        validCommands.get(0).add("help");
        validCommands.get(0).add("quit");
        validCommands.get(0).add("back");
        validCommands.get(0).add("manual");
        validCommands.get(0).add("inventory");

        validCommands.add(new ArrayList<String>());
        validCommands.get(1).add("go");
        validCommands.get(1).add("back");
        validCommands.get(1).add("use");
        validCommands.get(1).add("pickup");
        validCommands.get(1).add("drop");
        validCommands.get(1).add("manual");
        validCommands.get(1).add("new");
        validCommands.get(1).add("inspect");

        validCommands.add(new ArrayList<String>());
        validCommands.get(2).add("load");
        validCommands.get(2).add("talk");
        validCommands.get(2).add("go");

        validCommands.add(new ArrayList<String>());
        validCommands.get(3).add("save");        
        validCommands.get(3).add("take");
        validCommands.get(3).add("fight");

        preGameCommands.add("load");
        preGameCommands.add("new");
        preGameCommands.add("quit");

        commandWordsDocumentation.put("help", "Ask for help (no parameters)");
        commandWordsDocumentation.put("quit", "Quit the game (no parameters)");
        commandWordsDocumentation.put("inventory", "See your inventory (no parameters)");
        commandWordsDocumentation.put("back", "Back track your location. Usage: 'back {n}', where 'n' is optional (defaults to 1)");
        commandWordsDocumentation.put("manual", "View all commands or a specific command. Usage: 'manual {command}', where 'command' is optional (defaults to view all)");
        commandWordsDocumentation.put("go", "Go to another location and optionally unlock that location. Usage: 'go {direction}' or 'go {direction} unlock' (must have the key in your inventory)");
        commandWordsDocumentation.put("pickup", "Pickup an object by passing the object name as a parameter. Usage: 'pickup {object}'");
        commandWordsDocumentation.put("inspect", "Insepct an object by passing the object name as a parameter. Usage: 'inspect {object_name}'");
        commandWordsDocumentation.put("new", "Start a new game. Usage: 'new game'");
        commandWordsDocumentation.put("talk", "Talk to a player in the game. Usage: 'talk to {player_name}'");
        commandWordsDocumentation.put("load", "Load a saved game. Usage: 'load game {game_save}'");
        commandWordsDocumentation.put("save", "Save the current state of the game.   Usage: 'save game as {game_name}'");
        commandWordsDocumentation.put("fight", "Fight an enemy in the current room. Usage: 'fight {enemy_name} with {weapon_name}'");
    }

    /**
     * This predicate method is used to validate whether the command passed to it exists
     * 
     * @param  keyword   the keyword of the entered command
     * @return           a boolean depending upon the existence of the command word
     */
    public boolean commandExists(String keyword)
    {
        for(ArrayList<String> commands : validCommands) {
            if(commands.contains(keyword)) {
                return true;
            }
        }

        return false;
    }

    /**
     * This predicate method is used to validate whether the command passed to it has a
     * valid syntax (according to argument word count of the keyword)
     * 
     * @param  commands  the individual words in the entered commmand
     * @return           a boolean depending upon whether the command is valid
     */
    public boolean isValidCommand(ArrayList commands)
    {
        int commandsCount = commands.size();

        if(!validCommands.get(commandsCount-1).isEmpty() && validCommands.get(commandsCount-1).contains(commands.get(0))) {
            return true;
        }

        return false;
    }

    /**
     * This predicate method is used to validate whether the command passed to it is a
     * pregame command
     * 
     * @param  keyword  the keyword of the command entered
     * @return          a boolean depending upon whether the command is a pregame command
     */
    public boolean isPreGameCommand(String keyword)
    {
        if(preGameCommands.contains(keyword)) {
            return true;
        }

        return false;
    }

    /**
     * This method is used to output the keywords in the manual according to parameter count
     * 
     * @return           a sorted list of all commands
     */
    public String manual()
    {
        int commandLength = validCommands.size();
        String commands = "";
        int wordCount;

        for(int i = 0;i < commandLength;++i) {
            wordCount = i + 1;
            commands += wordCount + " word commands:\n";

            for(String command : validCommands.get(i)) {
                commands += " | " + command;
            }
            commands += " |" + (!(i < commandLength - 1) ? "" : "\n");
        }

        return commands;
    }

    /**
     * This method is used to lookup a specified keyword in the manual
     * 
     * @param  keyword  the keyword of the command entered
     * @return          information about a certain command
     */
    public String manual(String keyword)
    {
        if(!commandWordsDocumentation.containsKey(keyword)) {
            return "Specified command word is invalid";
        }

        return (String) commandWordsDocumentation.get(keyword);
    }
}
