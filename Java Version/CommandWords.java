/*
 * Shift constructor stuff elsewhere and make fields static
 * because CommandWords class is being instantiated in multiple
 * places, causing the class to create 2 versions of the same
 * command words and documentation.
 *
 * Maybe also extend the CommandWords class with a Documentation
 * class?
 */

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;

public class CommandWords
{
	private ArrayList<ArrayList<String>> validCommands;
	private ArrayList<String> preGameCommands;
	private HashMap<String, String> commandWordsDocumentation;

	public CommandWords()
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
		validCommands.get(1).add("pickup");
		validCommands.get(1).add("fight");		
		validCommands.get(1).add("attack");
		validCommands.get(1).add("manual");
		validCommands.get(1).add("new");

		validCommands.add(new ArrayList<String>());
		validCommands.get(2).add("load");
		
		validCommands.add(new ArrayList<String>());
		validCommands.get(3).add("save");
		
		preGameCommands.add("load");
		preGameCommands.add("new");
		preGameCommands.add("quit");

		commandWordsDocumentation.put("help", "Ask for help (no parameters)");
		commandWordsDocumentation.put("quit", "Quit the game (no parameters)");
		commandWordsDocumentation.put("inventory", "See your inventory (no parameters)");
		commandWordsDocumentation.put("back", "Back track your location. Usage: 'back {n}', where 'n' is optional (defaults to 1)");
		commandWordsDocumentation.put("manual", "View all commands or a specific command. Usage: 'manual {command}', where 'command' is optional (defaults to view all)");
		commandWordsDocumentation.put("go", "Go to another location by passing the exit direction as a paramter. Usage: 'go {direction}'");
		commandWordsDocumentation.put("pickup", "Pickup an object by passing the object name as a parameter. Usage: 'pickup {object}'");
		commandWordsDocumentation.put("new", "Start a new game. Usage: 'new game'");
		commandWordsDocumentation.put("load", "Load a saved game. Usage: 'load game {game_save}'");
		commandWordsDocumentation.put("save", "Save the current state of the game. Usage: 'save game as {game_name}'");
	}

	public boolean commandExists(String command)
	{
		for(ArrayList<String> commands : validCommands) {
			if(commands.contains(command)) {
				return true;
			}
		}

		return false;
	}

	public boolean isValidCommand(ArrayList commands)
	{
		int commandsCount = commands.size();

		if(!validCommands.get(commandsCount-1).isEmpty() && validCommands.get(commandsCount-1).contains(commands.get(0))) {
			return true;
		}

		return false;
	}
	
	public boolean isPreGameCommand(String command)
	{
	    if(preGameCommands.contains(command)) {
	        return true;
	    }
	    
	    return false;
	}

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

	public String manual(String command)
	{
		if(commandWordsDocumentation.containsKey(command)) {
			return (String) commandWordsDocumentation.get(command);
		}
			
		return "Specified command word is invalid";
	}
}
