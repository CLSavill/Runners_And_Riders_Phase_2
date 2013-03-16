package Data_Structures;

/**
 * @author Chris Savill, chs17@aber.ac.uk * 
 * File Name: Couse.java
 * Description: Course class which stores all members and functions pertaining to a course. 
 * First Created: 15/03/2013
 * Last Modified: 15/03/2013
 */
public class Course {

    private char letter;
    private int numberOfNodes;
    private int[] nodes;

    /** Constructor to initialise course.
     * @param letter The course letter identifier.
     * @param numberOfNodes The number of nodes the course contains.
     * @param nodes The array of nodes the course contains.
     */
    public Course(char letter, int numberOfNodes, int[] nodes) {
        this.letter = letter;
        this.numberOfNodes = numberOfNodes;
        this.nodes = nodes;
    }

    /** Method to return the course letter. */
    public char getLetter() {
        return letter;
    }
    
    /** Method to return the number of nodes the course contains. */
    public int getNumberOfNodes() {
        return numberOfNodes;
    }
    
    /** Method to return the array of nodes the course contains. */
    public int[] getNodes() {
        return nodes;
    }
}
