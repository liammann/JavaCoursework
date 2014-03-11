/* 
 * IMPORTANT NOTE:
 * 
 * Should we make our system case-insensitive?
 * 
 * Also, no classes should be "using" the Game class...
 */

import java.util.ArrayList;
import java.lang.Math;
import java.io.*;

public class Game
{
    private static CommandsParser commandsParser; // Used in Combat class
//     private static Player player1; // Used in Combat class
    private GameData gameData;
    
//     private MovableObject sword;
//     private Character jozef, liam, tom, zain;
//     private ArrayList<Character> bots;
//     private ArrayList<Location> places;
    
    // I dont know how to get the objects into combat without doing it this way

    public static CommandsParser getCommandsParserObject(){
        return commandsParser;
    }
    
    public Game()
    {
        commandsParser = new CommandsParser();
        gameData = new GameData();
        
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
            
            if(!command.equals("")) {
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
            }else{
                System.out.println("No command entered!");
            }
        }
    }
    
    private void play()
    {
        System.out.print(welcome());

        while(true) {
            System.out.print("\n> ");

            String command = commandsParser.getCommand();

            if(!command.equals("")) {
                String output = commandsParser.parseCommand(command);

                if(output.equals("save")) {
                    saveGame();
                    System.out.print("The game has successfully been saved as " + gameData.getName());
                }
                
                if(output.equals("quit")) { 
                    break;
                }
                
                if(output.equals("finished")) {
                    System.out.println("Congratulations on completing the game!");
                    break;
                }
                
                System.out.print(output);
            }else{
                System.out.print("No command entered!");
            }
        }

        System.out.println("Thanks for playing!");
    }
    
    private String getSavedGameNames()
    {
        File files = new File("gamesaves/");
        File[] contents = files.listFiles();
        String gameSaveNames = "";
       
        if(contents == null) {
            gameSaveNames = "none";
            return gameSaveNames;
        }
       
        for(File name : contents) {
            if(name.isFile()) {
                String[] fileName = name.getName().split("\\.");
                gameSaveNames += fileName[0] + " ";
                gameData.addGameSave(fileName[0]);
            }
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
        //
    }

    private void loadGame()
    {
        /* 
         * The following is still a work in progress...
         */
        /*
        GameData gameData = null;
        
        try {
            FileInputStream gameSaveFile = new FileInputStream("/gamesaves/" + . + ".ser");
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
        }*/
        
        play();
    }
    
    private void buildGame()
    {
        buildLocations();
        createCharacters(); // load chararacters here
    }

    private void buildLocations()
    {
        Location outside, theater, pub, lab, office;


        MovableObject cider = new MovableObject("Bottle of cider", "Half a bottle of Strongbow cider", 2);        
        MovableObject sword = new MovableObject("Sword", "A very strong sword", 2, 2.4);
        FixedObject chair = new FixedObject("Chair", "An old wooden chair");
        
        /* Pass location name as a argument to the Location constructor
         * if we are going to store each location in a hash map
         */
        outside = Location.create();
        theater = Location.create();
        lab = Location.create();
        pub = Location.create();
        office = Location.create();



        outside.addDescription("Outside the university entrance")
               .withExit("east", theater)
               .withExit("south", lab)
               .withExit("west", pub)
               .andItem(sword)
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

        // END of shift
        
        /* This is a note for myself (Tom) so that I don't forget
         * Character objects could be placed in an ArrayList field apart of the Location class
         */
        
        gameData.setNewLocation(outside);
    }

    private void createCharacters()
    {
       gameData.player1 = new Player("Player1", 100, 100);
       //       GameData.player1.getInventory().addItemToInventory(GameData.sword);
       
       Character jozef = new Character("Jozef", 100, 100);
       Character liam = new Character("Liam", 40, 15);
       Character tom = new Character("Tom", 70, 30);
       Character zain = new Character("Zain", 60, 20);
       
        gameData.bots = new ArrayList<Character>();
        gameData.bots.add(jozef);
        gameData.bots.add(liam);
        gameData.bots.add(tom);
        gameData.bots.add(zain);
        
        for (Character bot: gameData.bots)
        {
            gameData.places.get((int)Math.floor(Math.random() * gameData.bots.size())).addCharacter(bot);
        }
        Friend Jordan = new Friend("Jordan", 100, 100, "The Key is behind the library");
        Friend James = new Friend("James", 80, 20, "The Sword is very useful which you already have");
        Friend Jeremy = new Friend("Jeremy", 70, 30, "Try to pickup useful tools");
        Friend John = new Friend("John", 60, 20, "haha");
        
        gameData.friends = new ArrayList<Friend>();
        gameData.friends.add(Jordan);
        gameData.friends.add(James);
        gameData.friends.add(Jeremy);
        gameData.friends.add(John);
        
        for (Character friend: gameData.friends) {
            gameData.places.get((int)Math.floor(Math.random() * gameData.friends.size())).addCharacter(friend);
        }
        
    }

    private String welcome()
    {
        return "\nWelcome to the Pub Crawl Game!\n"
                +"Type 'help' if you are not sure what to do.\n\n"
                +gameData.getCurrentLocation().getLongDescription();
    }
}
