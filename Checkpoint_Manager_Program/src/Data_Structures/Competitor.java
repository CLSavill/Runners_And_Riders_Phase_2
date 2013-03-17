/* File Name: Competitor.java
 * Description: Competitor class which stores all members and functions pertaining to a competitor. 
 * First Created: 15/03/2013
 * Last Modified: 15/03/2013
 */
package Data_Structures;

import java.util.ArrayList;

/**
 * @author Chris Savill, chs17@aber.ac.uk
 */
public class Competitor {

    private String name;
    private int number;
    private char course;
    private char status;
    private int[] checkpoints;
    private int checkpointIndex; //Real index is the value - 1 as a non started would have a value of 0 but 0 is the first index.

    /**
     * Constructor to initialise competitor.
     *
     * @param number The competitor's number.
     * @param course The competitor's course.
     * @param name The competitor's name.
     */
    public Competitor(int number, char course, String name, Event event) {
        this.number = number;
        this.course = course;
        this.name = name;
        this.checkpoints = getCheckpoints(event);
        this.checkpointIndex = 0;
    }

    /**
     * Method to return the competitor's number.
     *
     * @return The number of the competitor.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Method to return the course the competitor is entered on.
     *
     * @return The course the competitor entered in on.
     */
    public char getCourse() {
        return course;
    }

    /**
     * Method to return the competitor's name.
     *
     * @return The name of the competitor.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to return the competitor's status.
     *
     * @return The status of the competitor.
     */
    public char getStatus() {
        return status;
    }

    /**
     * Method to return the index of the last checkpoint the competitor arrived at.
     *
     * @return The index of the last checkpoint the competitor arrived at.
     */
    public int getCheckpointIndex() {
        return checkpointIndex;
    }

    private int[] getCheckpoints(Event event) {
        ArrayList<Integer> checkpointsList = new ArrayList<Integer>();

        for (Node node : event.getNodes()) {
            for (int counter = 0; counter < event.retrieveCourse(course).getNumberOfNodes(); counter++) {
                if (node.getType().equals("JN") && node.getNumber() == event.retrieveCourse(course).getNodes()[counter]) {
                    checkpointsList.add(node.getNumber());
                }
            }

        }
        
        int[] intList = new int[checkpointsList.size()];
        
        for (int counter = 0; counter < checkpointsList.size(); counter++) {
            intList[counter] = checkpointsList.get(counter).intValue();
        }
        
        return intList;
    }

    /**
     * Method to set the status of the competitor.
     *
     * @param status The current status of the competitor.
     */
    public void setStatus(char status) {
        this.status = status;
    }

    /**
     * Method to set the last checkpoint the competitor arrived at.
     *
     * @param checkpoint The last checkpoint the competitor arrived at.
     */
    public void setCheckpointIndex(int checkpoint) {
        this.checkpointIndex = checkpoint;
    }
    
    /**
     * Method to increment the checkpoint index by 1.
     */
    public void incrementCheckpointIndex() {
       checkpointIndex++;
    }
}
