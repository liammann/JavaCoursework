/* 
 * IMPORTANT NOTE:
 * 
 * Should we make our system case-insensitive?
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
        gameData = new GameData();
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
                return fileName.matches("[a-zA-Z]+");
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
        gameData.player1 = new Player("Player1", 100, 100, sword);
        
        // Enemies Weapons 
        MovableObject axe = new MovableObject("Axe", "Brutal axe", 2, 2.4);        
        MovableObject mace = new MovableObject("Mace", "Brutal mace", 2, 1.8);
        MovableObject dagger = new MovableObject("Dagger", "Very pointy stick", 1, 1.3);
       
        
        Enemy jozef = new Enemy("Jozef", 100, 100, dagger);
        Enemy liam = new Enemy("Liam", 40, 15, axe);
        Enemy tom = new Enemy("Tom", 70, 30, mace);
        Enemy zain = new Enemy("Zain", 60, 20, dagger);
        
        gameData.bots = new ArrayList<Enemy>();
        gameData.bots.add(jozef);
        gameData.bots.add(liam);
        gameData.bots.add(tom);
        gameData.bots.add(zain);
        
        for(Enemy bot : gameData.bots) {
            gameData.places.get((int)Math.floor(Math.random() * gameData.bots.size())).addEnemy(bot);
        }
        
        // Set up friendly characters
        Friend Jordan = new Friend("Jordan", 100, 100, "The Key is behind the library");
        Friend James = new Friend("James", 80, 20, "The Sword is very useful which you already have");
        Friend Jeremy = new Friend("Jeremy", 70, 30, "Try to pickup useful tools");
        Friend John = new Friend("John", 60, 20, "haha");
        
    
        gameData.addFriend(Jordan.getName(), Jordan);
        gameData.addFriend(James.getName(), James);
        gameData.addFriend(Jeremy.getName(), Jeremy);
        gameData.addFriend(John.getName(), John);
        
        for(Friend friend : gameData.getAllFriends()) {
            gameData.places.get((int)Math.floor(Math.random() * gameData.friends.size())).addFriend(friend);
        }
        
    }

    private String welcome()
    {
        return "\nWelcome to the Pub Crawl Game!\n"
                +"Type 'help' if you are not sure what to do.\n\n"
                +gameData.getCurrentLocation().getLongDescription();
    }
    
    public static GameData getGameDataObject()
    {
        return gameData;
    }
}
