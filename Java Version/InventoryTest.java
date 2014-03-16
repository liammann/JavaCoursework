

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class InventoryTest.
 *
 * @author  liam mann
 * @version (a version number or a date)
 */
public class InventoryTest
{
    private Inventory inventory;
    /**
     * Default constructor for test class InventoryTest
     */
    public InventoryTest()
    {
        inventory = new Inventory();
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {     
        MovableObject sword;
        Player player1;

        sword = MovableObject.create("sword")
                             .withDescription("Steal sword")
                             .andWeight(3)
                             .withWeaponModifier(3.1);

        player1 = Player.create("Player 1")
                        .hasStrength(100)
                        .withHealth(100)
                        .andHasWeapon(sword);

        
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


