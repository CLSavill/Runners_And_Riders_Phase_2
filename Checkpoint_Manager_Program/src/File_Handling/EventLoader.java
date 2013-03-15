/*
 * @author Chris Savill, chs17@aber.ac.uk * 
 * File Name: EventLoader.java
 * Description: File that contains EventLoader class which stores methods to handle the reading of files. 
 * First Created: 15/03/2013
 * Last Modified: 15/03/2013
 */
package File_Handling;

import Data_Structures.Competitor;
import Data_Structures.Course;
import Data_Structures.Event;
import Data_Structures.Node;
import Data_Structures.Record;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventLoader {

    /* Method to retrieve file name from user. */
    private String getFileName(String objectsToBeLoaded) {
        boolean fileNameChosen = false;
        String fileName;
        String acceptance;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Enter in the filename required to load in the " + objectsToBeLoaded + ": ");
            fileName = input.nextLine();

            do {
                System.out.println("Are you sure the filename is: " + fileName + "? \ny/n: ");
                acceptance = input.nextLine();

                if (acceptance.charAt(0) != 'y' && acceptance.charAt(0) != 'n') {
                    System.out.println("Please enter in a valid option, either 'y' or 'n'.\n\n");
                }
            } while (acceptance.charAt(0) != 'y' && acceptance.charAt(0) != 'n');

            if (acceptance.charAt(0) == 'y') {
                fileNameChosen = true;
            } else if (acceptance.charAt(0) == 'n') {
                fileNameChosen = false;
            }
        } while (fileNameChosen == false);

        return fileName;
    }

    /* Method to read in all the details for the nodes pertaining to an event. */
    public boolean readNodes(Event event) throws IOException {
        String fileName = getFileName("nodes");
        String input;
        int nodeNumber;
        String nodeType;
        String[] subStrings;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            while ((input = reader.readLine()) != null) {
                if (input.matches("[\\d+][\\s+][A-Z{2}]")) { //Checks to make sure the line is in the right format.
                    subStrings = input.split("[\\s+]"); //Gets rid of whitespace and separates the two sides into two substrings.
                    nodeNumber = Integer.parseInt(subStrings[0]); //Retrieves the node number by parsing the string into an int.
                    nodeType = subStrings[1]; //Retrieves the node type.

                    Node node = new Node(nodeNumber, nodeType); //Creates new node with parameters read in.
                    event.getNodes().add(node); //Adds new node to array list of nodes.
                } else {
                    System.out.println("Invalid line format. Cancelling loading of nodes.\n\n");
                    return false;
                }
            }

            if (!event.getNodes().isEmpty()) {
                System.out.println("Loading in of nodes successful.\n\n");
                return true;
            } else {
                System.out.println("Loading in of nodes unsuccessful. No nodes in file.\n\n");
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EventLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Could not open file that contains nodes.\n\n");
        return false;
    }

    /* Method to read in all the details for the courses pertaining to an event. */
    public boolean readCourses(Event event) throws IOException {
        String fileName = getFileName("courses");
        String input;
        char courseLetter;
        int numberOfNodes;
        int[] nodes;
        String[] subStrings;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            while ((input = reader.readLine()) != null) {
                if (input.matches("[a-zA-Z][+[[\\s+][\\d+]]]")) { //Checks to make sure the line is in the right format.
                    subStrings = input.split("[\\s+]"); //Gets rid of whitespace and separates the strings into substrings.
                    courseLetter = subStrings[0].charAt(0); //Retrieves the course letter.
                    numberOfNodes = Integer.parseInt(subStrings[1]);
                    nodes = new int[numberOfNodes];

                    for (int counter = 1; counter < numberOfNodes; counter++) {
                        if (event.checkNodeExists(Integer.parseInt(subStrings[counter]))) {
                            nodes[counter] = Integer.parseInt(subStrings[counter]);
                        } else {
                            System.out.println("Invalid node in course file found. Cancelling loading of courses\n\n");
                            return false;
                        }
                    }

                    Course course = new Course(courseLetter, numberOfNodes, nodes); //Creates new course with parameters read in.
                    event.getCourses().add(course); //Adds new course to array list of courses.
                } else {
                    System.out.println("Invalid line format. Cancelling loading of courses\n\n");
                    return false;
                }
            }

            if (!event.getCourses().isEmpty()) {
                System.out.println("Loading in of courses successful.\n\n");
                return true;
            } else {
                System.out.println("Loading in of courses unsuccessful. No courses in file.\n\n");
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EventLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Could not open file that contains courses.\n\n");
        return false;
    }

    /* Method to read in all the details for the competitors pertaining to an event. */
    public boolean readCompetitors(Event event) throws IOException {
        String fileName = getFileName("competitors");
        String input;
        int competitorNumber;
        char courseLetter;
        String[] subStrings;
        String competitorName;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            while ((input = reader.readLine()) != null) {
                if (input.matches("[\\d+][+[[\\s+][A-Z][a-z+]]]")) { //Checks to make sure the line is in the right format.
                    subStrings = input.split("[\\s+]"); //Gets rid of whitespace and separates the strings into substrings.
                    competitorNumber = Integer.parseInt(subStrings[0]); //Retrieves the competitor number by parsing the string into an int.
                    courseLetter = subStrings[1].charAt(0); //Retrieves the course the competitor is entering in on.             

                    if (event.checkCourseExists(courseLetter)) {
                        competitorName = subStrings[1];
                    } else {
                        System.out.println("Invalid course in competitor file found. Cancelling loading of competitors.\n\n");
                        return false;
                    }

                    for (int counter = 2; counter < subStrings.length - 2; counter++) {
                        competitorName += " " + subStrings[counter]; //Concatanates name substrings together.
                    }

                    Competitor competitor = new Competitor(competitorNumber, courseLetter, competitorName); //Creates new competitor with parameters read in.
                    event.getCompetitors().add(competitor); //Adds new competitor to array list of competitors.                
                } else {
                    System.out.println("Invalid line format. Cancelling loading of competitors.\n\n");
                    return false;
                }
            }

            if (!event.getCompetitors().isEmpty()) {
                System.out.println("Loading in of competitors successful.\n\n");
                return true;
            } else {
                System.out.println("Loading in of competitors unsuccessful. No competitors in file.\n\n");
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EventLoader.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Could not open file that contains competitors.\n\n");
        return false;
    }

    /* Method to read in all the details for the checkpoint times pertaining to an event. */
    public boolean readTimes(Event event) throws IOException, ParseException {
        String fileName = getFileName("times");
        String input;
        char competitorStatus;
        int competitorNumber;
        int nodeNumber;
        String[] subStrings;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            while ((input = reader.readLine()) != null) {
                if (input.matches("[A-Z][\\s+][0-9+][\\s+][\\d{2}:\\d{2}]")) { //Checks to make sure the line is in the right format.
                    subStrings = input.split("[\\s+]"); //Gets rid of whitespace and separates the strings into substrings.
                    competitorStatus = subStrings[0].charAt(0); //Retrieves competitor status.
                    nodeNumber = Integer.parseInt(subStrings[1]); //Retrieves the node number by parsing the string into an int.
                    competitorNumber = Integer.parseInt(subStrings[2]); //Retrieves the competitor number by parsing the string into an int.
                    Date time = formatter.parse(subStrings[3]); //Retrieves the time being recorded and formats it into 24hour HH:MM.             

                    Record record = new Record(competitorStatus, nodeNumber, competitorNumber, time, event.getNodes()); //Creates new record with parameters read in.
                    event.getRecords().add(record); //Adds new record to array list of competitors.                
                } else {
                    System.out.println("Invalid line format. Cancelling loading of competitors.\n\n");
                    return false;
                }
            }

            if (!event.getCompetitors().isEmpty()) {
                System.out.println("Loading in of competitors successful.\n\n");
                return true;
            } else {
                System.out.println("Loading in of competitors unsuccessful. No competitors in file.\n\n");
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EventLoader.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Could not open file that contains competitors.\n\n");
        return false;
    }
}
