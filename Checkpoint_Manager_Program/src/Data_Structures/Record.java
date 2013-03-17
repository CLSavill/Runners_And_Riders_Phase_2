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
    private int competitorNumber;
    private Date time;

    /**
     * Constructor to initialise record data when read in from file.
     */
    public Record(char status, int nodeNumber, int competitorNumber, Date time) {
        this.competitorStatus = status;
        this.checkpoint = nodeNumber;
        this.competitorNumber = competitorNumber;
        this.time = time;
    }

    /**
     * Constructor to initialise record data when recorded through GUI.
     */
    public Record(int checkpoint, char status, int competitorNumber, Date time) {
        this.competitorStatus = status;
        this.checkpoint = checkpoint;
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

    private char calculateStatus(int checkpoint, String type, int competitorNumber, Event event) {
        if (checkOnCourse(competitorNumber, checkpoint, event)) {
            return 'T';
        } else {
            return 'I';
        }
    }

    private boolean checkOnCourse(int competitorNumber, int checkpoint, Event event) {
        Competitor competitor = event.retrieveCompetitor(competitorNumber);

        for (int counter = 0; counter < event.retrieveCourse(competitor.getCourse()).getNodes().length; counter++) {
            if (checkpoint != event.retrieveCourse(competitor.getCourse()).getNodes()[counter]) {
                return false;
            }
        }

        return true; //Competitor is on the correct course.
    }

    private boolean checkIfAtMedical(int competitorNumber, int checkpoint, Event event) {
        Competitor competitor = event.retrieveCompetitor(competitorNumber);

        for (int counter = 0; counter < event.retrieveCourse(competitor.getCourse()).getNodes().length; counter++) {
            if (checkpoint != event.retrieveCourse(competitor.getCourse()).getNodes()[counter]) {
                return false;
            }
        }

        return true; //Competitor is on the correct course.
    }
}
