import java.util.ArrayList;
import java.lang.Math;
public class Game
{
    private CommandsParser commandsParser;
    private GameData gameData;
    // private Character player;
    // private Character enemy;
    // private Combat fight;
    Character jozef, liam, tom, zain;

    public Game()
    {
        commandsParser = new CommandsParser();
        gameData = new GameData();
        
        System.out.println("No game loaded yet!");
        System.out.println("Either load a saved game state with 'load game {game_save}'");
        System.out.print("or load a new game with 'new game'");
        
        while(true) {
            System.out.print("\n> ");

            String command = commandsParser.getCommand();
            
            if(!command.equals("")) { // get rid of it?
                String output = commandsParser.parseCommand(command);

                if(output.equals("quit")) {
                    break;
                }
                
                if(output.equals("new")) {
                    buildGame();
                    break;
                }
                
                if(output.equals("load")) {
                    // load game save
                    buildGame();
                    break;
                }
                
                System.out.print(output);
            }else{
                System.out.print("No command entered!");
            }
        }
    }
    
    public void newGame()
    {
        buildGame();
    }

    public void loadGame()
    {
        // load game save
        buildGame();
    }
    
    private void buildGame()
    {
        buildLocations();
        createCharacters();
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
        chair = new FixedObject("Chair", "An old wooden chair");

        outside.addDescription("Outside the university entrance")
               .withExit("east", theater)
               .withExit("south", lab)
               .withExit("west", pub)
               .andItem(cider)
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
        
        jozef = new Character("Jozef", 100, 100);
        liam = new Character("Liam", 10, 20);
        tom = new Character("Tom", 20, 30);
        zain = new Character("Zane", 20, 20);
        
        ArrayList<Character> bots;
        bots = new ArrayList<Character>();
        bots.add(jozef);
        bots.add(liam);
        bots.add(tom);
        bots.add(zain);
        
        ArrayList<Location> places;
        places = new ArrayList<Location>();
        places.add(theater);
        places.add(pub);
        places.add(lab);
        places.add(office);
        
        ArrayList<Location> randomBots;
        randomBots = new ArrayList<Location>();
        for (int i=0; i<3; i++)
        {
            places.get(i).addCharacter(bots.get((int)Math.floor(Math.random() * 4)));
        }
        
        gameData.setNewLocation(outside);
    }

    private void createCharacters()
    {
        Character player1 = new Character("Player1", 100, 100);
    }

    private void play()
    {
        System.out.print(welcome());

        while(true) {
            System.out.print("\n> ");

            String command = commandsParser.getCommand();

            if(!command.equals("")) { //get rid of it?
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
        return "Welcome to the Pub Crawl Game!\n"
                +"Type 'help' if you are not sure what to do.\n\n"
                +gameData.getCurrentLocation().getLongDescription();
    }
}
