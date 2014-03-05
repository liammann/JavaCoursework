import java.lang.Math;
/**
 * Write a description of class combat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Combat
{

    private Character player;    
    private Character enemy;
    private boolean enemyDefeated;
    private boolean gameOver;
    private Parser parser;
    /**
     * Constructor for objects of class combat
     */
    public Combat(Character player, Character enemy)
    {
        // initialise instance variables

        parser = new Parser();
       this.player = player;
       this.enemy = enemy;    
       
       System.out.println("Noooo a monster....would you like to 'attack' or 'defend'!");
       while(!enemyDefeated || !gameOver){
           
           playerTurn();        
           System.out.println("Enemy turn");
           enemyTurn();
           if(enemy.getHealth() >=0){
               enemyDefeated = true;
           }else if(player.getHealth() >=0){
               gameOver = true;
           }
       }
       if(enemyDefeated){
           System.out.println("You have successfully defeated the monster");
        }else if (gameOver){
            System.out.println("Game Over");
        }
       
    }
    private String processCommand(Command command) 
    {
        String commandWord = command.getCommandWord();
        if (commandWord.equals("attack")) {
           return "attack";
        }
        else if (commandWord.equals("defend")) {
           return "defend";
        }
        return null;
        
    }
    public void playerTurn()
    {

        Command command = parser.getCommand();
        String whattodo = processCommand(command);
        int enemyHealth = 0;

        if(whattodo.equals("defend")){
            enemyHealth = defend(player, enemy);
        }else if(whattodo.equals("attack")){
            enemyHealth = attack(player, enemy);
        }
        enemy.updateHealth(enemyHealth);
        System.out.println("Player Health: "+player.getHealth());
        System.out.println("Enemy Health: "+enemyHealth);
    }
    public void enemyTurn()
    {
        int playerHealth = 0;
        
        int x = 1 + (int)(Math.random() * ((3 - 1) + 1));

        if(x == 1 || x == 2){
            playerHealth = attack(enemy, player);
        }else {
            playerHealth = defend(enemy, player);
        }
        player.updateHealth(playerHealth);        
        System.out.println("Player Health: "+playerHealth);
        System.out.println("Enemy Health: "+enemy.getHealth());
    }
    public int attack(Character person1, Character person2)
    {
        int x = 1 + (int)(Math.random() * ((6 - 1) + 1));
        int person2Health = person2.getHealth();
        
        if(person1.getHealth()/x > person2.getHealth()){
            // enemy is stronger and has more health
            //Something fun to say
            person2Health -= 80+x;
        } else if(person1.getHealth()/x < person2.getHealth()){
            // enemy is stronger and has more health
            //Something fun to say

            person2Health -= 20+x;
        }
       
 
        return person2Health;
    }
    public int defend(Character person1, Character person2)
    {
        int x = 1 + (int)(Math.random() * ((6 - 1) + 1));
        int person2Health = person2.getHealth();
        
        if(person1.getHealth()*x > person2.getHealth()){
            // enemy is stronger and has more health
            //Something fun to say
            person2Health -= 80+x;
        } else if(person1.getHealth()*x < person2.getHealth()){
            // enemy is stronger and has more health
            //Something fun to say

            person2Health -= 20+x;
        }
       
 
        return person2Health;


    }
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    
    
    public int sampleMethod(int y)
    {
        // put your code here
        return y;
    }
}
