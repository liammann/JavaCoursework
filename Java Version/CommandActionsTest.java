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
        args = new ArrayList<String>();
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
        args.clear();

        args.add("game");
        args.add("as");
        args.add("gameName");
        assertEquals("save", commandActions.invokeAction(keyword, args));
        args.clear();
    }

    @Test
    public void loadGame()
    {
        keyword = "load";
        
        args.add("games");
        args.add("gameName");
        assertEquals("Invalid syntax used for the 'load' command.", commandActions.invokeAction(keyword, args));
        args.clear();

        args.add("game");
        args.add("game Name");
        assertEquals("Invalid game save selected.", commandActions.invokeAction(keyword, args));
        args.clear();
    }

    @Test
    public void newGame()
    {
        keyword = "new";

        arg = "game";
        assertEquals("new", commandActions.invokeAction(keyword, arg));

        arg = "games";
        assertEquals("Invalid syntax used for the 'new' command.", commandActions.invokeAction(keyword, arg));
    }

    @Test
    public void inspect()
    {
        //keyword = "inspect";
        //arg = "objectA";
        //assertEquals("The object specified does not exist!", commandActions.invokeAction(keyword, arg));
    }

    @Test
    public void talk()
    {
        keyword = "talk";

        args.add("too");
        args.add("person");
        assertEquals("Invalid syntax used for the 'talk' command.", commandActions.invokeAction(keyword, args));
        args.clear();

        //args.clear();
        //args.add("to");
        //args.add("PersonA");
        //assertEquals("The friend specified is invalid!", commandActions.invokeAction(keyword, args));
    }
    
    @Test
    public void go()
    {
        keyword = "go";

        args.add("south");
        args.add("unlocks");
        assertEquals("Invalid syntax used for the 'go' command.", commandActions.invokeAction(keyword, args));
        args.clear();

        //args.add("west");
        //args.add("unlock");
        //assertEquals("Invalid exit!", commandActions.invokeAction(keyword, arg));
        //args.clear();
    }
    
    @Test
    public void take()
    {
        keyword = "take";

        args.add("health");
        args.add("fromm");
        args.add("FriendA");
        assertEquals("Invalid syntax used for the 'take' command.", commandActions.invokeAction(keyword, args));

        //args.add("health");
        //args.add("fromm");
        //args.add("FriendA");
        //assertEquals("The friend entered does not exist!", commandActions.invokeAction(keyword, args));
        //args.clear();

        //args.add("health");
        //args.add("from");
        //args.add("FriendA");
        //assertEquals("take", commandActions.invokeAction(keyword, args));
    }
    
    @Test
    public void fight()
    {
        keyword = "fight";
        
        args.add("EnemyA");
        args.add("withh");
        args.add("weapon");
        assertEquals("Invalid syntax used for the 'fight' command.", commandActions.invokeAction(keyword, args));
    }
}
