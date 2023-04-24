/**
 * Class which handles all of the chest actions.
 *
 * @author Michael Kiffer
 * @version ver1.0.0
 */
public class MagicChest
{
    private Item inChest;
    private double[] itemProbs; 
    private Probability whatsInBox;

    /**
    * default constructor for the magic chest object.
    * Initialises the probabilties based on the specification
    *
    */
    public MagicChest()
    {
        inChest = new Item();
        itemProbs = new double[] {0.3,0.25,0.15,0.3};
        whatsInBox = new Probability(itemProbs); 
    }

    /**
    * Non-default constructor for the magic chest object.
    * Takes the number of items that could come out of the chest
    * and their associated probabilities. The probability object handles
    * the selection of these probabilities. If length of 
    * listOfItemProbs != numOfItems defaults to the specs of the project.
    *
    * @param numOfItems         Number of possible items in the chest
    * @param listOfItemProbs    probabilities of items (length == numOfItems)
    */
    public MagicChest(int numOfItems, double[] listOfItemProbs)
    { 
        Item inBox = new Item();
        if (listOfItemProbs.length == numOfItems)
        {
            itemProbs = new double[numOfItems];
            for (int i = 0; i < itemProbs.length; i++)
            {
                itemProbs[i] = listOfItemProbs[i];
            }
        }
        else
        {
            itemProbs = new double[] {0.3,0.25,0.15,0.3};   
        }
        whatsInBox = new Probability(itemProbs); 
    }

    /**
    * Generates a random number of coins to be awarded to the player.
    *
    * @return               number of coins for player.
    */
    public int generateCoins()
    {
        Probability numOfCoins = new Probability();
        int coinsInBox = numOfCoins.randomInt(10,35);
        return coinsInBox;
    }

    /**
    * Accessor method for the item inside the chest
    *
    * @return               Item object inside chest.
    */
    public Item getItemInChest()
    {
        return inChest;
    }

    /**
    * Accessor method for the list of probabilities of the items
    *
    * @return           Array of doubles describing the probabilities of items.
    */
    public double[] getItemProbs()
    {
        return itemProbs;
    }

    /**
    * Accessor method for the Probability object that chooses 
    * the item to come out of the box.
    *
    * @return  Probability object that chooses the item to come out of the box.
    */
    public Probability getWhatsInBox()
    {
        return whatsInBox;
    }

    /**
    * Mutator method for the item inside the chest
    *
    * @param inChest              Item object inside chest.
    */
    public void setItemInChest(Item inChest)
    {
        this.inChest = inChest;
    }

    /**
    * Mutator method for the list of probabilities of the items
    *
    * @param itemProbs  Array of doubles describing the probabilities of items.
    */
    public void setItemProbs(double[] itemProbs)
    {
        if (this.itemProbs.length == itemProbs.length)
        {
            this.itemProbs = itemProbs;
        }
        else
        {
            System.out.println("not enough elements in the given list of probabilities");
        }
    }

    /**
    * Mutator method for the Probability object that chooses the 
    * item to come out of the box.
    *
    * @param whatsInBox      Probability object that chooses the item from box.
    */
    public void setWhatsInBox(Probability whatsInBox)
    {
        this.whatsInBox = whatsInBox;
    }

    /**
    * Method to open the chest and reveal the item using the probability 
    * object associated with the chest.
    *
    * @return           String describing the object in the chest.
    */
    public String showItem()
    {
        int index = whatsInBox.rollDice();
        String output = "";
        switch (index)
        {
            case 0:
                output = "Coins";
                break;
            case 1:
                output = "Police Alarm";
                break;
            case 2:
                output = "Cloak";
                break;
            case 3:
                output = "Coal";
                break;
            default:
                break;
        }
        return output;
    }

}
