import java.util.ArrayList;
import java.util.Scanner;

public class CommandsParser
{
    private CommandWords commandWords;
    private CommandActions commandActions;
    private Scanner reader;
    private GameData gameData;

    public CommandsParser()
    {
        commandWords = new CommandWords();
        commandActions = new CommandActions();
        reader = new Scanner(System.in);
        gameData = new GameData();
    }

    public String getCommand()
    {
        return reader.nextLine();
    }

    public String parseCommand(String inputCommand)
    {
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