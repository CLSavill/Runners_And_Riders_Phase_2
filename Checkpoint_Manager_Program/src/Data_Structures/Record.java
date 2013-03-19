/* File Name: Record.java
 * Description: Record class which stores all members and functions pertaining to checking a competitor in at a checkpoint. 
 * First Created: 15/03/2013
 * Last Modified: 17/03/2013
 */
package Data_Structures;

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
     *
     * @param checkpoint The number of the checkpoint.
     * @param competitorNumber The number of the competitor.
     * @param time The time of the record.
     */
    public Record(char status, int checkpoint, int competitorNumber, Date time) {
        this.competitorStatus = status;
        this.checkpoint = checkpoint;
        this.competitorNumber = competitorNumber;
        this.time = time;
    }

    /**
     * Constructor to initialise record data when recorded through GUI.
     *
     * @param checkpoint The number of the checkpoint.
     * @param competitorNumber The number of the competitor.
     * @param time The time of the record.
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
     *
     * @return The status of the competitor.
     */
    public char getCompetitorStatus() {
        return competitorStatus;
    }

    /**
     * Method to return the checkpoint number being recorded.
     *
     * @return The checkpoint number.
     */
    public int getCheckpointNumber() {
        return checkpoint;
    }

    /**
     * Method to return the competitor number being recorded.
     *
     * @return The competitor number.
     */
    public int getCompetitorNumber() {
        return competitorNumber;
    }

    /**
     * Method to return the time being recorded.
     *
     * @return The time of the record.
     */
    public Date getTime() {
        return time;
    }
}
