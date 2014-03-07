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
    }

    public void newGame()
    {
        buildLocations();
        createCharacters();
        play();
    }

    public void loadGame()
    {
        // load game save
    }

    private void buildLocations()
    {
        Location outside, theater, pub, lab, office;
        // Object cider, chair;
        MovableObject cider;
        FixedObject chair;

        outside = new Location("outside the main entrance of the university");
        theater = new Location("in a lecture theater");
        pub = new Location("in a campus pub");
        lab = new Location("in a computing lab");
        office = new Location("in the computing admin office");

        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        theater.setExit("west", outside);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);

        office.setLock();

        cider = new MovableObject("Bottle of cider", "Half a bottle of Strongbow cider", 2);
        chair = new FixedObject("Chair", "An old wooden chair");

        outside.setItem(cider);
        outside.setItem(chair);
        theater.setItem(cider);
        
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

        boolean finished = false;

        while(!finished) {
            System.out.print("\n> ");

            String command = commandsParser.getCommand();

            if(!command.equals("")) {
                String output = commandsParser.parseCommand(command);

                if(output.equals("quit")) {
                    finished = true;
                }else{
                    System.out.print(output);
                }
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
