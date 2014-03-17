import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CommandActionsTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CommandActionsTest
{
    CommandActions commandActions;
    String keyword, arg;
    ArrayList<String> args;
        
    public CommandActionsTest()
    {
        commandActions = new CommandActions();
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
    public void saveGame()
    {
        keyword = "save";
        args = new ArrayList<String>();

        args.add("game");
        args.add("as");
        args.add("gameName");
        assertEquals("save", commandActions.invokeAction(keyword, args));

        args.clear();
        args.add("games");
        args.add("as");
        args.add("gameName");
        assertEquals("Invalid syntax used for the 'save' command.", commandActions.invokeAction(keyword, args));

        args.clear();
        args.add("game");
        args.add("a");
        args.add("gameName");
        assertEquals("Invalid syntax used for the 'save' command.", commandActions.invokeAction(keyword, args));

        args.clear();
        args.add("games");
        args.add("a");
        args.add("gameName");
        assertEquals("Invalid syntax used for the 'save' command.", commandActions.invokeAction(keyword, args));

        args.clear();
        args.add("game");
        args.add("as");
        args.add("game Name");
        assertEquals("Only alphanumerical characters are allowed in the game save name.", commandActions.invokeAction(keyword, args));
    }

    @Test
    public void loadGame()
    {
        keyword = "load";
        args = new ArrayList<String>();
        
        args.add("games");
        args.add("gameName");
        assertEquals("Invalid syntax used for the 'load' command.", commandActions.invokeAction(keyword, args));

        args.clear();
        args.add("game");
        args.add("game Name");
        assertEquals("Invalid game save selected.", commandActions.invokeAction(keyword, args));
        
        args.clear();
        args.add("game");
        args.add("test");
        assertEquals("load", commandActions.invokeAction(keyword, args));
    }

    @Test
    public void newGame()
    {
        keyword = "new";
        args = new ArrayList<String>();
        
        args.add("game");
        assertEquals("new", commandActions.invokeAction(keyword, args));

        args.clear();
        args.add("games");
        assertEquals("Invalid syntax used for the 'new' command.", commandActions.invokeAction(keyword, args));
    }
}
