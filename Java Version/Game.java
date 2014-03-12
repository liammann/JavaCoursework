/* 
 * IMPORTANT NOTE:
 * 
 * Should we make our system case-insensitive?
 * 
 * NOTE TO SELF: I DON'T LIKE THE EXTRA LINE GIVEN WHEN OUTPUTTING THE STUFF IN A ROOM!
 * 
 */
import java.util.ArrayList;
import java.lang.Math;
import java.io.*;

public class Game
{
    private GameData gameData;
    private String gameSaveLocation = "gamesaves/";
    
    public Game()
    {
        gameData = GameData.getInstance();
        gameData.commandsParser = new CommandsParser();
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
            String command = gameData.commandsParser.getCommand();
            String output = gameData.commandsParser.parseCommand(command);

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

            String command = gameData.commandsParser.getCommand();
            String output = gameData.commandsParser.parseCommand(command);
                
            if(output.equals("quit")) {
                break;
            }
                
            if(output.equals("finished")) {
                System.out.println("Congratulations on completing the game!");
                break;
            }
                
            if(output.equals("save")) {
                saveGame();
                System.out.print("The game has successfully been saved as '" + gameData.getName() + "'");
            }else{
                System.out.print(output);
            }
        }

        System.out.println("Thanks for playing!");
    }

    private String getSavedGameNames()
    {
        File files = new File("gamesaves/");
        File[] contents = files.listFiles(new FilenameFilter() {
            public boolean accept(File directory, String file) {
                String fileName = file.split("\\.")[0];
                return fileName.matches("[a-zA-Z]+"); // match [a-zA-Z]+\.ser ??
            }
        });
        
        String gameSaveNames = "";
       
        if(contents == null || contents.length == 0) {
            return "none";
        }

        for(File name : contents) {
            String[] fileName = name.getName().split("\\.");
            gameSaveNames += "'" + fileName[0] + "' ";
            gameData.addGameSave(fileName[0]);
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
        try {
            FileOutputStream gameSave = new FileOutputStream(gameSaveLocation + gameData.getName() + ".ser");
            ObjectOutputStream dataToSave = new ObjectOutputStream(gameSave);
            dataToSave.writeObject(gameData);
            dataToSave.close();
            gameSave.close();
        }catch(IOException ioe) {
          ioe.printStackTrace();
        }
    }

    private void loadGame()
    {
        
        try {
            FileInputStream gameSaveFile = new FileInputStream("/gamesaves/" + gameData.getName() + ".ser");
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

        MovableObject cider = new MovableObject("Bottle of cider", "Half a bottle of Strongbow cider", 2);        
        FixedObject chair = new FixedObject("Chair", "An old wooden chair");

        outside = Location.create();
        theater = Location.create();
        lab = Location.create();
        pub = Location.create();
        office = Location.create();

        outside.addDescription("Outside the university entrance")
               .withExit("east", theater)
               .withExit("south", lab)
               .withExit("west", pub)
               .andItem(chair);

        pub.addDescription("In a campus pub")
           .withExit("east", outside)
           .andItem(cider);

        theater.addDescription("Inside of a lecture theater")
               .withExit("west", outside)
               .andItem(chair)
               .andIsLocked();

        lab.addDescription("In a computing lab")
           .withExit("north", outside)
           .withExit("east", office);

        office.addDescription("In a computing Admin office")
              .withExit("west", lab);
        
        gameData.places = new ArrayList<Location>();
        gameData.places.add(theater);
        gameData.places.add(pub);
        gameData.places.add(lab);
        gameData.places.add(office);

        /* This is a note for myself (Tom) so that I don't forget
         * Character objects could be placed in an ArrayList field apart of the Location class
         */
        
        gameData.setNewLocation(outside);
    }

    private void createCharacters()
    {
        MovableObject sword = new MovableObject("Sword", "Steal sword", 3, 0.1);
        //gameData.player1 = new Player("Player1", 100, 100, sword);
        
        Player player1;
        
        player1 = new Player("Player 1");
        
        player1.hasStrength(100)
             .withHealth(100)
             .andHasWeapon(sword);
        
        // Enemies Weapons 
        MovableObject axe = new MovableObject("Axe", "Brutal axe", 2, 2.4);        
        MovableObject mace = new MovableObject("Mace", "Brutal mace", 2, 1.8);
        MovableObject dagger = new MovableObject("Dagger", "Very pointy stick", 1, 1.3);
        
        // upon enemy death, drop weapon in current room?
        
        Enemy jozef, liam, tom, zain;
        
        jozef = new Enemy("Jozef");
        liam = new Enemy("Liam");
        tom = new Enemy("Tom");
        zain = new Enemy("Zain");
        
        jozef.hasStrength(100)
             .withHealth(100)
             .andHasWeapon(dagger);

        liam.hasStrength(40)
            .withHealth(15)
            .andHasWeapon(axe);

        tom.hasStrength(70)
           .withHealth(30)
           .andHasWeapon(mace);

        zain.hasStrength(60)
            .withHealth(20)
            .andHasWeapon(dagger);
        
        //gameData.bots = new ArrayList<Enemy>();
        //gameData.bots.add(jozef);
        //gameData.bots.add(liam);
        //gameData.bots.add(tom);
        //gameData.bots.add(zain);
        
        //for(Enemy bot : gameData.bots) {
        //    gameData.places.get((int)Math.floor(Math.random() * gameData.bots.size())).addEnemy(bot.getName(), bot);
        //}
        
        // Set up friendly characters
        Friend jordan, james, jeremy, john; // do friends need health? Given that they are not fighting anyone?
        
        jordan = new Friend("Jordan");
        james = new Friend("James");
        jeremy = new Friend("Jeremy");
        john = new Friend("John");
        
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
        
    
        //gameData.addFriend(Jordan.getName(), Jordan);
        //gameData.addFriend(James.getName(), James);
        //gameData.addFriend(Jeremy.getName(), Jeremy);
        //gameData.addFriend(John.getName(), John);
        
        //for(Friend friend : gameData.getAllFriends()) {
        //    gameData.places.get((int)Math.floor(Math.random() * gameData.friends.size())).addFriend(friend.getName(), friend);
        //}
        
    }

    private String welcome()
    {
        return "\nWelcome to the Pub Crawl Game!\n "
              +"You wake up at Porsmouth Museum in 2014 not knwing how you got there.\n"
               +"but there is something quite different about the world you have woken up in.\n"
               +"You hear people in distance shouting ‘vikings are here, get to gunwharf for rescue.\n"
               +"Type 'help' if you are not sure what to do.\n\n"
               +"You look for weapon to protect yourself. You wonder around in museum for quite sometime\n"
               +"before finding sword and shield and start your journey to gunwharf thinking what is going on here\n"
               +"You find a letter at the front of the museum which gives you hint how to get there\n"
               +"and what object you need to pick up.\n"
               +gameData.getCurrentLocation().getLongDescription();
    }
    
}
