/**
 * Class which stores information about the inventory for the player.
 * This object will be used by the player to store item objects.  
 *
 * @author Michael Kiffer
 * @version ver1.0.0
 */
public class Inventory
{
    private Item[] itemList;
    private int invSize;

    /**
     * Default constructor which creates the object of the class Inventory.
     * Initialises the item array with a default value of 3 and initialises 
     * each item in the array with a default "Empty" item.
     *
     */
    public Inventory()
    {
        itemList = new Item[3];
        invSize = 3;
        for (int i = 0; i < invSize; i++)
        {
            itemList[i] = new Item();
        }
    }

    /**
     * Non-Default constructor which creates the object of the class Inventory.
     *
     * @param itemList          Accepts an array of Item objects.
     */
    public Inventory(Item[] itemList)
    {
        this.itemList = itemList;
        invSize = itemList.length;
    }

    /**
     * Non-Default constructor which creates the object of the class Inventory.
     *
     * @param invSize  Accepts an integer specifying the size of the inventory.
     */
    public Inventory(int invSize)
    {
        itemList = new Item[3];
        this.invSize = invSize;
        for (int i = 0; i < invSize; i++)
        {
            itemList[i] = new Item();
        }
    }

    /**
     * Non-Default constructor which creates the object of the class Inventory.
     *
     * @param invSize      An integer specifying the size of the inventory.
     * @param itemList     An array of Item objects. Same length as invSize.
     */
    public Inventory(Item[] itemList, int invSize)
    {
        this.itemList = itemList;
        this.invSize = invSize;
    }

    /**
     * Display method to print the items in the Inventory.
     *
     */
    public void display()
    {
        System.out.print("\t Inventory:");
        System.out.print(" 1." + itemList[0].getType());
        System.out.print(" 2." + itemList[1].getType());
        System.out.println(" 3." + itemList[2].getType());
    }

    /**
    * Accessor method for item within array given its index. ensures 
    * index is within range.
    *
    * @param index        takes the index of the item to be accessed.
    * @return             item at index.
    */
    public Item getItem(int index)
    { 
        index = index-1;
        if ((index >= 0) && (index < invSize))
        {
            return itemList[index];
        }
        else
        {
            System.out.println("Please enter a number between 1 and 3");
            return null;
        }
    }

    /**
    * Accessor method for itemList.
    *
    * @return        Array of items.
    */
    public Item[] getItemArray()
    {
        return itemList;
    }

    /**
    * Method to decide whether the inventory contains as least one item.
    *
    * @return           true if inventory contains at least one item.
    */
    public boolean hasItem()
    {
        boolean output = false;
        for (Item item: itemList)
        {
            boolean itemIsEmpty = item.getType().equals("Empty");
            if (!itemIsEmpty) //checks if the item is NOT Empty
            {
                output = true;
                break;
            }
        }
        return output;
    }

    /**
    * Method to decide whether the inventory contains as least one 
    * empty space.
    *
    * @return     True if inventory contains at least one empty space.
    */
    public boolean hasSpace()
    {
        boolean output = false;
        for (Item item: itemList)
        {
            boolean itemIsEmpty = item.getType().equals("Empty");
            if (itemIsEmpty)
            {
                output = true;
                break;
            }
        }
        return output;
    }

    /**
    * Mutator method to change the state of one item within the inventory.
    *
    * @param index           accepts the index of the item to updated.
    * @param item            accepts the item to be replaced in the inventory.
    */
    public void setItem(int index, Item item)
    {
        if ((index >= 0) && (index < invSize))
        {
            itemList[index] = item;   
        }
        else
        {
            System.out.println("\t There are only 3 spots in your inventory." 
            + " Please enter a number between 1 and 3");   
        }
    }
}
