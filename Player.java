/**
 * Class which handles all of the player actions.
 *
 * @author Michael Kiffer
 * @version ver1.0.0
 */
public class Player
{
    private String name;
    private int coins;
    private int jumpBack;
    private Inventory inventory;
    private Input input;
    private Validation check;
    
    /**
    * Default constructor for the player class.
    *
    */
    public Player()
    {
        name = "Player 1";
        coins = 10;
        jumpBack = 3;
        inventory = new Inventory(3); 
        input = new Input();  
        check = new Validation();
    }

    /**
    * Parameterised constructor for the player class. 
    * If invalid fields are specified, default constructor values are assigned.
    *
    * @param name       accepts string as player name. 
    * @param coins      accepts the number of coins the player starts with.
    * @param jumpback   accepts the starting number of get out of jail jumps.
    * @param inventory  accept inventory object to hold items for the player. 
    */
    public Player(String name, int coins, int jumpback, Inventory inventory)
    {
        if (check.lengthWithinRange(name, 3, 25))
        {
            this.name = name;
        }
        else
        {
            this.name = name = "Player 1";
        }
        if (coins >= 0)
        {
            this.coins = coins;
        }
        else
        {
            this.coins = 0;
        }
        if (jumpback >= 0)
        {
            this.jumpBack = jumpback;
        }
        else
        {
            this.jumpBack = 3;
        }   
        this.inventory = inventory; 
        input = new Input();
        check = new Validation();
    }

    /**
    * Method allows player to add an item to their inventory. If there is 
    * space in the inventory, items are looped through until an empty 
    * space is found, the item is replaced and then the loop is exited.
    *
    * @param item        Accepts a string which describes the type of the item. 
    */
    public void addItemToInventory(String item)
    {
        if (!check.isBlank(item))
        {        
            for (Item itemInInv : inventory.getItemArray())
            {
                if (itemInInv.getType().equals("Empty"))
                {
                    itemInInv.setType(item);
                    break;
                }
            }
        }
        else
        {
            System.out.println("Item type cannot be blank.");
        }   
    }

    /**
    * Method will print player information about coins, jumping and 
    * inventory, to the console.
    *
    */
    public void display()
    {
        System.out.print(name);
        System.out.print("\t" + coins + " coins");
        System.out.print("\t" + jumpBack + " Get out of jail jumps\t");
        inventory.display();
    }

    /**
    * Accessor method for player coins
    *
    * @return       Number of coins player has.
    */
    public int getCoins()
    {
        return coins;
    }

    /**
    * Accessor method for player inventory
    *
    * @return       The player's inventory object .
    */
    public Inventory getInventory()
    {
        return inventory;
    }

    /**
    * Accessor method for number of get out of jail jumps
    *
    * @return       Number of jumps the player has.
    */
    public int getJumpBack()
    {
        return jumpBack;
    }

    /**
    * Accessor method for player name
    *
    * @return       String containing name of the player.
    */
    public String getName()
    {
        return name;
    }

    /**
    * Boolean method which indicates whether the player has any coins. 
    *
    * @return       True is player has coins. 
    */
    public boolean hasCoins()
    {
        boolean output = false;
        if (coins > 0)
        {
            output = true;
        }
        return output;
    }

    /**
    * Boolean method which indicates whether the player has any jumps left. 
    *
    * @return       True is player has more than 0 jumps left. 
    */
    public boolean hasJumps()
    {
        boolean output = false;
        if (jumpBack > 0)
        {
            output = true;
        }
        return output;
    }
    /**
    * Method that handles the start of the game and accepts the player 
    * name from the console. Checks for length of name between 3 and 25 
    * characters.
    *
    */
    public void inputPlayerName()
    {
        String nameOutput = "Please enter your name:";
        boolean flag = true;
        do
        {
            String name = input.acceptStringInput(nameOutput);
            if (check.lengthWithinRange(name, 3, 12))
            {
                setName(name);
                flag = false;
            }
            else
            {
                System.out.println("\t Your name must be " 
                + "between 3 and 12 characters");
            }
        } while (flag);
        System.out.println("\t\t\t Welcome, " + name + ", to Javalice");
    }

    /**
    * Method presents player with a list of open portals and accepts a single 
    * character choice. Will accept
    * either upper or lower case. The method will return an integer which corresponds to the portal chosen. 
    * This integer can then be used to update probabilities of this portal for the next room.
    *
    * @param room           Accepts the current room object as input. 
    * @return               Returns an integer,  0:N, 1:W, 2:E, 3:S
    */
    public int makePortalChoice(Room room)
    {
        //prompts user to choose a room and returns an integer that corresponds
        //to the portal that needs to be updated 0:N, 1:W, 2:E, 3:S
        String choiceOutput = "\t Choose your portal";
        String openPortals = room.getOpenPortalStrings();
        boolean flag = true;
        System.out.println("\t Open Portals: " + openPortals);
        String choice = input.acceptStringInput(choiceOutput);
        int output = -1;
        do
        {      
            if (check.validChoice(choice, openPortals))
            {
                flag = false;
            }
            else if (room.getOpenPortalStrings().length() == 0)
            {
                flag = false;
            }
            else
            {
                System.out.println("\t Please enter an appropriate character");
                System.out.println("\t Open Portals: " + openPortals);
                choice = input.acceptStringInput(choiceOutput);
            }
        } while (flag); 
        switch(choice)
        {
            case "N":
            case "n":
                output = 0;
                break;
            case "W":
            case "w":
                output = 1;
                break;
            case "E":
            case "e":
                output = 2;
                break;
            case "S":
            case "s":
                output = 3;
                break;
            default:
                break;
        }
        return output;
    }

    /**
    * Method to be used during the get out of jail jump. Reduces the number of jumps the player has by 1. 
    *
    */
    public void minusJumpBack()
    {
        if (jumpBack > 0)
        {
            jumpBack -= 1;
        }
    }

    /**
    * if no exits are found the player must acknowledge this and enter something to continue.
    *
    */
    public void noExits()
    {
        System.out.println("\t This room has no exits! Using 1 jump back!");
        input.acceptStringInput("Press any key to continue");
    }

    /**
    * Method to allow player to open a magic chest. Prompts user to select "y" to open 
    * the chest or no to skip. If the player chooses to open the chest, the method will return 
    * a string which indicates the type of the object. Coins and cloak cases are handled by the player object
    * while coal and police alarm are handled by the game object.  
    *
    * @param chest          Accepts the current magic chest object.  
    * @return               String describing the type of the object. 
    */
    public String openChest(MagicChest chest)
    {
        boolean flag = true;
        String itemInside = "";
        do
        {
            String choice = input.acceptStringInput("Would you like to open the chest? y/n");
            boolean validChoice = check.validChoice(choice, "YN");
            if (validChoice)
            {
                if ((choice.equals("Y")) || (choice.equals("y")))
                {
                    itemInside = chest.showItem();
                    switch (itemInside)
                    {
                        case "Coins":
                            int coinsInside = chest.generateCoins();
                            System.out.println("\t You found " + coinsInside + " coins");
                            setCoins(getCoins() + coinsInside);
                            display();
                            break;
                        case "Cloak":
                            if(inventory.hasSpace())
                            {
                                System.out.println("You found an Invisibility " + itemInside + 
                                ". It was added to the next available slot in your inventory.");
                                addItemToInventory(itemInside);
                            }
                            else
                            {
                                System.out.println("You found an Invisibility " + itemInside + 
                                ", but there is no space in your inventory.");
                            }
                        //if space in inventory add to first available space
                            break;
                        default:
                            break;
                    }
                    
                    flag = false;
                }    
                    
                else if ((choice.equals("N")) || (choice.equals("n")))
                {
                    System.out.println("\t You did not open the chest.");
                    flag = false;
                }
            }     
            else
            {
                System.out.println("Please select either Y or N");
            }
        } while (flag);
        return itemInside;    
    }

    /**
    * Method handles whether or not the player can pay the bribe calculated by the police. 
    * If the player refuses to pay the bribe they will be asked to select a different option to 
    * escape the police. 
    *
    * @param coins          number of coins the player currently has.
    * @param bribe          size of the bribe presented by the police. 
    * @return               String describing whether player pays or not (Y/N).
    */
    public String payBribe(int coins, int bribe)
    {
        String output = "";
        if ((coins > 0) && (bribe > 0))
        {
            System.out.println("\t You must pay " + bribe + "coins");
            if (bribe <= coins)
            {
                //allow user to choose whether they want to pay the bribe
                //if they choose not too, let them choose again
                boolean validSelection = false;
                do
                {
                    String choice = input.acceptStringInput("\t Would you like to pay the bribe? Y/N");
                    if (choice.matches("y") || choice.matches("Y"))
                    {
                        setCoins(coins-bribe);
                        output = choice;
                        validSelection = true; 
                    }       
                    else if (choice.matches("n") || choice.matches("N"))
                    {
                        System.out.println("\t Ok well, how are you going to escape?");
                        output = choice;
                        validSelection = true;
                    }
                    else
                    {
                        System.out.println("\t please select either y or n");
                    }
                } while (!validSelection);
                        
            }
            else
            {
                System.out.println("\t You cannot pay this bribe");
                output = "n";
            }
        }
        else
        {
            System.out.println("Both bribe and coin values must be greater than 0");
        }
        return output; 
    }

    /**
    * Accepts a string of choices from the police class and allows the player to choose how
    * they will escape from the police. 
    *
    * @param choices            String containing a combination of J,B or C. Or Jail if the player has no options.
    * @return                   String containing the player choice. 
    */
    public String respondToPolice(String choices)
    {
        boolean flag = true;
        String output = "";
        //output string has already been printed by the police
        do 
        {
            if (choices.strip().length() == 0)
            {
                output = "Invalid choices provided";
                break;
            }
            if (choices.equals("Jail"))
            {
                output = "Jail";
                break;
            }
            String choice = input.acceptStringInput("");
            
            if (check.validChoice(choice, choices))
            {
                flag = false;
                output = choice; 
            }
            else
            {
                System.out.println("Please select one of " + choices);
            }
        } while (flag);
        return output;
    }

    /**
    * Mutator method for number of coins player has. 
    *
    * @param coins              new number of coins the player has.
    */
    public void setCoins(int coins)
    {
        this.coins = coins;
    }

    /**
    * Mutator method for number of jumps player has. 
    *
    * @param jumpBack           new number of jumps the player has.
    */
    public void setJumpBack(int jumpBack)
    {
        this.jumpBack = jumpBack;
    }

    /**
    * Mutator method for players name. 
    *
    * @param name           new name of the player.
    */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
    * Method allow the player to use an item. Method checks for item that matches the input,
    * and removes it from the inventory. Once an item has been found the loop exits to avoid 
    * removing more than one item at a time. 
    *
    * @param itemType           Takes the item type to compare against what is already in the inventory.
    */
    public void useItem(String itemType)
    {
        for (Item item : inventory.getItemArray())
        {
            if (item.getType().equals(itemType))
            {
                item.setType("Empty");
                System.out.println("\t You have used 1 " + itemType);
                break;
            }
        }
    }
}
