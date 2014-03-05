import java.lang.Math;

public class Combat
{
    private Character player;
    private Character enemy;
    private boolean enemyDefeated;
    private boolean gameOver;
    private CommandsParser commandsParser;

    public Combat(Character player, Character enemy)
    {
        commandsParser = new CommandsParser();
        this.player = player;
        this.enemy = enemy;
       
        System.out.println("Noooo a monster....would you like to 'attack' or 'defend'!");

        while(!enemyDefeated || !gameOver) {
            playerTurn();

            System.out.println("Enemy turn");

            enemyTurn();

            if(enemy.getHealth() >= 0) {
                enemyDefeated = true;
                break;
            }

            if(player.getHealth() >= 0) {
                gameOver = true;
                break;
            }
        }

        if(enemyDefeated) {
            System.out.println("You have successfully defeated the monster");
        }

        if(gameOver) {
            System.out.println("Game Over");
        }
    }

    private String processCommand(Command command) 
    {
        String commandWord = command.getCommandWord();

        if(commandWord.equals("attack")) {
            return "attack";
        }else{
            return "defend";
        }
    }

    public void playerTurn()
    {
        Command command = commandsParser.getCommand();
        String whattodo = processCommand(command);
        int enemyHealth = 0;

        if(whattodo.equals("defend")) {
            enemyHealth = defend(player, enemy);
        }else{
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

        if(x == 1 || x == 2) {
            playerHealth = attack(enemy, player);
        }else{
            playerHealth = defend(enemy, player);
        }

        player.updateHealth(playerHealth);

        System.out.println("Player Health: " + playerHealth);
        System.out.println("Enemy Health: " + enemy.getHealth());
    }

    public int attack(Character person1, Character person2)
    {
        int x = 1 + (int) (Math.random() * ((6 - 1) + 1));
        int person2Health = person2.getHealth();

        if(person1.getHealth() / x > person2.getHealth()) {
            // enemy is stronger and has more health
            //Something fun to say
            person2Health -= 80 + x;
        }else if(person1.getHealth() / x < person2.getHealth()) {
            // enemy is stronger and has more health
            //Something fun to say
            person2Health -= 20 + x;
        } // else, incase they are equal!

        return person2Health;
    }

    public int defend(Character person1, Character person2)
    {
        int x = 1 + (int) (Math.random() * ((6 - 1) + 1));
        int person2Health = person2.getHealth();
        
        if(person1.getHealth() * x > person2.getHealth()) {
            // enemy is stronger and has more health
            //Something fun to say
            person2Health -= 80 + x;
        } else if(person1.getHealth() * x < person2.getHealth()) {
            // enemy is stronger and has more health
            //Something fun to say
            person2Health -= 20 + x;
        } // else, incase they are equal!
 
        return person2Health;
    }
}