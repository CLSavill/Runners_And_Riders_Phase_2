package Data_Structures;
/* File Name: Launcher.java
 * Description: Launcher class which handles the initial launching of the Checkpoint Manager Program.
 * First Created: 15/03/2013
 * Last Modified: 15/03/2013
 */

import GUI.SelectionWindow;
import java.io.IOException;

/**
 * @author Chris Savill, chs17@aber.ac.uk
 */
public class Launcher {

    /**
     * Main method that just calls the loadCycle method.
     */
    public static void main(String[] args) throws IOException {
        Event event = new Event();

        if (event.loadCycle()) {
            SelectionWindow nodeWindow = new SelectionWindow(event);
        } else {
            System.out.print("Exiting Program...\n");
        }
    }
}
