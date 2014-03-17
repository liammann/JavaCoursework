import java.util.ArrayList;
import java.util.HashMap;

import java.io.*;

/**
 * A textual based game
 *
 * @author Thomas Punt, Liam Mann, Jozef Stodulski and Zain Ali
 * @version 17.3.2014 
 */

public class Game
{
    private GameData gameData;
    private CommandsParser commandsParser;
    private String gameSaveLocation = "gamesaves/";
    private ArrayList<String> gameSaves;

    private Enemy enemy1, enemy2, enemy3;
    private Friend sam, james, jeremy, john;
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
        System.out.println("...................,.......,...........,,,,.............,.........,,,....,......");
        System.out.println(".......,...,,.,.,.,,,,,,,,,,,,,,,,,,,,,,,I,,,,.,,,,,,,,,,,,,,,,,,,,,,,,,,.,,,,,,");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,Z==$$Z,,,,,,,,,,.............,,,,,,,,,,,,");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,I+:~=+O,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,~~ZZ?$$,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,~ZI,,,OD,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,~:=::I+D,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,7I~=:::O=O,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,$$?$:~?~:D7,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,=7Z$:DZOON7,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,+~+,:,Z+~I8,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,I,=:ZZ:::$Z,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,:,+~NZ=::Z8,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,!,::D8:::ZZ$,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        System.out.println("///////////////////////////////////~,I~:~==+=$=?////////////////////////////////");
        System.out.println("IIIIIII?????????????????????????~+?ZZOOO$OOZZI~:~??????????????????????????????O");
        System.out.println("II????????????????????????????7=+I7?:,.~$=:,,7~I~~?????????????????????????????O");
        System.out.println("??????????????????????????????7$?7??::~?$,:::$,::IZ????????????????????????????O");
        System.out.println("??????????????????????????????I,:8D?::I:7,::::+N=:???????????????~I:=,~????????M");
        System.out.println("??????????????????????????????:NOZO?OZO~$:,?+::+~~???????????+++?:+++==????????N");
        System.out.println("???????????????:Z+:=ZI?????+?$:D?ZO87+:7,Z8??=+7~~?+++++++??==8O8Z~=+~~+??7?????");
        System.out.println("???????????????~~=+=ZZ+++++?+:?O$:~+=7=:+:IO$Z:877O:ZO$DZ::Z$:~,~~~Z==+++?I?++??");
        System.out.println("???????????I+Z$77,7I:==I,Z?OI==:,.?8OZZ$Z=OZ?8888DD+====~?D8Z87+I?8888+D$88D++++");
        System.out.println("???????+++?+=?I7ZOZ77$ZZ7++~=++~~:::=:~~~====?7???NDZO+::~~~==:~~=:::::==?:78O++");
        System.out.println("????++++++?~~=+?+~::::~+=::77$$?~8D$~8DI7DDD7DND8+OO=~~++::?Z$=DDO,DDD=O+~OO:+++");
        System.out.println("$+++++++++=Z$+?D=?88788Z+D8?7N88$DNN~DND=DND,NND:~NNN88NNO=DDO?NNN:DNN:=::~:?7$$");
        System.out.println("8OZ?+=$+Z?:~::ZZD:ZD:ODZ$DD::ZOZ~O88~8DD,NDD,O88~:DNZ~~$I~:O$7:7$O~~7I:::::::DD8");
        System.out.println("DND$7$O8N8::::$$7~~+:Z?:ZZ=::$?+~Z8O~88D,MD8:O+==:8NZI:Z$$:$$I~~NI~=ZO:::::::888");
        System.out.println("MMO?OON8N8::::$Z7==$:I?:ZO=::$=~:Z8O:ODO:ODO:O+++:8DZO:ZOZ:~ZZ~=ZZ==ZZ7:,~=~~7DO");
        System.out.println("D8D8ND8NN8:=~:$OZ+IZ~=O:ZOZ~~$Z?:Z8Z:ZD8:88Z:Z==~~$888:+8D~~DD=~NN==DO7:$?:~+:N8");
        System.out.println("MONM8NN$NO::::Z8:?OO~~8:ODO=:$~~:ZO$:$DN,NO$:$~Z+~$~8D::DD~~NN=:NN+=N8:~=~~~=~NN");
        System.out.println("8MMMNNNN88::::Z8:7Z8~=87O8O~~$:::$$::~OZ,OZ:~7::~~Z~7D~~ND~~Z~=+ND+=8O:~~~:~=:ND");
        System.out.println("MDDDNMN$8II$I?O$+$$~7I?I7DZ+~Z:::ZZ:::OZ:$O::?,:::ZI=$=+$???$+88OZI7$DID7IIIIINN");
        System.out.println("8DMDM88NODZI+:D7+DM=~N8Z$$ZO==+~~I?:~??:~?I?~~I?~I??7?88O$?IOD?=NN~::NZ::::~~INN");
        System.out.println("MM8MMNNNNOI,,.=NOOO+.8=~~=++=?+++++++???===++++?+?++?=I~N$~~DO~~DN~~~O88?$ZZIDNZ");
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
            System.out.print("\n__________________________________________");
            System.out.print("\n> ");

            String command = commandsParser.getCommand();
            String output = commandsParser.parseCommand(command);

            if(output.equals("quit")) {
                break;
            }else if(output.equals("finished")) {
                System.out.println("Congratulations on completing the game!");
                break;
            }else if(output.equals("take")) {
                String[] takeCommands = command.split(" ");
                Friend friend = gameData.getCurrentLocation().getFriendByName(takeCommands[3]);
                MovableObject item = friend.getInventory().getFromInventoryByName(takeCommands[1]);

                if(player1.getInventory().addItemToInventory(item)){
                    friend.getInventory().dropFromInventory(item.getObjectName());
                    System.out.print(friend.getName()+" has given you "+ item.getObjectDescription());
                }else {
                    System.out.print("What item?");
                }
                
                
            }else if(output.equals("fight")) {
                String[] fightCommands = command.split(" ");
                Enemy enemy = gameData.getCurrentLocation().getEnemyByName(fightCommands[1]);
                MovableObject weapon = player1.getInventory().getWeapon(fightCommands[3]);
                boolean playerWin = combatStartFight(enemy, weapon, player1);

                if(playerWin == false) {
                    System.out.println("You've lost the game!");
                    break;
                }

                System.out.println("You have successfully beaten " + fightCommands[1] + "!");
                System.out.print("You currently have:\n\t- " + player1.getHealth() + " health points\n\t- ");
                System.out.println(player1.getStrength() + " strength points");
                System.out.println(gameData.getCurrentLocation().getLongDescription());    
                System.out.println(gameData.getCurrentLocation().getLocationCharacters());
                System.out.println(gameData.getCurrentLocation().getLocationItems());
                System.out.print(gameData.getCurrentLocation().getExits());
                
            }else if(output.equals("save")) {
                if(gameData.getSavedGames().contains(command.split(" ")[3])) {
                    System.out.print("Are you sure you would like to overwrite this game save?\n[yes/no] : ");
                    command = commandsParser.getCommand();

                    if(command.equals("yes")) {
                        saveGame();
                        System.out.print("The game has successfully been saved as '" + gameData.getName() + "'");
                    }else{
                        System.out.print("Saving game aborted.");
                    }
                }else{
                    saveGame();
                    System.out.print("The game has successfully been saved as '" + gameData.getName() + "'");
                }
            }else if(output.equals("load")) {
                System.out.print("You cannot load a saved game whilst in game. Please go into pregame mode first.");
            }else{
                System.out.print(output);
            }
        }
        System.out.println("Thanks for playing!");
    }

    private void getGameSaves()
    {
        File files = new File("gamesaves/");
        File[] contents = files.listFiles(new FilenameFilter() {
            public boolean accept(File directory, String file) {
                if(file.matches("[a-zA-Z]+\\.ser")) {
                    return true;
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
        Location exhibit, reception, cafe, guardOffice, museumEntrance, museumCarPark, car, gunWharf, cinema, lobby1, lobby2, screen1, screen2, screen3, screen4, fireExit, rescuePoint;
        MovableObject keyEntrance, health, food;
        FixedObject chair;

        keyEntrance = MovableObject.create("key")
                           .withDescription("A key that unlocks museum entrance door")
                           .andHasPasscode(900);       
       
        health = MovableObject.create("health")
                           .withDescription("A health Potion that recovers 12")
                           .andHealthPotion(12);
        food = MovableObject.create("food")
                           .withDescription("Food")
                           .andWeight(22);

        chair = FixedObject.create("chair")
                           .withDescription("An old wooden chair");
       
        
        exhibit = Location.create(); // Start Location
        reception = Location.create();
        cafe = Location.create();
        guardOffice = Location.create();
        museumEntrance = Location.create();
        museumCarPark = Location.create();
        car = Location.create();
        
        gunWharf = Location.create();
        
        cinema = Location.create();
        lobby1 = Location.create();        
        lobby2 = Location.create();
        screen1 = Location.create();
        screen2 = Location.create();
        screen3 = Location.create();
        screen4 = Location.create();
        
        fireExit = Location.create();        
        rescuePoint = Location.create();
        
        
        exhibit.addDescription("Medieval Exhibit")
                .withExit("south", reception)
                .andHasObject(chair)
                .andHasObject(health);
               
        reception.addDescription("Museum Reception")
               .withExit("east", cafe)
               .withExit("south", museumEntrance)
               .withExit("west", guardOffice)
               .withExit("north", exhibit)
               .andEnemy(enemy1);

        guardOffice.addDescription("Security Guard Office")
           .withExit("east", reception)
           .andPasscode(500) // Unlocked by keyOffice
           .andHasObject(keyEntrance);
        
        cafe.addDescription("Museum Cafe")
           .withExit("west", reception)
           .andHasObject(health)                          
           .andHasObject(food)               
           .andFriend(sam);

        museumEntrance.addDescription("Museum Entrance")
            .withExit("north", reception)
            .withExit("south", museumCarPark)
            .andPasscode(900); // Unlocked by keyOffice 
            
        museumCarPark.addDescription("Museum Car Park")
            .withExit("north", museumEntrance)
            .withExit("south", gunWharf);
            
        gunWharf.addDescription("Gun Wharf Shoping Floor 1")
            .withExit("north", car)
            .withExit("south", cinema);
            
        cinema.addDescription("Cinema Entrance")
            .withExit("north", gunWharf)
            .withExit("west", lobby1)
            .withExit("south", lobby2)
            .withExit("east", fireExit);
            
        lobby1.addDescription("Cinema Lobby for screens 1 and 2")
            .withExit("east", cinema)
            .withExit("west", screen1)            
            .withExit("south", screen2);        
            
        lobby2.addDescription("Cinema Lobby for screens 3 and 4")
            .withExit("north", cinema)
            .withExit("east", screen4)            
            .withExit("south", screen3)
            .andHasObject(food)               
            .andFriend(sam);
            
        screen1.addDescription("Cinema Screen 1")
            .withExit("north", lobby1)
            .withExit("east", screen2);
            
        screen2.addDescription("Cinema Screen 2")
            .withExit("north", lobby1)
            .withExit("west", screen1)   
            .withExit("east", screen3);
            
        screen3.addDescription("Cinema Screen 3")
            .withExit("north", lobby2)
            .withExit("west", screen2)   
            .withExit("east", screen4)
            .andFriend(john);
            
        screen4.addDescription("Cinema Screen 4")
            .withExit("north", lobby2)
            .withExit("west", screen3);
            
            
        fireExit.addDescription("Cinema Fire Exit")
            .withExit("east", rescuePoint)
            .withExit("west", cinema)
            .andEnemy(enemy3)
            .andPasscode(200); // Unlocked by keyBoat 
            
        rescuePoint.addDescription("RESCUE POINT")
            .withExit("west", fireExit);
            
        
        
        gameData.addLocation(exhibit);
        gameData.addLocation(reception);
        gameData.addLocation(cafe);
        gameData.addLocation(guardOffice);
        gameData.addLocation(museumEntrance);
        gameData.addLocation(museumCarPark);
        gameData.addLocation(car);
        gameData.addLocation(gunWharf);
        gameData.addLocation(cinema);
        gameData.addLocation(lobby1);
        gameData.addLocation(lobby2);
        gameData.addLocation(screen1);
        gameData.addLocation(screen2);
        gameData.addLocation(screen3);
        gameData.addLocation(screen4);
        
        gameData.addLocation(fireExit);
        gameData.addLocation(rescuePoint);
        
        
        gameData.setNewLocation(exhibit);
    }
    
    /**
     * Create Characters Method 
     *
     * Creates the initial player character and all the friends/ enemies 
     * character form the Player/Enemy/Friend classes
     */
    private void createCharacters() // segregate this method into friends, enemies, and players
    {
        MovableObject sword;

        sword = MovableObject.create("sword")
                             .withDescription("Steel sword")
                             .andWeight(3)
                             .withWeaponModifier(3.1);

        player1 = Player.create("Player 1")
                        .hasStrength(100)
                        .withHealth(100)
                        .andHasWeapon(sword);

        gameData.addPlayer(player1);

        enemy1 = new Enemy("enemy1");
        enemy2 = new Enemy("enemy2");
        enemy3 = new Enemy("enemy4");

         // Enemies Weapons 
        MovableObject axe, mace, dagger, food, keyOffice, keyBoat;

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
        keyOffice = MovableObject.create("key")
                           .withDescription("A key that unlocks security office door")
                           .andHasPasscode(500);
                           
        keyBoat = MovableObject.create("key")
                           .withDescription("A key that starts the engine of rescue boat")
                           .andHasPasscode(500);
        // upon enemy death, drop weapon in current room?
        enemy1.hasStrength(50)
             .withHealth(90)
             .andHasObject(dagger)           
             .andHasObject(keyOffice);

        enemy2.hasStrength(80)
            .withHealth(110)
            .andHasObject(mace); // Needs key for another door

        enemy3.hasStrength(100)
           .withHealth(130)
           .andHasObject(axe);
       
        MovableObject health = MovableObject.create("health")
                                           .withDescription("a Health Potion")
                                           .andHealthPotion(15);

        sam = Friend.create("sam")
                       .withHint("Would you like a health potion? {take health from sam}")
                       .andHasObject(health);

        james = Friend.create("james")
                      .withHint("Find John he watching a movie!!");

        jeremy = Friend.create("jeremy")
                       .withHint("John has the rescue boat key");

        john = Friend.create("john")
                     .withHint("Here the key for the rescue boat, please take it")
                     .andHasObject(keyBoat);
    }
     /**
     * CombatFight Method 
     *
     * Used when the player types in: fight ENEMY-NAME with WEAPON
     * method will loop until either the player or the enemy runs out of health points
     * Both the player and enemy have weapons in there inventory and use them every time they attack.
     * 
     * Method return whether the player has won, true or false. It also prints messages to the player with health stats
     *
     * @param enemy The enemy object you are fighting 
     * @param weapon The weapon you have selected from your inventory
     * @param player The player object of player1
     * 
     * @return true if player win the fight
     */
    private boolean combatStartFight(Enemy enemy, MovableObject weapon, Player player)
    {
        boolean playerWin = false;
        boolean enemyDefending = false; // Not used yet
        boolean playerDefending = false; // Not used yet 
        int enemyDealt = 0;
        int playerDealt = 0;
        int totalEnemyDealt = 0;
        int totalPlayerDealt = 0;

        while(!playerWin) {
            // validate the movable object weapon before using it (do this in the 'fight' method in CommandActions
            playerDealt = (int) Math.floor(15*weapon.getWeaponModifier());                
            enemyDealt = (int) Math.floor(14*enemy.getInventory().getWeapon().getWeaponModifier()+2);

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
                
                HashMap<String, MovableObject> enemyHash = enemy.getInventory().getArrayInventory(); // Convert Hash Map to arrayList
                ArrayList<MovableObject> enemyArray = new ArrayList<MovableObject>(enemyHash.values());

                for (MovableObject item : enemyArray){
                    enemy.getInventory().dropFromInventory(item.getObjectName());
                    gameData.getCurrentLocation().andHasObject(item);
                }
                break;
            }
        }

        return playerWin;
    }
    
     /**
     * Used when you first start the game.
     * 
     * @return welcome text
     */
    private String welcome()
    {
        return "---------------------------------------------------\n"
               +"You wake up at Porsmouth Museum in 2014 not knowing how you got there.\n"
               +"But there is something quite different about the world you have woken up in.\n"
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
