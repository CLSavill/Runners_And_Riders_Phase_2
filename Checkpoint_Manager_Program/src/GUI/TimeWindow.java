/* File Name: TimeWindow.java
 * Description: TimeWindow GUI class using swing.  
 * First Created: 16/03/2013
 * Last Modified: 16/03/2013
 */
package GUI;

import Data_Structures.Event;
import Data_Structures.Record;
import File_Handling.EventLoader;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

/**
 * @author Chris Savill, chs17@aber.ac.uk
 */
public class TimeWindow extends JFrame implements ActionListener {

    private Event event;
    private EventLoader loader;
    private int checkpoint;
    private String type;
    private int competitor;
    private JFrame timeFrame, selectionFrame;
    private JPanel timePanel, bottomPanel;
    private JLabel timeLabel;
    private JButton submit;
    private Date date;
    private SpinnerDateModel spinnerModel;
    private JSpinner spinner;
    private JSpinner.DateEditor dateEditor;

    public TimeWindow(Event event, int checkpoint, String type, int competitor, JFrame selectionFrame) {
        this.event = event;
        this.checkpoint = checkpoint;
        this.type = type;
        this.competitor = competitor;
        this.selectionFrame = selectionFrame;

        //Setup frame:
        timeFrame = new JFrame("Checkpoint Time");
        timeFrame.setLocation(400, 200);
        timeFrame.setLayout(new BorderLayout());
        timeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sets the default close operation
        timeFrame.setIconImage(new ImageIcon("horse.jpg").getImage()); //Loads an image and sets it as the frame icon
        //////////////////////////////////////////////////////////////

        //Setup panels:
        timePanel = new JPanel(new BorderLayout()); //Creates new JPanel.
        timePanel.setBorder(new EmptyBorder(10, 25, 10, 25));  //Sets an invisible border to simulate a padding effect
        timeFrame.add(timePanel, BorderLayout.WEST); //Adds panel to frame and places it in WEST container.
        bottomPanel = new JPanel();
        timeFrame.add(bottomPanel, BorderLayout.SOUTH); //Adds panel to frame and places it in SOUTH container.
        //////////////////////////////////////////////////////////////

        //Setup checkpoint panel components:
        timeLabel = new JLabel("Select Time Below: ");
        timePanel.add(timeLabel, BorderLayout.NORTH);

        date = new Date();
        spinnerModel = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        spinner = new JSpinner(spinnerModel);
        dateEditor = new JSpinner.DateEditor(spinner, "HH:mm"); //24-hour format.
        spinner.setEditor(dateEditor);
        timePanel.add(spinner, BorderLayout.CENTER);
        //////////////////////////////////////////////////////////////

        //Setup bottom panel components:
        submit = new JButton("Submit Checkpoint Record");
        submit.setPreferredSize(new Dimension(225, 30));
        bottomPanel.add(submit);
        submit.addActionListener(this);
        //////////////////////////////////////////////////////////////

        //Finialise frame setup:
        timeFrame.pack();
        timeFrame.setVisible(true); //Makes the frame visible
        //////////////////////////////////////////////////////////////
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();

        if (actionCommand.equals("Submit Checkpoint Record")) {
            timeFrame.dispose(); //Closes frame and releases resourses.
            this.dispose(); //Releases resources.

            try {
                loader.readTimes(event);
            } catch (IOException | ParseException ex) {
                Logger.getLogger(TimeWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

            Record record = new Record(checkpoint, type, competitor, time);

            selectionFrame.setVisible(true);
        }
    }
}