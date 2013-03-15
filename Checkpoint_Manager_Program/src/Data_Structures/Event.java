/*
 * @author Chris Savill, chs17@aber.ac.uk * 
 * File Name: Manager.java
 * Description: File that contains Event class which stores all members and functions pertaining to an event. 
 * First Created: 15/03/2013
 * Last Modified: 15/03/2013
 */
package Data_Structures;

import File_Handling.EventLoader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Event {

    private ArrayList<Competitor> competitors; //Array list of competitors in an event.
    private ArrayList<Node> nodes; //Array list of nodes in an event.
    private ArrayList<Course> courses; //Array list of courses in an event.
    private PriorityQueue<Checkpoint> times; //Priority queue of times logged prioritised by earliest time first.

    /* Method to return array list of competitors. */
    public ArrayList<Competitor> getCompetitors() {
        return competitors;
    }
    /* Method to return array list of nodes. */

    public ArrayList<Node> getNodes() {
        return nodes;
    }
    /* Method to return array list of courses. */

    public ArrayList<Course> getCourses() {
        return courses;
    }
    /* Method to return priority queue of times. */

    public PriorityQueue<Checkpoint> getTimes() {
        return times;
    }

    public boolean loadCycle() {
        EventLoader fileReader = new EventLoader();
        
        if (fileReader.readNodes(event)) {
            if (fileReader.readCourses(event)) {
                if (fileReader.readCompetitors(event)) {
                    return true;
                } else {
                    System.out.println("Failed to load competitors. Program Exiting.\n");
                }
            } else {
                System.out.println("Failed to load courses. Program Exiting.\n");
            }
        } else {
            System.out.println("Failed to load nodes. Program Exiting.\n");
        }
        
        return false;
    }
}
