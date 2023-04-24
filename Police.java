/**
 * Class which handles the police interactions with the player.   
 *
 * @author Michael Kiffer
 * @version ver1.0.0
 */
public class Police
{
    /**
    * Default constructor for the police object.    
    *
    */
    public Police()
    {

    }

    /**
    * Private method which calculates the size of the bribe, given the number of
    * coins the player has. 
    *    
    * @param coins          Takes number of coins player has.
    * @return               An integer representing the size of the bribe.
    */
    public int calculateBribe(int coins)
    {
        Probability calculator = new Probability();
        //calculate the size of the bribe using the number of coins
        //which can be passed by the player object. 
        int bribe = calculator.randomInt(coins/2, (3*coins)/2);
        return bribe;
    }

    /**
    * Method to handle a police interaction. Provides the option for player to
    * choose whether to Jump, Bribe, or use a cloak. This method first checks 
    * what options are available to the player and then serves the user these
    *  options on the screen. The method returns a string containing J, B, or C 
    * and combinations of each or Jail if the player has no coins jumps or 
    * cloaks.
    *
    * @param hasCloak           Boolean, true if player has a cloak.
    * @param hasCoins           Boolean, true if player has coins.
    * @param hasJumps           Boolean, true if player has jumps.
    * @return                   String options for player to choose.
    */
    public String offerChoices(boolean hasCloak, boolean hasCoins, boolean hasJumps)
    {        
        String optionsOutput = "\t These are your choices: ";
        String tryAgain = "\t Please select a valid choice";
        Input userInput = new Input();
        Validation check = new Validation();
        String jump = "Jump (J) ";
        String bribe = "Bribe (B) ";
        String cloak = "Cloak (C) ";
        boolean flag = true;
        String outputString = "No Selection";
        String jumpSelection = "J, ";
        String bribeSelection = "B, ";
        String cloakSelection = "C, ";
        //create an if statment to provide user with choices.
        if ((hasCloak) && (hasCoins) && (hasJumps))
        {
            System.out.println(optionsOutput + jump + bribe + cloak);
            outputString = jumpSelection + bribeSelection + cloakSelection; 
        }
        else if ((hasCloak) && (hasCoins) && (!hasJumps))
        {
            System.out.println(optionsOutput + bribe + cloak);
            outputString = bribeSelection + cloakSelection;                    
        }
        else if ((!hasCloak) && (hasCoins) && (hasJumps))
        {
            System.out.println(optionsOutput + jump + bribe);
            outputString = jumpSelection + bribeSelection;
             
        }
        else if ((hasCloak) && (!hasCoins) && (hasJumps))
        {
            System.out.println(optionsOutput + jump + cloak);
            outputString = jumpSelection + cloakSelection;
        }
        else if ((!hasCloak) && (hasCoins) && (!hasJumps)) 
        {
            System.out.println(optionsOutput + bribe);
            outputString = bribeSelection;
        }
        else if ((hasCloak) && (!hasCoins) && (!hasJumps))
        {
            System.out.println(optionsOutput + cloak);
            outputString = cloakSelection;            
        }
        else if ((!hasCloak) && (!hasCoins) && (hasJumps))
        {
            System.out.println(optionsOutput + jump);
            outputString = jumpSelection;
        }
        else
        {
            System.out.println("\t You have no options - "
            + "you are going to jail!");
            outputString = "Jail";
        }

    return outputString;
    } 
}
