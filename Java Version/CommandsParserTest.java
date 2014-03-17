

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CommandsParserTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CommandsParserTest
{
    /**
     * Default constructor for test class CommandsParserTest
     */
    public CommandsParserTest()
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
    public void parseCommand()
    {
        CommandsParser commands1 = new CommandsParser();
        assertEquals("No command entered!", commands1.parseCommand(""));
        assertEquals("Empty commands are invalid!", commands1.parseCommand(" "));
        assertEquals("The command entered does not exist!", commands1.parseCommand("InvalidCommandWordHere"));
        assertEquals("Invalid pre-game command!", commands1.parseCommand("back"));
        assertEquals("The command entered has an incorrect syntax. Please refer to the commands manual.", commands1.parseCommand("load game"));
        assertEquals("Invalid game save selected.", commands1.parseCommand("load game InvalidGameSaveHere"));
        assertEquals("new", commands1.parseCommand("new game"));
    }
}

