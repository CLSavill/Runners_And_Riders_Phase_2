/*
 * @author Chris Savill, chs17@aber.ac.uk * 
 * File Name: Launcher.java
 * Description: File that contains Launcher class which handles the intitial launching of the Checkpoint Manager Program.
 * First Created: 15/03/2013
 * Last Modified: 15/03/2013
 */

package Data_Structures;

public class Launcher {
    
    /* Main method that just calls the loadCycle method. */
    public static void main(String[] args) {
        Event event = new Event();
        
        if (event.loadCycle()) loadGUI();
    }
}
