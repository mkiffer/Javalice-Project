import java.util.Random;
import java.util.ArrayList;

/**
 * Class which handles all the probability calculations for various classes within the game.
 * There are two types of probabilities within the game, binary dice rolls (weight coin flips) 
 * and four outcome dice rolls. 
 *
 * @author Michael Kiffer
 * @version ver1.0.0
 */
public class Probability
{
    private double threshold;
    private Random dice;
    private double[] probList;
    
    /**
    * Default constructor for the probability object.
    *
    */
    public Probability()
    {
        threshold = 0.5;
        dice = new Random();
        probList = new double[1];
    }

    /**
    * Parameterised constructor for the probability object that will be used in a weighted coin flip.
    *
    * @param threshold          Takes the threshold for a binary dice roll.      
    */
    public Probability(double threshold)
    {
        //constructor for binary decision
        this.threshold = threshold;
        dice = new Random();
        probList = new double[1];
    }

    /**
    * Parameterised constructor for the probability object that will be used in a multiple outcome dice roll.
    *
    * @param inputList          Takes a list of doubles which contains the relative probability of each outcome.      
    */
    public Probability(double[] inputList)
    {
        //constructor for multiple outcomes
        probList = new double[4];
        for (int i = 0; i < inputList.length; i++)
        {
            probList[i] = inputList[i];
        } 
        threshold = 0.5;
        dice = new Random();
    }

    /**
    * Turns the list of relative probabilities into a list of culmulative probabilities
    * that can be used to decide the outcome of a dice roll. 
    *
    * @return          Creates an ArrayList of doubles which contain culmulative probability.      
    */
    private ArrayList<Double> culmulativeProbList()
    {
        ArrayList<Double> culmProb = new ArrayList<Double>();
        double culmSum = 0;
        for(int i = 0; i < probList.length; i++)
        {
            culmSum += probList[i];
            culmProb.add(culmSum);
        }
        return culmProb;
    }

    /**
    * Accessor method will return the list of relative probabilities.
    * 
    * @return                array of relative probabilities.      
    */
    public double[] getProbList()
    {
        return probList;
    }

    /**
    * Accessor method will return the threshold of a binary dice roll.
    * 
    * @return                threshold of a binary dice roll.      
    */
    public double getThreshold()
    {
        return threshold;
    }

    /**
    * Chooses the increment size and decides whether the increment will be positive or negative. 
    * This is used when updating portal probabilities. 
    *
    * @param min        lower bound of the random increment.
    * @param max        upper bound of the random increment.
    * @return          a double between -0.5 and 0.5      
    */
    public double increment(int min, int max)
    {
        //convert random integer to a double and represent in decimal format
        double incrementSize = ((double)(randomInt(min,max)))/100;
        if (rollBinaryDice())
        {
            return incrementSize;
        }
        else
        {
            return -incrementSize;
        }           
    }

    /**
    * Random number generator 
    *
    * @param min        lower bound.
    * @param max        upper bound.        
    * @return           a random integer.      
    */
    public int randomInt(int min, int max)
    {
        int num = dice.nextInt((max - min) + 1) + min;
        return num;
    }

    /**
    * Binary dice (weighted coin flip).  
    *        
    * @return           True if the random number generated is less than the threshold.      
    */
    public boolean rollBinaryDice()
    {
        double roll = dice.nextDouble();
        if (roll < threshold)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    /**
    * Binary dice (weighted coin flip).  
    * 
    * @param weight     Threshold for the coin flip.       
    * @return           True if the random number generated is less than the threshold.      
    */
    public boolean rollBinaryDice(double weight)
    {
        double roll = dice.nextDouble();
        if (roll < weight)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    /**
    * Create an array list with cumulative sum of the probabilities.
    * checks to see if the random number value is less than each of the c
    * cumulative probability. This method is used when deciding what will come
    * out of a chest.
    *
    * @return           The index of the first element that is greater than the random number.
    */
    public int rollDice()
    {
        ArrayList<Double> probArray = culmulativeProbList();
        double value = dice.nextDouble();
        int output = -1;
        for (int i = 0; i < probArray.size(); i++)
        {
            if (value < probArray.get(i))
            {
                output = i;
                break;
            }
        }
        return output;
    }

    /**
    * Mutator method for the Random object. 
    *
    * @param dice           new Random object.
    */
    public void setDice(Random dice)
    {
        this.dice = dice;
    } 

    /**
    * Mutator method for the probability list. 
    *
    * @param probList           new probability list.
    */
    public void setProbList(double[] probList)
    {
        this.probList = probList;
    } 

    /**
    * Mutator method for the threshold used in binary dice roll (weighted coin flips) 
    *
    * @param threshold.
    */
    public void setThreshold(double threshold)
    {
        this.threshold = threshold;
    }  
}
