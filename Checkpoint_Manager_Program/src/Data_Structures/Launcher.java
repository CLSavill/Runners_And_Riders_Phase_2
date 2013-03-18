/* File Name: Launcher.java
 * Description: Launcher class which handles the initial launching of the Checkpoint Manager Program.
 * First Created: 15/03/2013
 * Last Modified: 18/03/2013
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
     * Main method that just calls the loadCycle method.
     */
    public static void main(String[] args) throws IOException {
        Event event = new Event();

        if (event.loadCycle()) {
            JOptionPane.showMessageDialog(null, "Data files loaded successfully.");
            TypeWindow typeWindow = new TypeWindow(event);
        } else {
            System.out.print("Exiting Program...\n");
        }
    }
}
