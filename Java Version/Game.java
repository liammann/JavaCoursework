import java.util.ArrayList;
import java.lang.Math;
public class Game
{
    public static CommandsParser commandsParser; // Used in Combat class
    public static Player player1; // Used in Combat class
    private GameData gameData;
    private MovableObject sword; // testing
    private Character jozef, liam, tom, zain;
    ArrayList<Character> bots;
    ArrayList<Location> places;

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
                        buildGame();
                        break;
                    case "load":
                        // load game save
                        buildGame();
                        break;
                    default:
                        System.out.println(output);
                }
            }else{
                System.out.println("No command entered!");
            }
        }
    }
    
    private void newGame()
    {
        buildGame();
    }

    private void loadGame()
    {
        // load game save
        buildGame();
    }
    
    private void buildGame()
    {
        buildLocations();
        createCharacters(); // load chararacters here
        play();
    }

    private void buildLocations()
    {
        Location outside, theater, pub, lab, office;
        MovableObject cider;        

        FixedObject chair;

        /* Pass location name as a argument to the Location constructor
         * if we are going to store each location in a hash map
         */
        outside = Location.create();
        theater = Location.create();
        lab = Location.create();
        pub = Location.create();
        office = Location.create();

        cider = new MovableObject("Bottle of cider", "Half a bottle of Strongbow cider", 2);        
        sword = new MovableObject("Sword", "A very strong sword", 2, 2.4);
        chair = new FixedObject("Chair", "An old wooden chair");

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
        
        places = new ArrayList<Location>();
        places.add(theater);
        places.add(pub);
        places.add(lab);
        places.add(office);
        
        bots = new ArrayList<Character>();
        bots.add(jozef);
        bots.add(liam);
        bots.add(tom);
        bots.add(zain);
        
        for (Character bot: bots)
        {
            places.get((int)Math.floor(Math.random() * bots.size())).addCharacter(bot);
        }
        // END of shift
        
        /* This is a note for myself (Tom) so that I don't forget
         * Character objects could be placed in an ArrayList field apart of the Location class
         */
        
        gameData.setNewLocation(outside);
    }

    private void createCharacters()
    {
       player1 = new Player("Player1", 100, 100);
       player1.getInventory().addItemToInventory(sword);
       
       jozef = new Character("Jozef", 7, 100);
       liam = new Character("Liam", 80, 20);
       tom = new Character("Tom", 70, 30);
       zain = new Character("Zain", 60, 20);
    }

    private void play()
    {
        System.out.print(welcome());

        while(true) {
            System.out.print("\n> ");

            String command = commandsParser.getCommand();

            if(!command.equals("")) {
                String output = commandsParser.parseCommand(command);

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

    private String welcome()
    {
        return "\nWelcome to the Pub Crawl Game!\n"
                +"Type 'help' if you are not sure what to do.\n\n"
                +gameData.getCurrentLocation().getLongDescription();
    }
}
