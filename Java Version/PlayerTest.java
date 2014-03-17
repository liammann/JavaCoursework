

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
    private MovableObject axe;
    /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest()
    {
        player = Player.create("playername");
        axe = MovableObject.create("axe")
                           .withDescription("Brutal axe")
                           .andWeight(2)
                           .withWeaponModifier(2.4);
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
        System.out.println(player.andHasWeapon(axe));
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
        System.out.println(player.getStrength());
        System.out.println(player.getInventory().currentInventory());
    }
}
