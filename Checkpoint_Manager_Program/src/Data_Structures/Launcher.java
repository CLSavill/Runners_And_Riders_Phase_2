/* File Name: Launcher.java
 * Description: Launcher class which handles the initial launching of the Checkpoint Manager Program.
 * First Created: 15/03/2013
 * Last Modified: 19/03/2013
 */
package Data_Structures;

import GUI.TypeWindow;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * @author Chris Savill, chs17@aber.ac.uk
 */
public class Launcher {

    /**
     * Main method that checks that the right number of arguments were received
     * and calls methods to load the file required and launch the GUI.
     * 
     * @param args String array of arguments, should be a list of file names.
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 5) {
            JOptionPane.showMessageDialog(null, "Invalid number of file names supplied required for program to run.\n\n"
                    + "File names required for:\nFile containing nodes\nFile containing courses\nFile containing entrants\n"
                    + "File to retrieve time records and write time records to\nFile to write logs to.\n\n"
                    + "Now exiting program.");
        } else {
            Event event = new Event(args);

            if (event.loadCycle(args)) {
                JOptionPane.showMessageDialog(null, "Data files loaded successfully.");
                TypeWindow typeWindow = new TypeWindow(event);
            } else {
                System.out.print("Exiting Program...\n");
            }
        }
    }
}
