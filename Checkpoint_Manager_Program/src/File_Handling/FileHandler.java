/* File Name: FileHandler.java
 * Description: FileHandler class which stores methods to handle the reading of files. 
 * First Created: 15/03/2013
 * Last Modified: 16/03/2013
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
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Chris Savill, chs17@aber.ac.uk
 */
public class FileHandler {

    /**
     * Method to retrieve file name from user.
     *
     * @param objectsToBeLoaded a string to give context.
     */
    private String getFileName(String objectsToBeLoaded) {
        boolean fileNameChosen = false;
        boolean validAcceptance = false;
        String fileName;
        String acceptance;
        Scanner input = new Scanner(System.in);

        do {
            System.out.print("Enter in the filename required to load in the " + objectsToBeLoaded + ": ");
            fileName = input.nextLine();

            do {
                System.out.print("Are you sure the filename is: " + fileName + "? \ny/n: ");
                acceptance = input.nextLine();

                if (acceptance.isEmpty()) {
                    System.out.print("Please enter in a valid option, either 'y' or 'n'.\n\n");
                    validAcceptance = false;
                } else {
                    if (acceptance.charAt(0) != 'y' && acceptance.charAt(0) != 'n') {
                        System.out.print("Please enter in a valid option, either 'y' or 'n'.\n\n");
                        validAcceptance = false;
                    } else {
                        validAcceptance = true;
                    }
                }
            } while (validAcceptance == false);

            if (acceptance.charAt(0) == 'y') {
                fileNameChosen = true;
            } else if (acceptance.charAt(0) == 'n') {
                fileNameChosen = false;
            }
        } while (fileNameChosen == false);

        return fileName;
    }

    /**
     * Method to read in all the details for the nodes pertaining to an event.
     *
     * @param event The event instance.
     */
    public boolean readNodes(Event event) throws IOException {
        String fileName = getFileName("nodes");
        String input;
        int nodeNumber;
        String nodeType;
        String[] subStrings;
        String pattern = "(\\d+\\s+([A-Z]{2})$)"; //Regular expression for nodes file.

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            while ((input = reader.readLine()) != null) {
                if (input.matches(pattern)) { //Checks to make sure the line is in the right format.
                    subStrings = input.split("\\s+"); //Gets rid of whitespace and separates the two sides into two substrings.
                    nodeNumber = Integer.parseInt(subStrings[0]); //Retrieves the node number by parsing the string into an int.
                    nodeType = subStrings[1]; //Retrieves the node type.

                    Node node = new Node(nodeNumber, nodeType); //Creates new node with parameters read in.
                    event.getNodes().add(node); //Adds new node to array list of nodes.

                    if (node.getType().equals("CP") || node.getType().equals("MC")) {
                        event.getCheckpoints().add(node); //Adds new node to array list of checkpoints if the node is of type "CP or "MC". 
                    }
                } else {
                    System.out.print("Invalid line format. Cancelling loading of nodes.\n\n");
                    reader.close();
                    return false;
                }
            }

            if (!event.getNodes().isEmpty()) {
                System.out.print("Loading in of nodes successful.\n\n");
                reader.close();
                return true;
            } else {
                System.out.print("Loading in of nodes unsuccessful. No nodes in file.\n\n");
                reader.close();
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.print("Could not open file that contains nodes.\n\n");
        return false;
    }

    /**
     * Method to read in all the details for the courses pertaining to an event.
     *
     * @param event The event instance.
     */
    public boolean readCourses(Event event) throws IOException {
        String fileName = getFileName("courses");
        String input;
        char courseLetter;
        int numberOfNodes;
        int[] nodes;
        String[] subStrings;
        String pattern = "(([A-Za-z]+)((\\s+\\d+)+)$)"; //Regular expression for courses file.

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            while ((input = reader.readLine()) != null) {
                if (input.matches(pattern)) { //Checks to make sure the line is in the right format.
                    subStrings = input.split("\\s+"); //Gets rid of whitespace and separates the strings into substrings.
                    courseLetter = subStrings[0].charAt(0); //Retrieves the course letter.
                    numberOfNodes = Integer.parseInt(subStrings[1]);
                    nodes = new int[numberOfNodes];

                    for (int counter = 0; counter < numberOfNodes; counter++) {
                        if (event.checkNodeExists(Integer.parseInt(subStrings[counter + 2]))) {
                            nodes[counter] = Integer.parseInt(subStrings[counter + 2]);
                        } else {
                            System.out.print("Invalid node in course file found. Cancelling loading of courses\n\n");
                            reader.close();
                            return false;
                        }
                    }

                    Course course = new Course(courseLetter, numberOfNodes, nodes); //Creates new course with parameters read in.
                    event.getCourses().add(course); //Adds new course to array list of courses.
                } else {
                    System.out.print("Invalid line format. Cancelling loading of courses\n\n");
                    reader.close();
                    return false;
                }
            }

            if (!event.getCourses().isEmpty()) {
                System.out.print("Loading in of courses successful.\n\n");
                reader.close();
                return true;
            } else {
                System.out.print("Loading in of courses unsuccessful. No courses in file.\n\n");
                reader.close();
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.print("Could not open file that contains courses.\n\n");
        return false;
    }

    /**
     * Method to read in all the details for the competitors pertaining to an
     * event.
     *
     * @param event The event instance.
     */
    public boolean readCompetitors(Event event) throws IOException {
        String fileName = getFileName("competitors");
        String input;
        int competitorNumber;
        char courseLetter;
        String[] subStrings;
        String competitorName;
        String pattern = "(\\d+\\s+[A-Za-z]((\\s+[A-Za-z]{1}[a-z]+)+)$)"; //Regular expression for competitors file.

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            while ((input = reader.readLine()) != null) {
                if (input.matches(pattern)) { //Checks to make sure the line is in the right format.
                    subStrings = input.split("\\s+"); //Gets rid of whitespace and separates the strings into substrings.
                    competitorNumber = Integer.parseInt(subStrings[0]); //Retrieves the competitor number by parsing the string into an int.             

                    if (event.checkCourseExists(subStrings[1].charAt(0))) {
                        courseLetter = subStrings[1].charAt(0); //Retrieves the course the competitor is entering in on.
                    } else {
                        System.out.print("Invalid course in competitor file found. Cancelling loading of competitors.\n\n");
                        reader.close();
                        return false;
                    }

                    competitorName = subStrings[2];

                    if (subStrings.length > 3) {
                        for (int counter = 3; counter < subStrings.length; counter++) {
                            competitorName += " " + subStrings[counter]; //Concatanates name substrings together.
                        }
                    }

                    Competitor competitor = new Competitor(competitorNumber, courseLetter, competitorName, event); //Creates new competitor with parameters read in.
                    event.getCompetitors().add(competitor); //Adds new competitor to array list of competitors.                
                } else {
                    System.out.print("Invalid line format. Cancelling loading of competitors.\n\n");
                    reader.close();
                    return false;
                }
            }

            if (!event.getCompetitors().isEmpty()) {
                System.out.print("Loading in of competitors successful.\n\n");
                reader.close();
                return true;
            } else {
                System.out.print("Loading in of competitors unsuccessful. No competitors in file.\n\n");
                reader.close();
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        System.out.print("Could not open file that contains competitors.\n\n");
        return false;
    }

    /**
     * Method to read in all the details for the checkpoint times pertaining to
     * an event.
     *
     * @param event The event instance.
     */
    public boolean readTimes(Event event) throws IOException, ParseException {
        String input;
        int currentLineNumber = 0;
        int lastLineNumber = event.getLastLineRead();
        char competitorStatus;
        int competitorNumber;
        int nodeNumber;
        String[] subStrings;
        String pattern = "([A-Z{1}]((\\s+\\d+){2})\\s+[0-2{1}][0-9{1}]:[0-5{1}][0-9{1}]$)"; //Regular expression for times file.
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date time;

        event.getRecords().clear(); //Empties array list.

        try {
            BufferedReader reader = new BufferedReader(new FileReader("cp_times.txt"));

            while ((input = reader.readLine()) != null) {
                currentLineNumber++;
                if (currentLineNumber > lastLineNumber) {
                    if (input.matches(pattern)) { //Checks to make sure the line is in the right format.
                        subStrings = input.split("[\\s+]"); //Gets rid of whitespace and separates the strings into substrings.
                        competitorStatus = subStrings[0].charAt(0); //Retrieves competitor status.
                        nodeNumber = Integer.parseInt(subStrings[1]); //Retrieves the node number by parsing the string into an int.
                        competitorNumber = Integer.parseInt(subStrings[2]); //Retrieves the competitor number by parsing the string into an int.
                        time = formatter.parse(subStrings[3]); //Retrieves the time being recorded and formats it into 24hour HH:MM.             

                        Record record = new Record(competitorStatus, nodeNumber, competitorNumber, time); //Creates new record with parameters read in.
                        event.getRecords().add(record); //Adds new record to array list of records.
                        event.retrieveCompetitor(competitorNumber).setStatus(competitorStatus); //Updates competitor's status.

                        if (event.retrieveCompetitor(competitorNumber).getStatus() != 'N') {
                            event.retrieveCompetitor(competitorNumber).incrementCheckpointIndex(); //Increments the competitor's checkpoint intdex by 1.
                        }

                        event.setLastLineRead(currentLineNumber);
                        event.setLastRecordedTime(time);
                    } else {
                        System.out.print("Invalid line format. Cancelling loading of times.\n\n");
                        reader.close();
                        return false;
                    }
                }
            }

            reader.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        System.out.print("Could not open file that contains times.\n\n");
        return false;
    }

    public boolean appendTimeRecord(Record record) {

        try {
            FileWriter writer = new FileWriter("cp_times.txt", true); //True sets append mode.          
            writer.write(record.getCompetitorStatus() + " " + record.getNodeNumber()
                    + " " + record.getCompetitorNumber() + " " + record.getTime().toString());
            writer.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}