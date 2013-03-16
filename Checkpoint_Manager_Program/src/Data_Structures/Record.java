/* File Name: Record.java
 * Description: Record class which stores all members and functions pertaining to checking a competitor in at a checkpoint. 
 * First Created: 15/03/2013
 * Last Modified: 15/03/2013
 */
package Data_Structures;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Chris Savill, chs17@aber.ac.uk
 */
public class Record {

    private Event event;
    private char competitorStatus;
    private int checkpoint;
    private String nodeType;
    private int competitorNumber;
    private Date time;

    /**
     * Constructor to initialise record data when read in from file.
     */
    public Record(char status, int nodeNumber, int competitorNumber, Date time, ArrayList<Node> nodes) {
        this.competitorStatus = status;
        this.checkpoint = nodeNumber;
        this.nodeType = setNodeType(nodes);
        this.competitorNumber = competitorNumber;
        this.time = time;
    }

    /**
     * Constructor to initialise record data when recorded through GUI.
     */
    public Record(int checkpoint, String type, int competitorNumber, Date time, Event event) {
        this.status = calculateStatus(checkpoint, type, competitorNumber, event);
        this.checkpoint = checkpoint;
        this.nodeType = type;
        this.competitorNumber = competitorNumber;
        this.time = time;
    }

    /**
     * Method to return the status of the competitor as marked by the
     * checkpoint.
     */
    public char getCompetitorStatus() {
        return competitorStatus;
    }

    /**
     * Method to return the node number being recorded.
     */
    public int getNodeNumber() {
        return checkpoint;
    }

    /**
     * Method to return the type of the node being recorded.
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * Method to return the competitor number being recorded.
     */
    public int getCompetitorNumber() {
        return competitorNumber;
    }

    /**
     * Method to return the time being recorded.
     */
    public Date getTime() {
        return time;
    }

    /**
     * Method to set the type of the node being recorded.
     *
     * @param nodes The array list of nodes to be compared with.
     */
    private String setNodeType(ArrayList<Node> nodes) {
        String type = null;

        for (int counter = 0; counter < nodes.size(); counter++) {
            if (checkpoint == nodes.get(counter).getNumber()) {
                type = nodes.get(counter).getType();
            }
        }

        return type;
    }
    
    private String calculateStatus(int checkpoint, String type, int competitorNumber, Event event) {
        
    }
}
