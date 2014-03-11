import java.lang.Math;

public class Combat
{
    private Player player; 
    private Enemy enemy;
    private GameData gameData;
    private boolean playerDefending; // if true the player choose defending last turn
    private boolean enemyDefending;

      
    public Combat(Enemy enemy,  GameData gameData)
    { 
       this.gameData = gameData;
       this.player = gameData.getPlayer1Object();
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
            if(enemy.getHealth() <= 0){
                playerWin = true;
                break;
            }
            
            enemyTurn(playerDefending);
            
            if(player.getHealth() <= 0){
                playerWin = false;
                break;
            }

        }
        return playerWin;
    }   
    public void playerTurn(boolean enemyDefending)
    {
        System.out.print("> ");

        String command = gameData.commandsParser.getCommand();
        
        if(!command.equals("")){ 
            int hpDealt = 0;
            String output = gameData.commandsParser.parseCommand(command);

            if(command.contains("attack")){
                for (MovableObject weapon : player.getInventory().playerWeapons()){
                    if(command.equals("attack "+weapon.getObjectName().toLowerCase())){
                        hpDealt = attack(enemyDefending, weapon);
                        System.out.print("    "+"You attack the evil "+enemy.getName()+" with your mighty "+weapon.getObjectName().toLowerCase()+" and deal "+hpDealt+" health points. \n");
                        playerDefending = false;
                    }else{
                        hpDealt = attack(enemyDefending);
                        System.out.print("    "+"You attack the evil "+enemy.getName()+" and deal "+hpDealt+" health points. \n");
                        playerDefending = false;
                    }
                }
            }else if(command.equals("defend")){
                hpDealt = defend(enemyDefending);
                playerDefending = true;
                System.out.print("    "+"you ready your shield \n");
            }else{
                System.out.print("    "+"You stand there and get attacked.  Attack {Weapon} or Defend \n   ");
            }
            enemy.updateHealth(enemy.getHealth()-hpDealt);
        }else{
            System.out.print("No command entered! \n ");
        }
 
        
    }
    
    public void enemyTurn(boolean playerDefending)
    {
         int x = 1 + (int)(Math.random() * ((3 - 1) + 1));
         int hpDealt = 0;

            if(x >= 2 ){
                hpDealt = attack(playerDefending, enemy.getWeapon());
                System.out.print("    "+enemy.getName()+" dealt "+hpDealt+" with his "+enemy.getWeapon().getObjectDescription().toLowerCase()+" \n");
                enemyDefending = false;
            }else{
                hpDealt = defend(playerDefending);
                enemyDefending = true;
                System.out.print("    "+enemy.getName()+" readies his shield for your next attack \n");
            }
           player.updateHealth(player.getHealth()-hpDealt);


    }
    
    // ========================================================================
    // Current status means if the oppenent last move was to attack or defend
    // ========================================================================
    public int attack(boolean currentStatus)
    {
        int x = 1 + (int)(Math.random() * ((5 - 1) + 1));
        int y = 1 + (int)(Math.random() * ((3 - 1) + 1));
        int damage = 15;

        if (currentStatus){
            return damage -= damage - x;
        }
        return damage+(x*y);
        
    }
    public int attack(boolean currentStatus, MovableObject weapon)
    {
        int x = 1 + (int)(Math.random() * ((5 - 1) + 1));
        int y = 1 + (int)(Math.random() * ((3 - 1) + 1));
        int damage = (int) Math.floor(15*weapon.getWeaponModifier());

        if (currentStatus){
            return damage -= damage - x;
        }
        return damage+(x*y);
        
    }
    public int defend(boolean currentStatus)
    {
        int x = 1 + (int)(Math.random() * ((3 - 1) + 1));
        int damage = 2;
        
        if (currentStatus){
            return damage;
        }
        return damage*(x);
        
    
    }
}