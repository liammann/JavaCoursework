

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LocationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LocationTest
{
    private Location location;
    private Enemy enemy;
    private Friend friend;
    private FixedObject fixed;
    private MovableObject movable;
    /**
     * Default constructor for test class LocationTest
     */
    public LocationTest()
    {
        location = new Location();
        enemy = new Enemy("enemy name");
        // friend = new Friend("friend name");
        // fixed = new FixedObject("fixed name");
        // movable = new MovableObject("movable name");
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        location.addDescription("description text");
        location.andEnemy(enemy);
        location.andFriend("friend name", friend);
        location.andHasObject(fixed);
        location.andHasObject(movable);
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
}
