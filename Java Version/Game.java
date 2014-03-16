import java.util.ArrayList;
import java.lang.Math;
import java.io.*;

public class Game
{
    private GameData gameData;
    private CommandsParser commandsParser;
    private String gameSaveLocation = "gamesaves/";
    private ArrayList<String> gameSaves;
    
    private Enemy jozef, liam, tom, zain;
    
    private Player player1; // combat
    
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

            if(output.equals("quit")) {
                break;
            }

            if(output.equals("finished")) {
                System.out.println("Congratulations on completing the game!");
                break;
            } 

            if(output.equals("fight")) {
                String[] fightCommands = command.split(" ");
                Enemy enemy = gameData.getCurrentLocation().getEnemyByName(fightCommands[1]);
                MovableObject weapon = player1.getInventory().getWeapon(fightCommands[3]);
                boolean playerWin = combatStartFight(enemy, weapon, player1);

                if(playerWin == false) {
                    System.out.println("You've lost the game!");
                    break;
                }

                System.out.println("You have successfully beaten " + fightCommands[1] + "!");
                System.out.println("You currently have:\n\t- " + player1.getHealth() + " health points\n\t- " + player1.getStrength() + " strength points");                       
                System.out.print(gameData.getCurrentLocation().getLongDescription());                       
                System.out.print(gameData.getCurrentLocation().getExits());
            }else if(output.equals("save")) {
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
        createCharacters();
        buildLocations();
    }

    private void buildLocations()
    {
        Location outside, theater, pub, lab, office;
        MovableObject cider, key;
        FixedObject chair;

        cider = MovableObject.create("cider")
                             .withDescription("Half a bottle of Strongbow cider")
                             .andWeight(2);

        key = MovableObject.create("key")
                           .withDescription("A key that unlocks some door")
                           .andHasPasscode(42);

        chair = FixedObject.create("chair")
                           .withDescription("An old wooden chair");
       
        MovableObject healthPotion = new MovableObject("health", 12.0);        


        outside = Location.create();
        theater = Location.create();
        lab = Location.create();
        pub = Location.create();
        office = Location.create();
        
        outside.addDescription("Outside the university entrance")
               .withExit("east", theater)
               .withExit("south", lab)
               .withExit("west", pub)
               .andHasObject(chair)
               .andHasObject(healthPotion);
               
        pub.addDescription("In a campus pub")
           .withExit("east", outside)
           .andEnemy(liam)
           .andHasObject(key);

        theater.addDescription("Inside of a lecture theater")
               .withExit("west", outside)
               .andHasObject(chair)
               .andIsLocked()
               .andPasscode(42);
        
        lab.addDescription("In a computing lab")
           .withExit("north", outside)
           .withExit("east", office)
           .andEnemy(tom);

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
        MovableObject sword;
        
        sword = MovableObject.create("sword")
                             .withDescription("Steal sword")
                             .andWeight(3)
                             .withWeaponModifier(3.1);
                             
        player1 = Player.create("Player 1")
                        .hasStrength(100)
                        .withHealth(100)
                        .andHasWeapon(sword);
        
        gameData.addPlayer(player1);
        
        jozef = new Enemy("jozef");
        liam = new Enemy("liam");
        tom = new Enemy("tom");
        zain = new Enemy("zain");
             
         // Enemies Weapons 
        MovableObject axe, mace, dagger;
        
        axe = MovableObject.create("axe")
                           .withDescription("Brutal axe")
                           .andWeight(2)
                           .withWeaponModifier(2.4);
        
        mace = MovableObject.create("mace")
                            .withDescription("Brutal mace")
                            .andWeight(2)
                            .withWeaponModifier(1.8);

        dagger = MovableObject.create("dagger")
                              .withDescription("Very pointy stick")
                              .andWeight(1)
                              .withWeaponModifier(1.3);

        // upon enemy death, drop weapon in current room?

        jozef.hasStrength(100)
             .withHealth(100)
             .andHasWeapon(dagger);

        liam.hasStrength(40)
            .withHealth(5)
            .andHasWeapon(axe);

        tom.hasStrength(70)
           .withHealth(30)
           .andHasWeapon(mace);

        zain.hasStrength(60)
            .withHealth(20)
            .andHasWeapon(dagger);
            

        Friend jordan, james, jeremy, john;
        
        jordan = Friend.create("jordan")
                       .withHint("The Key is behind the library");

        james = Friend.create("james")
                      .withHint("The Sword is very useful which you already have");

        jeremy = Friend.create("jeremy")
                       .withHint("Try to pickup useful tools");

        john = Friend.create("john")
                     .withHint("Shut up, Meg");
    }

    public boolean combatStartFight(Enemy enemy, MovableObject weapon, Player player)
    {
        boolean playerWin = false;
        boolean enemyDefending = false;
        boolean playerDefending = false;
        int enemyDealt = 0;
        int playerDealt = 0;
        int totalEnemyDealt = 0;
        int totalPlayerDealt = 0;

        while(!playerWin) {
            playerDealt = (int) Math.floor(15*weapon.getWeaponModifier());                
            enemyDealt = (int) Math.floor(14*enemy.getInventory().getWeapon().getWeaponModifier()); // getWeapon().getWeaponModifier());
            
            totalEnemyDealt += enemyDealt;
            totalPlayerDealt += playerDealt;
            
            enemy.updateHealth(enemy.getHealth() - playerDealt);
            player.updateHealth(player.getHealth() - enemyDealt);
                
            if(player.getHealth() <= 0) {
                playerWin = false;
                System.out.println("You fight the evil " + enemy.getName() + " to death with your " + weapon.getObjectName());
                System.out.println("unfortunately it was your death.\nGAME OVER");
                break;
            }
            
            if(enemy.getHealth() <= 0) {
                playerWin = true;
                System.out.println("You fight the evil " + enemy.getName() + " to the death with your " + weapon.getObjectName());
                System.out.println("and kill him only taking " + totalEnemyDealt + " health points.\n");
                gameData.getCurrentLocation().removeEnemyByName(enemy.getName());
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
               +gameData.getCurrentLocation().getLongDescription()
               +gameData.getCurrentLocation().getLocationCharacters()
               +gameData.getCurrentLocation().getLocationItems()
               +gameData.getCurrentLocation().getExits();
    }
}
