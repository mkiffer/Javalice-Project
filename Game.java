import java.util.Scanner;
import java.util.ArrayList;

/**
* Class which runs the information for the game
*
* @author Michael Kiffer
* @version ver1.0.0
*/
public class Game
{
    public static final String FILE_NAME = "/home/exits.txt";
    private Room room;
    private Player player;
    private Police police;
    private FileIO writer;
    
    /**
     * Default constructor which creates the object of the class Game.
     * Initialises a room, player, police
     *
     */
    public Game()
    {
        room = new Room();
        player = new Player();
        writer = new FileIO();
        police = new Police();
    }

    /**
     * Non-Default constructor which creates the object of the class Driver.
     *
     * @param room          Accepts the starting room as a Room object.
     * @param player        Accepts the player object.
     * @param police        Accepts the police object.
     */
    public Game(Room room, Player player, Police police)
    {
        this.room = room;
        this.player = player;
        this.police = police;
    }

    /**
    * This method will activate items that the player cannot use. The police 
    * alarm is the responsibility of the room object and the coal does nothing. 
    *
    * @param itemInside         a string of the item inside of the magic chest. 
    */
    public void activateItemInChest(String itemInside)
    {
        switch (itemInside)
        {
            case "Police Alarm":
                System.out.println("You found a " + itemInside + 
                ". The probability of finding the police has been "
                + "raised by 0.03 in all directions.");
                room.raisePoliceAlarm();
                room.displayPortals();
                break;
            case "Coal":
                System.out.println("You found coal, bad luck!");
                break;
            default:
                break;
        }
    } 

    /**
    * Clears the screen so the next room can be displayed cleanly
    *
    */
    public void clearScreen()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    /**
     * Display method to return the state of the room and player, 
     * including open portals and associated probabilities, as well 
     * as player information about jumps, coins and inventory.
     * This method will print to the console. 
     * 
     */
    public void display()
    {
        player.display();
        room.displayCurrent();
    }

    /**
     * Accessor method to get the player object.
     *
     * @return              The Player object.
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * Accessor method to get the FileIO object.
     *
     * @return              The file writer object.
     */
    public FileIO getWriter()
    {
        return writer;
    }

    /**
     * Accessor method to get the current room object.
     *
     * @return              The Room object.
     */
    public Room getRoom()
    {
        return room;
    }

    /**
     * Accessor method to get the current police object.
     *
     * @return              The police object.
     */
    public Police getPolice()
    {
        return police;
    }

    /**
    * Method to handle the jumpback sequence in the game. This method will 
    * create a new room given the magic police probability from the previous
    * room. 
    *
    * @param magicPoliceProb  double of the probability of encountering police.
    */
    public void jumpBack(double magicPoliceProb)
    { 
        room.updateRoom(magicPoliceProb);
        player.minusJumpBack();
    }

    /**
    * Main method which drives the game. First player must input name. Room
    * is initialised. Main loop checks if there are any exits, if not a 
    * jumpback is performed. If no jumps, the game is over. Then, the room 
    * is checked for police, once dealt with, the chest, if present can be 
    * accessed. The player is then asked to select from the available portals. 
    * A new room is then generated and the loop continues
    * until either an exit is found or the player is caught by the police. 
    *
    * @param args       Command line arguments, not applicable for this application. 
    */
    public static void main(String[] args)
    {
        Game game = new Game();
        String winLose = ""; // final string to be written to output file.
        FileIO inputFile = new FileIO(FILE_NAME);
        String contents = inputFile.readFile();
        //initialise first room and set that to the game room
        Room firstRoom = game.getRoom().initialiseRoom(4, contents);
        game.setRoom(firstRoom);
        Room room = game.getRoom();
        Player player = game.getPlayer();
        Inventory inventory = player.getInventory();
        //set initial police prob to the north portal
        double policeProb = room.getPortals()[0].getMagicPoliceProb();
        //Start game by asking for name
        player.inputPlayerName();
        System.out.print("\n You must escape the maze, move" 
        + " through the portals to escape the police. ");
        System.out.println("Keep an eye out for exits," 
        + " they are your only chance for survival! \n");
        boolean play = true;
        do
        {
            //set up all boolean checks required for gameplay
            boolean jumpBack = false;
            boolean thereAreNoExits = room.getOpenPortalStrings().length() == 0;
            boolean policeAreHere = room.isPolicePresent();
            boolean hasCloak = inventory.hasItem(); 
            boolean hasCoins = player.hasCoins();
            boolean hasJumps = player.hasJumps();
            int coins = player.getCoins();
            game.display();
            
            //check if no exits. if there is a chest in the room
            // give user choice to open it, then restart do loop.
            if (thereAreNoExits)
            {
                if (player.getJumpBack() == 0)
                {
                    String stuck = "\t You are stuck in the room and there " 
                     + "is no way out! You went to Jail. GAME OVER!";
                    System.out.println(stuck);
                    winLose += stuck;
                    play = false;
                }
                else
                {
                    if (room.isChestPresent())
                    {
                        MagicChest chest = room.getChest();
                        String itemInside = player.openChest(chest);
                        game.activateItemInChest(itemInside);         
                    }
                    player.noExits(); //press any key to continue. 
                    game.clearScreen();
                    game.jumpBack(policeProb);
                    jumpBack = true;
                }  
            }
            //then check for police
            if ((policeAreHere) && (play) && (!jumpBack))
            {
                System.out.println("\t HALT! Magic police! Put your hands "
                + "where we can see them!");
                boolean inTrouble = true;
                String playerChoice = "";
                String policeChoice = "";
                do
                {
                    policeChoice = game.getPolice().offerChoices(
                        hasCloak, hasCoins, hasJumps);
                    playerChoice = player.respondToPolice(policeChoice);
                    switch (playerChoice)
                    {
                        case "J":
                        case "j":
                            inTrouble = false;
                            game.clearScreen(); 
                            game.jumpBack(policeProb);
                            System.out.println("\t You have jumped"
                            + " to a new room!");
                            jumpBack = true; 
                            break;
                        case "B":
                        case "b":
                            int bribe = game.getPolice().calculateBribe(coins);
                            String choice = player.payBribe(coins, bribe);
                            if (choice.matches("N") || choice.matches("n"))
                            {
                    //player will not be able to select bribe as an option
                                hasCoins = false;
                            }
                            else
                            {
                                inTrouble = false;
                                game.display();
                                System.out.println("\t...but you bribed them");  
                            }
                            break;
                        case "C":
                        case "c":
                            player.useItem("Cloak");
                            inTrouble = false;
                            game.display();
                            System.out.println("\t...but they can't see you");
                            break;
                        default:
                            inTrouble = false;
                    }
                } while (inTrouble);
                if (playerChoice.equals("Jail"))
                {
                    System.out.println("\tYou are out of choices! GAME OVER!");
                    winLose += "You ended up in Jail!";
                    play = false;
                }
            }
            //then check for chest
            if ((room.isChestPresent()) && (play) && (!jumpBack))
                {
                    MagicChest chest = new MagicChest();
                    String itemInside = player.openChest(chest);
                    game.activateItemInChest(itemInside);       
                }
            //prompt user to select a portal and store the choice as an integer
            if((play) && (!jumpBack))
            {
                int index = player.makePortalChoice(game.room);
                //check for exit
                if (room.getPortals()[index].isExit())
                {
                    String congrats = "You have escaped Javalice, CONGRATS!";
                    System.out.println(congrats);
                    winLose += congrats;
                    play = false;
                }
                else
                {
                    room.updatePortalProbs(index);
                    Portal entryPortal = room.getPortal(index);
                    policeProb = entryPortal.getMagicPoliceProb();
                    room.updateRoom(policeProb);
                    game.clearScreen();
                }    
            }        
        } while (play);

        FileIO writer = game.getWriter();
        writer.writeFile(winLose, "outcome");
    }

    /**
    * Mutator method for the player field.
    *
    * @param player         Accepts a player object to replace the current one. 
    */
    public void setPlayer(Player player)
    {
        this.player = player;
    }

    /**
    * Mutator method for the police field.
    *
    * @param police         Accepts a police object to replace the current one. 
    */
    public void setPolice(Police police)
    {
        this.police = police;
    }

    /**
    * Mutator method for the room field.
    *
    * @param room         Accepts a room object to replace the current one. 
    */
    public void setRoom(Room room)
    {
        this.room = room;
    }

    /**
    * Mutator method for the writer field.
    *
    * @param writer         Accepts a FileIO object to replace the current one. 
    */
    public void setWriter(FileIO writer)
    {
        this.writer = writer;
    }
}
