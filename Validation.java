/**
* Class that handles the validation of user input
*
* @author Michael Kiffer
* @version v1.0.0
*/
public class Validation
{
    /**
    * default constructor for the validation object
    *
    */
    public Validation()
    {

    }
    /**
    * Checks whether input is blank or only contains whitespace
    *
    * @param input          String to check if is blank
    * @return               True if string is blank or only whitespace
    */
    public boolean isBlank(String input)
    {
        if (input.strip().length() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }  
    }
    
    /**
    * Checks whether input is within a specified range
    *
    * @param input       String to check if is within range
    * @param lo          Minimum length of string
    * @param hi          Maximum length of string
    * @return            True if string is within range
    */
    public boolean lengthWithinRange(String input, int lo, int hi)
    {
        if ((input.strip().length() < lo) || (input.strip().length() > hi))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
    * Checks whether single character input is contained within a 
    * string containing single character choices. Is not case sensitive.
    *
    * @param input       input from user
    * @param choices     String containing possible choices
    * @return            True if input is in choices
    */
    public boolean validChoice(String input, String choices)
    {
        boolean flag = false;
        String lowerCaseChoices = choices.toLowerCase();
        String totalChoices = choices + lowerCaseChoices;
        String stripped = input.strip();
        if (stripped.length() == 1)
        {
            if (totalChoices.contains(stripped))
                flag = true;
        }
        return flag;
    }
}
