import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CommandWordsTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CommandWordsTest
{
    private CommandWords commandWords;
    private ArrayList<String> commands;

    public CommandWordsTest()
    {
        commandWords = CommandWords.getInstance();
        commands = new ArrayList<String>();
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void commandExist()
    {
        assertEquals(true, commandWords.commandExists("help"));
        assertEquals(true, commandWords.commandExists("quit"));
        assertEquals(true, commandWords.commandExists("back"));
        assertEquals(true, commandWords.commandExists("manual"));
        assertEquals(true, commandWords.commandExists("inventory"));
        assertEquals(true, commandWords.commandExists("go"));
        assertEquals(true, commandWords.commandExists("use"));
        assertEquals(true, commandWords.commandExists("pickup"));
        assertEquals(true, commandWords.commandExists("drop"));
        assertEquals(true, commandWords.commandExists("new"));
        assertEquals(true, commandWords.commandExists("inspect"));
        assertEquals(true, commandWords.commandExists("load"));
        assertEquals(true, commandWords.commandExists("talk"));
        assertEquals(true, commandWords.commandExists("go"));
        assertEquals(true, commandWords.commandExists("save"));
        assertEquals(true, commandWords.commandExists("take"));
        assertEquals(true, commandWords.commandExists("fight"));
        assertEquals(false, commandWords.commandExists("An Invalid Command"));
    }

    @Test
    public void isValidCommand()
    {
        // should be valid
        commands.add(0, "help");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "quit");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "back");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "manual");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "inventory");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "go");
        commands.add(1, "direction");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "back");
        commands.add(1, "number of spaces to go back");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "use");
        commands.add(1, "health");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "pickup");
        commands.add(1, "movable object name");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "drop");
        commands.add(1, "movable object name");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "manual");
        commands.add(1, "keyword");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "new");
        commands.add(1, "game");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "inspect");
        commands.add(1, "object name");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "load");
        commands.add(1, "game");
        commands.add(2, "game name");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "talk");
        commands.add(1, "to");
        commands.add(2, "friend name");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "go");
        commands.add(1, "direction");
        commands.add(2, "key");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "save");
        commands.add(1, "game");
        commands.add(2, "as");
        commands.add(3, "game name");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "take");
        commands.add(1, "movable object name");
        commands.add(2, "from");
        commands.add(3, "friend name");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "fight");
        commands.add(1, "enemy name");
        commands.add(2, "with");
        commands.add(3, "sword");
        assertEquals(true, commandWords.isValidCommand(commands));
        commands.clear();
        
        // should be invalid
        commands.add(0, "help");
        commands.add(1, "...");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "quit");
        commands.add(1, "...");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "inventory");
        commands.add(1, "...");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "go");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "back");
        commands.add(1, "number of spaces to go back");
        commands.add(2, "...");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "use");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "pickup");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "drop");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "manual");
        commands.add(1, "keyword");
        commands.add(2, "...");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "new");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "inspect");
        commands.add(1, "object name");
        commands.add(2, "...");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "load");
        commands.add(1, "game");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "talk");
        commands.add(1, "to");
        commands.add(2, "friend name");
        commands.add(3, "...");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "go");
        commands.add(1, "direction");
        commands.add(2, "...");
        commands.add(3, "...");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "save");
        commands.add(1, "game");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "take");
        commands.add(1, "movable object name");
        commands.add(2, "from");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
        commands.add(0, "fight");
        assertEquals(false, commandWords.isValidCommand(commands));
        commands.clear();
    }
}
