/* File Name: Node.java
 * Description: Node class which stores all members and functions pertaining to a node. 
 * First Created: 15/03/2013
 * Last Modified: 15/03/2013
 */
package Data_Structures;

/**
 * @author Chris Savill, chs17@aber.ac.uk
 */
public class Node {

    private int number;
    private String type;

    /**
     * Constructor to initialise Node.
     *
     * @param number The number of the node.
     * @param type The type of the node.
     */
    public Node(int number, String type) {
        this.number = number;
        this.type = type;
    }

    /**
     * Method to return the node's number.
     *
     * @return The node number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Method to return the node's type.
     * @return The type of the node.
     */
    public String getType() {
        return type;
    }
}
