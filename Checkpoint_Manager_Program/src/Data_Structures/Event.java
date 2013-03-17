/* File Name: Manager.java
 * Description: Event class which stores all members and functions pertaining to an event. 
 * First Created: 15/03/2013
 * Last Modified: 16/03/2013
 */
package Data_Structures;

import File_Handling.FileHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Chris Savill, chs17@aber.ac.uk
 */
public class Event {

    private ArrayList<Competitor> competitors; //Array list of competitors in an event.
    private ArrayList<Node> nodes; //Array list of nodes in an event.
    private ArrayList<Node> checkpoints; //Array list of nodes that are of type "CP" or "MC".
    private ArrayList<Course> courses; //Array list of courses in an event.
    private ArrayList<Record> records; //Array list of records logged.
    private int lastLineRead;
    private Date lastRecordedTime;

    /**
     * Method to return array list of competitors.
     */
    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }

    /**
     * Method to return array list of nodes.
     */
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Method to return array list of checkpoints.
     */
    public ArrayList<Node> getCheckpoints() {
        return checkpoints;
    }

    /**
     * Method to return array list of courses.
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Method to return array list of records.
     */
    public ArrayList<Record> getRecords() {
        return records;
    }

    /**
     * Method to get the last line read number.
     *
     * @return The line read from the times file.
     */
    public int getLastLineRead() {
        return lastLineRead;
    }

    /**
     * Method to set the last line read number.
     *
     * @param lineNumber The line read from the times file.
     */
    public void setLastLineRead(int lineNumber) {
        this.lastLineRead = lineNumber;
    }

    /**
     * Method to set the last time recorded.
     *
     * @param time The last time recorded.
     */
    public void setLastRecordedTime(Date time) {
        this.lastRecordedTime = time;
    }

    /**
     * Method to call a series of methods to load in the data required by the
     * program.
     */
    public boolean loadCycle() throws IOException {
        FileHandler fileReader = new FileHandler();

        if (fileReader.readNodes(this)) {
            if (fileReader.readCourses(this)) {
                if (fileReader.readCompetitors(this)) {
                    return true;
                } else {
                    System.out.print("Failed to load competitors. Program Exiting.\n");
                }
            } else {
                System.out.print("Failed to load courses. Program Exiting.\n");
            }
        } else {
            System.out.print("Failed to load nodes. Program Exiting.\n");
        }

        return false;
    }

    /**
     * Method that checks if the node number passed in exists in the array list
     * of nodes loaded in.
     *
     * @param number The number to be compared with.
     */
    public boolean checkNodeExists(int number) {
        for (int counter = 0; counter < nodes.size(); counter++) {
            if (number == nodes.get(counter).getNumber()) {
                return true;
            } //Nodes exists.
        }

        return false; //Returns false if the node number passed in does not exist in the array list of nodes.
    }

    /**
     * Method that checks if the course letter passed in exists in the array
     * list of courses loaded in.
     *
     * @param letter The letter to be compared with.
     */
    public boolean checkCourseExists(char letter) {
        for (int counter = 0; counter < courses.size(); counter++) {
            if (letter == courses.get(counter).getLetter()) {
                return true;
            } //Course exists.
        }

        return false; //Returns false if the course letter passed in does not exist in the array list of courses.
    }

    /**
     * Method to find a competitor and return it.
     *
     * @param competitorNumber The number of the competitor being looked for.
     * @return The competitor matched.
     */
    public Competitor retrieveCompetitor(int competitorNumber) {
        for (Competitor competitor : competitors) {
            if (competitor.getNumber() == competitorNumber) {
                return competitor;
            }
        }

        return null;
    }

    /**
     * Method to find a course and return it.
     *
     * @param courseLetter The course being looked for.
     * @return The course matched.
     */
    public Course retrieveCourse(char courseLetter) {
        for (Course course : courses) {
            if (course.getLetter() == courseLetter) {
                return course;
            }
        }

        return null;
    }

    public boolean checkNewRecord(int checkpoint, int status, int competitorNumber, Date time) {
        Competitor competitor = retrieveCompetitor(competitorNumber);

        if (time.before(lastRecordedTime)) {
            return false;
        } else {
            if (competitor.getStatus() == 'I' || competitor.getStatus() == 'E') {
                return false; //Should not be updated as competitor already excluded.
            } else if (status == 2 || status == 3) {
                if (competitor.getStatus() != 'A') {
                    return false; //Competitor cannot be departing or be exclude from a medical checkpoint they haven't arrived at.
                } else {
                    return true;
                }
            } else if (status == 0) {
                if (competitor.getStatus() != 'A') {
                    return true;
                } else {
                    return false; //Competitor cannot be at a time checkpoint when should be at a medical checkpoint being examined.
                }
            }
        }

        return false;
    }

    public char determineFinalStatus(int checkpoint, int status, int competitorNumber) {
        Competitor competitor = retrieveCompetitor(competitorNumber);

        if (checkpoint == competitor.getCheckpoints()[competitor.getCheckpointIndex() + 1]) {
            return 'I';
        } else {
            if (status == 0) {
                return 'T';
            } else if (status == 1) {
                return 'A';
            } else if (status == 2) {
                return 'D';
            } else if (status == 3) {
                return 'E';
            }
        }
        
        System.out.print("\n\nInvalid final status, returning 'I'.\n");
        return 'I';
    }

    /**
     * Constructor to initialise the event.
     */
    public Event() {
        competitors = new ArrayList<Competitor>();
        nodes = new ArrayList<Node>();
        checkpoints = new ArrayList<Node>();
        courses = new ArrayList<Course>();
        records = new ArrayList<Record>();
        lastLineRead = 0;
    }
}
