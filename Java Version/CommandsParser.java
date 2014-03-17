import java.util.ArrayList;
import java.util.Scanner;

/**
 * CommandsParser is a class that reads the input line, tokenises the sequence
 * of command inputs, validates these command inputs, and then passes on these
 * values to the invokeAction() method in the CommandActions class.
 * 
 * @author (Thomas Punt) 
 * @version (1.01)
 */
public class CommandsParser
{
    private static CommandWords commandWords;
    private static CommandActions commandActions;
    private static Scanner reader;
    private GameData gameData;

    /**
     * Constructor for CommandActions class to initialise the class fields
     */
    public CommandsParser()
    {
        commandWords = CommandWords.getInstance();
        commandActions = new CommandActions();
        reader = new Scanner(System.in);
        gameData = GameData.getInstance();
    }

    /**
     * This method uses the Scanner class to read the entered input line and return it
     * 
     * @return     the input line
     */
    public String getCommand()
    {
        return reader.nextLine().toLowerCase();
    }

    /**
     * This method inspects the integrity of the input command by validating it
     * with a number of tests. Provided the input passes the integrity checks, it
     * passes on the input to the invokeActions() method in CommandActions
     * 
     * @param  inputCommand   the input line entered
     * @return                either an error string if a check fails or the return value of invokeAction
     */
    public String parseCommand(String inputCommand)
    {
        if(inputCommand.equals("")) {
            return "No command entered!";
        }

        ArrayList<String> commands = new ArrayList<String>();

        for(String command : inputCommand.split(" ")) {
            commands.add(command);
        }

        if(commands.size() == 0) {
            return "Empty commands are invalid!";
        }

        String keyword = commands.get(0);

        if(!commandWords.commandExists(keyword)) {
            return "The command entered does not exist!";
        }

        if(gameData.hasGameStarted() == false && !commandWords.isPreGameCommand(keyword)) {
            return "Invalid pre-game command!";
        }

        if(!commandWords.isValidCommand(commands)) {
            return "The command entered has an incorrect syntax. Please refer to the commands manual.";
        }

        if(commands.size() > 1) {
            commands.remove(0);

            if(commands.size() == 1) {
                return commandActions.invokeAction(keyword, commands.get(0));
            }

            return commandActions.invokeAction(keyword, commands);
        }

        return commandActions.invokeAction(keyword);
    }
}