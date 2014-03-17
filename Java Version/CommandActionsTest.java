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
    /**
     * Default constructor for test class CommandActionsTest
     */
    public CommandActionsTest()
    {
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
    public void commands()
    {
        Game game1 = new Game();
    }

    @Test
    public void CommandActions()
    {
    }

    @Test
    public void save()
    {
        CommandActions commandActions = new CommandActions();
        String keyword, response;
        ArrayList<String> arguments;
        
        keyword = "save";
        arguments = new ArrayList<String>();
        arguments.add("game");
        arguments.add("as");
        arguments.add("gameName");
        response = commandActions.invokeAction(keyword, arguments);
        assertEquals("save", response);

        keyword = "save";
        arguments = new ArrayList<String>();
        arguments.add("games");
        arguments.add("as");
        arguments.add("gameName");
        response = commandActions.invokeAction(keyword, arguments);
        assertEquals("save", response);
    }
}

