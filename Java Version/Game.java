

public class Game
{
	private CommandsParser commandsParser;
	private GameData gameData;
	// private Character player;
	// private Character enemy;
	// private Combat fight;

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

		theater.setLock();
		pub.setLock();
		lab.setLock();
		office.setLock();

		cider = new MovableObject("Bottle of cider", "Half a bottle of Strongbow cider");
		chair = new FixedObject("Chair", "An old wooden chair");

		outside.setItem(cider);
		outside.setItem(chair);
		theater.setItem(cider);

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

		System.out.println("Thanks for play!");
	}

	private String welcome()
	{
		return "Welcome to the Pub Crawl Game!\n"
				+"Type 'help' if you are not sure what to do.\n\n"
				+gameData.getCurrentLocation().getLongDescription();
	}
}