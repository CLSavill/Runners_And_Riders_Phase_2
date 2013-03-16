/*
 * @author Chris Savill, chs17@aber.ac.uk * 
 * File Name: Manager.java
 * Description: File that contains Event class which stores all members and functions pertaining to an event. 
 * First Created: 15/03/2013
 * Last Modified: 15/03/2013
 */
package Data_Structures;

import File_Handling.EventLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Event {

    private ArrayList<Competitor> competitors; //Array list of competitors in an event.
    private ArrayList<Node> nodes; //Array list of nodes in an event.
    private ArrayList<Course> courses; //Array list of courses in an event.
    private PriorityQueue<Record> records; //Priority queue of records logged prioritised by earliest time first.

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
    
    /* Method to return priority queue of records. */
    public PriorityQueue<Record> getRecords() {
        return records;
    }

    /* Method to call a series of methods to load in the data required by the program. */
    public boolean loadCycle() throws IOException {
        EventLoader fileReader = new EventLoader();

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

    /* Method that checks if the node number passed in exists in the array list of nodes loaded in. 
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

    /* Method that checks if the course letter passed in exists in the array list of courses loaded in.
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
    
    public Event() {
        competitors = new ArrayList<Competitor>();
        nodes = new ArrayList<Node>();
        courses = new ArrayList<Course>();
    }
    
    public void printData() {
        System.out.print("Nodes: \n");
        
        for (int counter = 0; counter < nodes.size(); counter++) {
            System.out.print(nodes.get(counter).getNumber() + " " + nodes.get(counter).getType() + "\n");
        }
        
        System.out.print("\nCourses: ");
        
        for (int counter = 0; counter < courses.size(); counter++) {
            System.out.print("\n" + courses.get(counter).getLetter() + " " +
                    courses.get(counter).getNumberOfNodes());
            
            for (int counter2 = 0; counter2 < courses.get(counter).getNodes().length; counter2++) {
                System.out.print(" " + courses.get(counter).getNodes()[counter2]);
            }
        }
        
        System.out.print("\n\nCompetitors: ");
        
        for (int counter = 0; counter < competitors.size(); counter++) {
            System.out.print("\n" + competitors.get(counter).getNumber() + " " +
                    competitors.get(counter).getCourse() + " " + competitors.get(counter).getName());
        }
        
        System.out.print("\n\nData printing finished.");
    }
}
