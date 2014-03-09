
/**
 * Write a description of class Combat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.Math;

public class Combat
{
    // instance variables - replace the example below with your own
    public Character player; 
    private Character enemy;
    public boolean playerDefending; // if true the player choose defending last turn
    public boolean enemyDefending;
    private String output;

    /**
     * Constructor for objects of class Combat
     */
    public Combat(){}
     
 
    public Combat(Character enemy)
    { 
        this.player = Game.player1;
        this.enemy = enemy;  
    }

    /**
     * 
     * If you choose to defend you should lose less health on the next attack 
     * each person takes a turn 
     * strength and health should change the outcome of the fight
     * the bot and player get a choice of attacking or defending
     * get to choose what weapon to attack with.... 
     *       ...each weapon does a different amount of hp and require a certain amount of strength
     * 
     */

    public boolean startFight()
    {
        System.out.println("\n NOW ENTERED COMBAT MODE!!\n You can ethier 'Attack {Weapon}' or 'Defend'\n");
        boolean playerWin = false;
        playerDefending = false;
        enemyDefending = false;


        while(!playerWin){
            playerTurn(enemyDefending);            
            enemyTurn(playerDefending);
            
            if(enemy.getHealth() <= 0){
                playerWin = true;
                break;
            }else if(player.getHealth() <= 0){
                playerWin = false;
                break;
            }

        }
        return playerWin;
    }   
    public void playerTurn(boolean enemyDefending)
    {
        System.out.print("> ");
        CommandsParser commandsParser = Game.commandsParser;
        String command = commandsParser.getCommand();

        if(!command.equals("")) { 
            int hpDealt = 0;
            output = commandsParser.parseCommand(command);
            if(output.equals("attack")){
                hpDealt = attack(enemyDefending);
                playerDefending = false;
            }else{
                hpDealt = defend(enemyDefending);
                playerDefending = true;
            }
           enemy.updateHealth(enemy.getHealth()-hpDealt);
           System.out.print("Player Dealt: "+hpDealt+"\n");
        }else{
            System.out.print("No command entered! \n");
        }
 
        
    }
    
    public void enemyTurn(boolean playerDefending)
    {
         int x = 1 + (int)(Math.random() * ((3 - 1) + 1));
         int hpDealt = 0;

            if(x >= 2 ){
                hpDealt = attack(playerDefending);
                enemyDefending = false;
            }else{
                hpDealt = defend(playerDefending);
                enemyDefending = true;
            }
           player.updateHealth(player.getHealth()-hpDealt);
           System.out.print("Enemy Dealt: "+hpDealt+"\n");

    }
    
    // ========================================================================
    // Current status means if the oppenent last move was to attack or defend
    // ========================================================================
    public int attack(boolean currentStatus)
    {
        int x = 1 + (int)(Math.random() * ((5 - 1) + 1));
        int y = 1 + (int)(Math.random() * ((3 - 1) + 1));
        int damage = 25;

        if (currentStatus){
            return damage-(12+x);
        }
        return damage+(x*y);
        
    }
    public int defend(boolean currentStatus)
    {
        int x = 1 + (int)(Math.random() * ((5 - 1) + 1));
        int y = 1 + (int)(Math.random() * ((5 - 1) + 1));
        int damage = 2;
        
        if (currentStatus){
            return x;
        }
        return damage+(x);
        
    
    }
}