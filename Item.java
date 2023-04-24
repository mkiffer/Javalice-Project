 /**
 * Class which handles the item class. 
 *
 * @author Michael Kiffer
 * @version ver1.0.0
 */
public class Item
{
    private String type;

    /**
    * Default constructor for the item class. Will set the item to empty type.
    *
    */
    public Item()
    {
        type = "Empty";
    }

    /**
    * Parameterised constructor for the item class. Will set the item the type 
    * provided.
    *
    * @param type           Accepts string which will represent the item type.
    */
    public Item(String type)
    {
        this.type = type;
    }

    /**
    *Display method will print the type of the item.
    *
    */
    public void display()
    {
        System.out.println(type);
    }

    /**
    * Accessor method will return the type of the item.
    * 
    * @return       A string object which will represent the type of the item.
    */
    public String getType()
    {
        return type;
    }

    /**
    * Mutator method will change the type of the item.
    * 
    * @param type    A string object which will represent the type of the item.
    */
    public void setType(String type)
    {
        this.type = type;
    }
}
