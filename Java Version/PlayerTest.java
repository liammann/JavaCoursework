

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PlayerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlayerTest
{
    private Player player;
    /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest()
    {
        player = new Player("playername");
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        System.out.println(player.hasStrength(5));
        System.out.println(player.withHealth(5));
        System.out.println(player.andHasWeapon(sword));
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        System.out.println(player.getName());
        System.out.println(player.getHealth());
        System.out.println(player.getStreangth());
        System.out.println(player.getInventory().currentInventory());
    }
}
