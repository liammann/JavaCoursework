/* 
 * IMPORTANT NOTE:
 * 
 * Should we make our system case-insensitive?
 * 
 * NOTE TO SELF: I DON'T LIKE THE EXTRA LINE GIVEN WHEN OUTPUTTING THE STUFF IN A ROOM!
 * 
 */

import java.util.ArrayList;
import java.lang.Math; // do we still need this?
import java.io.*;

public class Game
{
    private GameData gameData;
    private CommandsParser commandsParser;
    private String gameSaveLocation = "gamesaves/";
    private ArrayList<String> gameSaves;
    
    private Enemy jozef, liam, tom, zain;
    
    private Player player1;//combat
    
    public Game()
    {
        gameData = GameData.getInstance();
        commandsParser = new CommandsParser();
        getGameSaves();
        preGame();
    }
    
    public void preGame()
    {
        System.out.println("=============================");
        System.out.println("No game loaded yet!");
        System.out.println("Either load a saved game state with 'load game {game_save}'");
        System.out.println("Current game saves: " + getSavedGameNames());
        System.out.println("or load a new game with 'new game'");
        System.out.println("=============================");
        
        quitGame: while(true) {
            String command = commandsParser.getCommand();
            String output = commandsParser.parseCommand(command);

            switch(output) {
                case "quit":
                    break quitGame;
                case "new":
                    gameData.startGame();
                    newGame();
                    break;
                case "load":
                    loadGame();
                    break;
                default:
                    System.out.println(output);
            }
        }
    }

    private void play()
    {
        System.out.print(welcome());

        while(true) {
            System.out.print("\n> ");

            String command = commandsParser.getCommand();
            String output = commandsParser.parseCommand(command);
            boolean playerWin = false;
                
            if(output.equals("quit")) {
                break;
            }
                
            if(output.equals("finished")) {
                System.out.println("Congratulations on completing the game!");
                break;
            }
            
            if(output.equals("fight")) {
                ArrayList<String> fightCommands = new ArrayList<String>();
                
                for(String fightCommand : command.split(" ")) {
                    fightCommands.add(fightCommand);
                }

                if(gameData.getCurrentLocation().getEnemies().containsKey(fightCommands.get(1))) {
                   // Check to see if the command was entered correctly eg. fight NAME with WEAPON
                   if(fightCommands.size() > 3) { 
                       playerWin = combatStartFight(gameData.getCurrentLocation().getEnemy(fightCommands.get(1)),command, player1.getInventory().getWeapon(fightCommands.get(3)), player1);
                       
                       if(playerWin == false) {
                           break; // GAME OVER
                       }
                       
                       System.out.println("You have successfully beaten "+fightCommands.get(1)+"!");
                       System.out.println("You currently have: \n \t - "+player1.getHealth()+ " health points \n \t - "+player1.getStrength()+" strength points");                       
                       System.out.println(gameData.getCurrentLocation().getLongDescription());
                    }else{
                        System.out.print("Please put what weapon you would like to use (fight NAME with WEAPON)");
                    }
                }
            }
                
            if(output.equals("save")) {
                //if(saveGame()) {
                    saveGame();
                    System.out.print("The game has successfully been saved as '" + gameData.getName() + "'");
                //}else{
                //    System.out.print("There is already a game save with that name!");
                //}
            }else{
                System.out.print(output);
            }
            
            // put in a load game method?
        }

        System.out.println("Thanks for playing!");
    }
    
    private void getGameSaves()
    {
        File files = new File("gamesaves/");
        File[] contents = files.listFiles(new FilenameFilter() {
            public boolean accept(File directory, String file) {
                if(file.matches("[a-zA-Z]+\\.ser")) {
                    String fileName = file.split("\\.")[0];
                    return fileName.matches("[a-zA-Z]+");
                }

                return false;
            }
        });
       
        if(contents != null && contents.length != 0) {
            for(File name : contents) {
                String[] fileName = name.getName().split("\\.");
                gameData.addGameSave(fileName[0]);
            }
        }
    }

    private String getSavedGameNames()
    {
        ArrayList<String> savedGames = gameData.getSavedGames();
        String gameSaveNames = "";
        
        if(savedGames.size() == 0) {
            return "none";
        }       
        
        for(String gameName : savedGames) {
            gameSaveNames += "'" + gameName + "' ";
        }

        return gameSaveNames;
    }
    
    private void newGame()
    {
        buildGame();
        play();
    }
    
    private void saveGame()
    {
        //if(gameData.getGameSaves().contains()) PREVENTS OVERWRITING PREVIOUS GAME SAVES
        try {
            gameData.addGameSave(gameData.getName());

            FileOutputStream gameSave = new FileOutputStream(gameSaveLocation + gameData.getName() + ".ser");
            ObjectOutputStream dataToSave = new ObjectOutputStream(gameSave);

            dataToSave.writeObject(gameData);

            dataToSave.close();
            gameSave.close();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGame() //return a string for confirmation for overwrite?
    {
        try {
            FileInputStream gameSaveFile = new FileInputStream("gamesaves/" + gameData.getName() + ".ser");
            ObjectInputStream gameSaveData = new ObjectInputStream(gameSaveFile);

            gameData = (GameData) gameSaveData.readObject();

            gameSaveData.close();
            gameSaveFile.close();
        }catch(IOException ioe) {
            ioe.printStackTrace();
            return;
        }catch(ClassNotFoundException classe) {
            System.out.println("Class not found");
            classe.printStackTrace();
            return;
        }
        
        play();
    }
    
    private void buildGame()
    {
        buildLocations();
        createCharacters();
    }

    private void buildLocations()
    {
        Location outside, theater, pub, lab, office;

        MovableObject cider = new MovableObject("cider", "Half a bottle of Strongbow cider", 2);        
        FixedObject chair = new FixedObject("chair", "An old wooden chair");

        outside = Location.create();
        theater = Location.create();
        lab = Location.create();
        pub = Location.create();
        office = Location.create();
        
        outside.addDescription("Outside the university entrance")
               .withExit("east", theater)
               .withExit("south", lab)
               .withExit("west", pub)
               .andHasObject(chair);
               
        pub.addDescription("In a campus pub")
           .withExit("east", outside)
           .andEnemy("liam",liam)
           .andHasObject(cider);

        theater.addDescription("Inside of a lecture theater")
               .withExit("west", outside)
               .andHasObject(chair)
               .andIsLocked();
        
        lab.addDescription("In a computing lab")
           .withExit("north", outside)
           .withExit("east", office)
           .andEnemy("tom", tom);

        office.addDescription("In a computing Admin office")
              .withExit("west", lab);
        
        gameData.addLocation(theater);
        gameData.addLocation(pub);
        gameData.addLocation(lab);
        gameData.addLocation(office);
        
        gameData.setNewLocation(outside);
    }

    private void createCharacters() // segregate this method into friends, enemies, and players
    {
        MovableObject sword = new MovableObject("Sword", "Steal sword", 3, 3.1);
        
        player1 = new Player("Player 1");
        
        player1.hasStrength(100)
             .withHealth(100)
             .andHasWeapon("sword", sword);
        
        gameData.addPlayer(player1);

        jozef = new Enemy("jozef");
        liam = new Enemy("liam");
        tom = new Enemy("tom");
        zain = new Enemy("zain");
             
         // Enemies Weapons 
        MovableObject axe = new MovableObject("Axe", "Brutal axe", 2, 2.4);        
        MovableObject mace = new MovableObject("Mace", "Brutal mace", 2, 1.8);
        MovableObject dagger = new MovableObject("Dagger", "Very pointy stick", 1, 1.3);
        
        // upon enemy death, drop weapon in current room?
        
        // Dont use capitals for names 
        jozef.hasStrength(100)
             .withHealth(100)
             .andHasWeapon("dagger", dagger);

        liam.hasStrength(40)
            .withHealth(5)
            .andHasWeapon("axe", axe);

        tom.hasStrength(70)
           .withHealth(30)
           .andHasWeapon("mace", mace);

        zain.hasStrength(60)
            .withHealth(20)
            .andHasWeapon("dagger", dagger);
            
        
        // Set up friendly characters
        Friend jordan, james, jeremy, john; // do friends need health? Given that they are not fighting anyone?
        
        jordan = new Friend("jordan");
        james = new Friend("james");
        jeremy = new Friend("jeremy");
        john = new Friend("john");
        
        jordan.hasStrength(100)
              .withHealth(100)
              .andHasHint("The Key is behind the library");

        james.hasStrength(80)
             .withHealth(20)
             .andHasHint("The Sword is very useful which you already have");

        jeremy.hasStrength(70)
              .withHealth(30)
              .andHasHint("Try to pickup useful tools");

        john.hasStrength(60)
              .withHealth(20);
        
        
    }
    public boolean combatStartFight(Enemy enemy, String command, MovableObject weapon, Player player)
    {

        boolean playerWin = false;        
        boolean enemyDefending = false;
        boolean playerDefending = false;
        int enemyDealt = 0;
        int playerDealt = 0;        

        int totalEnemyDealt = 0;
        int totalPlayerDealt = 0;

        while(!playerWin){
            
            if(weapon.getName().equals("sheild")) {
                playerDealt = (int) Math.floor(10);
            }else{
                playerDealt = (int) Math.floor(15*weapon.getWeaponModifier());                
                enemyDealt = (int) Math.floor(14*enemy.getWeapon().getWeaponModifier());
            }
            
            totalEnemyDealt += enemyDealt;
            totalPlayerDealt += playerDealt;
            
            enemy.updateHealth(enemy.getHealth()-playerDealt);
            player.updateHealth(player.getHealth()-enemyDealt);
                
            if(player.getHealth() <= 0) {
                playerWin = false;

                System.out.println("You fight the evil "+enemy.getName()+ " to death with your "+weapon.getObjectName()+" \n, \t unfortunately it was your death. \n GAME OVER");

                break;
            }else if(enemy.getHealth() <= 0) {
                playerWin = true;

                System.out.println("You fight the evil " + enemy.getName() + " to the death with your "+weapon.getObjectName()+", and kill him only taking "+totalEnemyDealt+" health points.\n");

                gameData.getCurrentLocation().removeEnemy(enemy.getName());
                break;
            }
        }
        return playerWin;
    }   
    
    private String welcome()
    {
        return "---------------------------------------------------\n"
               +"You wake up at Porsmouth Museum in 2014 not knwing how you got there.\n"
               +"but there is something quite different about the world you have woken up in.\n"
               +"You hear people in distance shouting ‘vikings are here, get to gunwharf for rescue.'\n"

               +"You look for weapon to protect yourself. You wonder around in museum for quite sometime\n"
               +"before finding sword and shield and start your journey to gunwharf thinking what is going on here\n"
               +"You find a letter at the front of the museum which gives you hint how to get there\n"
               +"and what object you need to pick up.\n\n"
               +gameData.getCurrentLocation().getLongDescription()+
                gameData.getCurrentLocation().getLocationCharacters()+
                gameData.getCurrentLocation().getLocationItems()+
                gameData.getCurrentLocation().getExits();
    }
    
}
