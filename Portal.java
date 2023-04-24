/**
* Portal class handles the portal object. 
*
* @author Michael Kiffer
* @version v1.0.0
*/
public class Portal
{
    private String direction;
    private double openProb;
    private double exitProb;
    private double magicPoliceProb;
    private boolean isOpen;
    private boolean isExit;
    
    /**
    * Default constructor for the portal object. 
    *
    */
    public Portal()
    {
        direction = "North";
        openProb = 0.5;
        exitProb = 0.5;
        magicPoliceProb = 0.5;
        //decide whether portal is open or exit
        Probability prob = new Probability(exitProb);
        isExit = prob.rollBinaryDice();
        //If exit is true then the gate must also be open, otherwise rolldice to see
        //if portal is open.
        if (isExit)
        {
            isOpen = true;
        }
        else
        {
            prob.setThreshold(openProb);
            isOpen = prob.rollBinaryDice();
        }  
    }

    /**
    * Parameterised constructor for the portal object given a string of the direction. 
    *
    * @param direction            A string either: North, South, East or West.
    */
    public Portal(String direction)
    {
        this.direction = direction;
        openProb = 0.5;
        exitProb = 0.5;
        magicPoliceProb = 0.5;
        Probability prob = new Probability(exitProb);
        isExit = prob.rollBinaryDice();
        //If exit is true then the gate must also be open, otherwise rolldice to see
        //if portal is open.
        if (isExit)
        {
            isOpen = true;
        }
        else
        {
            prob.setThreshold(openProb);
            isOpen = prob.rollBinaryDice();
        }
    }

    /**
    * Parameterised constructor for the portal object given a string of the direction and a 
    * probability of being open.
    *
    * @param direction            A string either: North, South, East or West.
    * @param openProb             Double describing the probability of portal being open.
    */
    public Portal(String direction, double openProb)
    {
        this.direction = direction;
        this.openProb = openProb;
        exitProb = 0.5;
        magicPoliceProb = 0.5;
        Probability prob = new Probability(exitProb);
        isExit = prob.rollBinaryDice();
        //If exit is true then the gate must also be open, otherwise rolldice to see
        //if portal is open.
        if (isExit)
        {
            isOpen = true;
        }
        else
        {
            prob.setThreshold(openProb);
            isOpen = prob.rollBinaryDice();
        }
    }

    /**
    * Parameterised constructor for the portal object given a string of the direction, a 
    * probability of being open and a probability of the portal being an exit.
    *
    * @param direction            A string either: North, South, East or West.
    * @param openProb             Double describing the probability of portal being open.
    * @param exitProb             Double describing the probability of portal being and exit.
    */
    public Portal(String direction, double openProb, double exitProb)
    {
        this.direction = direction;
        this.openProb = openProb;
        this.exitProb = exitProb;
        magicPoliceProb = 0.5;
        Probability prob = new Probability(exitProb);
        isExit = prob.rollBinaryDice();
        //If exit is true then the gate must also be open, otherwise rolldice to see
        //if portal is open.
        if (isExit)
        {
            isOpen = true;
        }
        else
        {
            prob.setThreshold(openProb);
            isOpen = prob.rollBinaryDice();
        }
    }

    /**
    * Parameterised constructor for the portal object given a string of the direction, a 
    * probability of being open, a probability of the portal being an exit and a probability
    * that the police will be on the other side of the portal.
    *
    * @param direction            A string either: North, South, East or West.
    * @param openProb             Double describing the probability of portal being open.
    * @param exitProb             Double describing the probability of portal being an exit.
    * @param magicPoliceProb      Double describing the probability of the police being on the other side.
    */
    public Portal(String direction, double openProb, double exitProb, double magicPoliceProb)
    {
        this.direction = direction;
        this.openProb = openProb;
        this.exitProb = exitProb;
        this.magicPoliceProb = magicPoliceProb;
        Probability prob = new Probability(exitProb);
        isExit = prob.rollBinaryDice();
        //If exit is true then the gate must also be open, otherwise rolldice to see
        //if portal is open.
        if (isExit)
        {
            isOpen = true;
        }
        else
        {
            prob.setThreshold(openProb);
            isOpen = prob.rollBinaryDice();
        }
    }

    /**
    * Prints state of the portal including direction, exit probability and probability of being open.
    *
    */
    public void display()
    {
        System.out.println(" " + direction + " exit probability:" + 
        exitProb + " magic police probability" + magicPoliceProb);
    }

    /**
    * Accessor method for direction of the portal. 
    *
    * @return           Direction of portal as a string.
    */
    public String getDirection()
    {
        return direction;
    }

    /**
    * Accessor method for the probability of portal being an exit. 
    *
    * @return           probability of exit as a double.
    */
    public double getExitProb()
    {
        return exitProb;
    }

    /**
    * Accessor method for the probability of police on other side. 
    *
    * @return           probability of police as a double.
    */
    public double getMagicPoliceProb()
    {
        return magicPoliceProb;
    }

    /**
    * Accessor method for the probability of portal being open. 
    *
    * @return           probability of open as a double.
    */
    public double getOpenProb()
    {
        return openProb;
    }

    /**
    * Boolean method which indicates whether portal is an exit.
    *
    * @return       True portal is exit. 
    */
    public boolean isExit()
    {
        return isExit;
    }

    /**
    * Boolean method which indicates whether portal is open.
    *
    * @return       True portal is open. 
    */
    public boolean isOpen()
    {
        return isOpen;
    }

    /**
    * Mutator method for the direction of the portal.
    *
    * @param direction          String describing the direction.       
    */
    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    /**
    * Mutator method for the probability of exit for portal.
    *
    * @param exitProb           Double describing the probability of being an exit.       
    */
    public void setExitProb(double exitProb)
    {
        this.exitProb = exitProb;
    }

    /**
    * Mutator method for the boolean if portal is exit.
    *
    * @param isExit         True if portal is an exit   
    */
    public void setIsExit(boolean isExit)
    {
        this.isExit = isExit;
    }

    /**
    * Mutator method for the boolean if portal is open.
    *
    * @param isOpen         True if portal is open. 
    */
    public void setIsOpen(boolean isOpen)
    {
        this.isOpen = isOpen;
    }

    /**
    * Mutator method for the probability of police on otherside of portal.
    *
    * @param magicPoliceProb             Double describing the probability of police being present. 
    */
    public void setMagicPoliceProb(double magicPoliceProb)
    {
        this.magicPoliceProb = magicPoliceProb;
    }

    /**
    * Mutator method for the probability of portal being open.
    *
    * @param openProb               Double describing the probability of a portal being open.
    */
    public void setOpenProb(double openProb)
    {
        this.openProb = openProb;
    }

    /**
    * Using the increment method from the probability class, a portal can have
    * the exit probability updated if that direction was travelled through. 
    * the method creates a probability object and calls the increment function
    * which returns a double between -0.05 and 0.05. There is 50-50 chance of being
    * positive or negative increment. 
    * 
    */
    public void updateExitProb()
    {
        
        //
        Probability getIncrement = new Probability(0.5);
        double increment = getIncrement.increment(1,5);
        if ((Math.abs(increment) > exitProb) && (increment < 0))
        {
            exitProb = 0;
        }
        else
        {
            exitProb += increment;
        }
    }

    /**
    * Using the increment method from the probability class, a portal can have
    * the police probability updated if that direction was travelled through. 
    * 
    */
    public void updateMagicPoliceProb()
    {
        //create a probability object and call the increment function
        // which returns a double between -0.05 and 0.05
        //
        Probability getIncrement = new Probability(0.5);
        double increment = getIncrement.increment(1,5);
        if ((Math.abs(increment) > magicPoliceProb) && (increment < 0))
        {
            magicPoliceProb = 0;
        }
        else
        {
            magicPoliceProb += increment;
        }
    }

    /** 
    * Decides whether a portal will be open when travelling to the next room. 
    * if a portal is an exit then it will be forced open.
    *
    */
    public void updatePortal()
    {
        Probability prob = new Probability(exitProb);
        isExit = prob.rollBinaryDice();
        if (isExit)
        {
            isOpen = true;
        }
        else
        {
            prob.setThreshold(openProb);
            isOpen = prob.rollBinaryDice();
        }
    }
}


