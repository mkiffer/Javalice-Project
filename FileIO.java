import java.io.*;
import java.util.Scanner;

/**
* Utility class which handles file writing and reading.
*
* @author Michael Kiffer
* @version ver1.0.0
*/

public class FileIO
{
    private String fileName;

    /**
    * Default constructor which set the filename string to a default value.
    *
    */
    public FileIO()
    {
        fileName = "No file specified";
    }

    /**
    * parameterised constructor which will set the filename as specified.
    *
    * @param fileName       Accepts path to file that needs to be read.       
    */

    public FileIO(String fileName)
    {
        this.fileName = fileName;
    }

    /**
    * Method that wil read the contents of a file line by line and create a 
    * string with the same formatting. This method handles exceptions and 
    * will output a blank string if it cannot find the file specified in 
    * fileName.  
    *
    * @return           String containing contents of file.
    */

    public String readFile()
    {
        String output = "";
        try
        {
            FileReader file = new FileReader(fileName);
            try
            {
                Scanner fileInput = new Scanner(file);
                while (fileInput.hasNextLine())
                {
                    output += fileInput.nextLine() + "\n";
                }
                
            }   
            finally
            {
                file.close();
            }    
            
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not find the file " + e);
        } 
        catch (IOException e)
        {
            System.out.println("There was an issue reading the file " + e);
        }
        catch (Exception e)
        {
            System.out.println("There was an issue with file reader " + e);
        } 
        return output;
    }

    /**
    * Mutator method which allows the input file to be changed.
    *
    * @param fileName       Accepts path to file that needs to be read.       
    */

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    /**
    * Method that will write the contents of a string line by line and create a 
    * file with the same formatting. This method will ask for the name of the 
    * output file from the user.  
    *
    * @param contents           String of contents of the file to be written.
    * @param outputFileName     String of name of file to be written. 
    * No extension required.  
    */
    public void writeFile(String contents, String outputFileName)
    {
        boolean flag = true;
        Scanner input = new Scanner(contents);
        outputFileName += ".txt";
        try
        {
            PrintWriter writer = new PrintWriter(outputFileName);
            try
            {
                while(input.hasNextLine())
                {
                    writer.println(input.nextLine());
                }  
            }
            catch(Exception e)
            {
                System.out.println("Error in writing to file! " + e);
            }
            finally
            {
                writer.close();
            }       
        }
        catch (Exception e)
        {
            System.out.println("Error during write file method " + e);
        }    
    } 
}


