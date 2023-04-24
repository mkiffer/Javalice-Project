import java.util.Scanner;

/**
 * Class which stores information of the utility class of Input, 
 * handles all user input for the game.
 *
 * @author Michael Kiffer
 * @version ver1.0.0
 */
public class Input
{
    private Scanner input;

    /**
     * Default constructor which creates the object of the class Input.
     *
     */
    public Input()
    {
        input = new Scanner(System.in);
    }

    /**
    * Method to accept input from the user in the form of a char.
    *
    * @param output     Accepts a string which instructs the user what to input
    * @return           The double given by the user.
    */
    public double acceptDoubleInput(String output)
    {
        System.out.println("\t " + output);
        boolean flag = true;
        double value = -1;
        do
        {
            try
            {
                value = Double.parseDouble(input.nextLine().strip());
                flag = false;
            }
            catch (Exception e)
            {
                System.out.println("Please only input a number. Try again");
            }
        } while (flag);
        return value;
    }

    /**
    * Method to accept input from the user in the form of a char.
    *
    * @param output     Accepts a string which instructs the user what to input
    * @return           The integer given by the user
    */
    public int acceptIntegerInput(String output)
    {
        System.out.println("\t " + output);
        boolean flag = true;
        int value = -1;
        do
        {
            try
            {
                value = Integer.parseInt(input.nextLine().strip());
                flag = false;
            }
            catch (Exception e)
            {
                System.out.println("Please only input an integer. Try again");
            }
        } while (flag);
        return value;
    }

    /**
    * Method to accept input from the user in the form of a char.
    *
    * @param output     Accepts a string which instructs the user what to input
    * @return           The string given by the user
    */
    public String acceptStringInput(String output)
    {
        System.out.println("\t " + output);
        String value = input.nextLine().strip();
        return value;
    }

}
