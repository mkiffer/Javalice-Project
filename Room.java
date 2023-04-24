import java.lang.StringBuilder;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Class which handles the actions of a room object. A room may have four portals, as well as
 * indicators about what is in side of the room, like police or magic chests.  
 *
 * @author Michael Kiffer
 * @version ver1.0.0
 */
public class Room
{
    private Portal north;
    private Portal east;
    private Portal south;
    private Portal west;
    private Portal[] portals;
    private boolean policePresent;
    private boolean chestPresent;
    private double chestProb;
    private MagicChest chest;

    /**
    * Default constructor for the room object. 
    *
    */
    public Room()
    {
        north = new Portal("North");
        east = new Portal("East");
        south = new Portal("South");
        west = new Portal("West");
        portals = new Portal[]{north, east, south, west};
        policePresent = false;
        chestPresent = false;
        chestProb = 0.5;
        chest = new MagicChest();
    }
    
    /**
    * Parameterised constructor for the room object given an array of portal objects. 
    *
    * @param portals            An array of portal objects.
    */
    public Room(Portal[] portals)
    {
        this.portals = portals;
        north = portals[0];
        east = portals[1];
        south = portals[2];
        west = portals[3];
        policePresent = false;
        chestPresent = false;
        chestProb = 0.5;
        chest = new MagicChest();
    }

    /**
    * Parameterised constructor for the room object given an array of portal objects
    * and booleans representing presence of police and magic chests. 
    *
    * @param portals            An array of portal objects.
    * @param policePresent      Boolean true if police are present
    * @param chestPresent       Boolean true if chest is present
    */
    public Room(Portal[] portals, boolean policePresent, boolean chestPresent)
    {
        north = portals[0];
        east = portals[1];
        south = portals[2];
        west = portals[3];
        this.policePresent = policePresent;
        this.chestPresent = chestPresent;
        chestProb = 0.5;
        chest = new MagicChest();
    }

    /**
    * Used for testing: Closes all portals in the room.
    */
    public void closePortals()
    {
        for (Portal port : portals)
        {
            port.setIsOpen(false);
        }
    }

    /**
    * Prints the state of the room including open portals, chance of police encounter,
    * chance of exit and, if a portal is an exit will tell the user. Will display if police 
    * or chest is present.
    *
    */
    public void displayCurrent()
    {
        for (Portal port : portals)
        {
            if (port.isOpen())
            {
                System.out.println("\t " +port.getDirection());
                System.out.printf("\t %.2f chance of police encounter \t", port.getMagicPoliceProb());
                
                if (port.isExit())
                {
                   System.out.printf("\t EXIT!!!");
                }
                else
                {
                    System.out.printf("\t %.2f chance that portal is exit", port.getExitProb());
                }
                System.out.println("");
            }
        }
        System.out.println(chestPresent ? ("\t There is a chest"):("\t There is no chest")); 
        System.out.println(policePresent ? ("\t The police are here"):("\t The coast is clear"));          
    }

    /**
    * Prints the state of the room including open portals, chance of police encounter,
    * chance of exit and, if a portal is an exit will tell the user.
    *
    */
    public void displayPortals()
    {
        for (Portal port : portals)
        {
            if (port.isOpen())
            {
                System.out.println("\t " +port.getDirection());
                System.out.printf("\t %.2f chance of police encounter \t", port.getMagicPoliceProb());
                
                if (port.isExit())
                {
                   System.out.printf("\t EXIT!!!");
                }
                else
                {
                    System.out.printf("\t %.2f chance that portal is exit", port.getExitProb());
                }
                System.out.println("");
            }
        }          
    }

    /**
    *Accessor method for the Magic Chest object
    *
    * @return       Magic chest object.
    */
    public MagicChest getChest()
    {
        return chest;
    }

    /**
    * Returns a string of valid choices for open portals using a string builder object
    * so that the player can select an open portal. 
    *
    * @return           String containing open portals eg "N S E W" if all portals are open.
    */
    public String getOpenPortalStrings()
    {
        
        StringBuilder output = new StringBuilder(); 
        for (int i = 0; i < 4; i++)
        {
            if (portals[i].isOpen())
            {
                switch (i)
                {
                    case 0:
                        output.append("N ");
                        break;
                    case 1:
                        output.append("W ");
                        break;
                    case 2:
                        output.append("E ");
                        break;
                    case 3:
                        output.append("S ");
                        break;
                    default:
                        break;
                }
            }
        }
        return output.toString();
    }

    /**
    * Accessor method for a portal in the room. 
    *
    * @param portNum    takes an integer corresponding to a portal direction 0:North, 1: West, 2: East, 3:South
    * @return           Portal object.
    */
    public Portal getPortal(int portNum)
    {
        return portals[portNum];
    }

    /**
    * Accessor method for an Array of portals in the room. 
    *
    * @return           Portal array.
    */
    public Portal[] getPortals()
    {

        return portals;
    }

    /**
    * Method to read from file the attributes of the initial Room 
    *
    * @param numPortals           The max number of portals required for a room.
    * @param contents             String read from file containing info about the portals
    * @return                     The room object that will be the first room of the game.
    */
    public Room initialiseRoom(int numPortals, String contents)
    {
        //Creates a new room from file.
        Room room = new Room();
        //create list of portals to input into room constructor read from file
        Portal[] portals = new Portal[numPortals];
        String[] lines = contents.split(System.lineSeparator());
        
        int counter = 0;
        for (String line : lines)
        {
            String[] portalAttributes = line.split(",");
            String direction = portalAttributes[0];
            double openProb = Double.parseDouble(portalAttributes[1])/100;
            double exitProb = Double.parseDouble(portalAttributes[2])/100;
            double magicPoliceProb = Double.parseDouble(portalAttributes[3])/100;
            portals[counter] = new Portal(direction, openProb, exitProb, magicPoliceProb);
            counter++;
        }
        room.setPortals(portals);
        return room;
    }

    /**
    * Boolean method which indicates whether the room has a chest.
    *
    * @return       True chest is in room. 
    */
    public boolean isChestPresent() 
    {
        //takes probability from chosen portal object
        return chestPresent;
    }

    /**
    * Boolean method which indicates whether the room has police.
    *
    * @return       True police are in room. 
    */
    public boolean isPolicePresent()
    {
        //takes probability from chosen portal object
        return policePresent;
    }

    /**
    * Increases the police probability for all portals in a room by 3%. 
    * 
    */
    public void raisePoliceAlarm()
    {
        for (Portal port : portals)
        {
            double currentProb = port.getMagicPoliceProb();
            port.setMagicPoliceProb(currentProb + 0.03);
        }
    }

    /**
    *Mutator method for the Magic Chest object
    *
    * @param chest       Magic chest object.
    */
    public void setChest(MagicChest chest)
    {
        this.chest = chest;
    }

    /**
    * Allows testing to add or remove police from the room.
    *
    * @param police        True if police are present.
    */
    public void setPolice(boolean police)
    {
        //for testing
        policePresent = police;
    }

    /**
    * Mutator method for the boolean representing if police are in room. 
    *
    * @param policePresent        True if police are present.
    */
    public void setPolicePresent(boolean policePresent)
    {
        this.policePresent = policePresent;
    }

    /**
    * Mutator method for a specific portal
    *
    * @param portNum        takes an integer corresponding to a portal direction 0:North, 1: West, 2: East, 3:South
    * @param port           Portal object.
    */
    public void setPortal(int portNum, Portal port)
    {
        //0:North, 1: West, 2: East, 3:South
        portals[portNum] = port;
    }

    /**
    * Mutator method for an array of portals
    *
    * @param portals        takes an array of portals.
    */
    public void setPortals(Portal[] portals)
    {
        this.portals = portals;
    }

    /**
    * Updates portal police and exit probabilities when traveling through a particular portals
    *
    * @param portNum        takes an integer corresponding to a portal direction 0:North, 1: West, 2: East, 3:South
    */
    public void updatePortalProbs(int portNum)
    {
        portals[portNum].updateExitProb();
        portals[portNum].updateMagicPoliceProb();
    }

    /**
    * Updates new room after travelling through portal and decides whether police and chest are present
    * as well as which portals are open. 
    *
    * @param policeProb        double representing the police probability of the portal player travelled through.
    */
    public void updateRoom(double policeProb)
    {
        for (Portal port : portals)
        {
            port.updatePortal();
        }
        Probability police = new Probability(policeProb);
        Probability chest = new Probability(chestProb);
        if (police.rollBinaryDice())
        {
            policePresent = true;
        }
        else
        {
            policePresent = false;
        }
        if (chest.rollBinaryDice())
        {
            chestPresent = true;
        }
        else
        {
            chestPresent = false;
        }
    }
}
