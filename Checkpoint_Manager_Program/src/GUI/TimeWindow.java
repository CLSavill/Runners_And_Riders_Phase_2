/* File Name: TimeWindow.java
 * Description: TimeWindow GUI class using swing.  
 * First Created: 16/03/2013
 * Last Modified: 17/03/2013
 */
package GUI;

import Data_Structures.Event;
import Data_Structures.Record;
import File_Handling.FileHandler;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

/**
 * @author Chris Savill, chs17@aber.ac.uk
 */
public class TimeWindow extends JFrame implements ActionListener {

    private Event event;
    private FileHandler fileHandler;
    private int checkpoint;
    private String type;
    private int competitor;
    private int status;
    private JFrame timeFrame, typeFrame;
    private JPanel timePanel, bottomPanel;
    private JLabel timeLabel;
    private JButton submit;
    private Date date;
    private SpinnerDateModel spinnerModel;
    private JSpinner spinner;
    private JSpinner.DateEditor dateEditor;

    /**
     * Constructor for TimeWindow GUI class that sets up and launches the GUI.
     *
     * @param event The event instance.
     * @param checkpoint The checkpoint number.
     * @param type The checkpoint type.
     * @param competitor The competitor number.
     * @param selectionFrame The JFrame this transitioned from.
     * @param typeFrame The JFrame that is reopened after this JFrame closes.
     */
    public TimeWindow(Event event, int checkpoint, String type, int competitor, JFrame selectionFrame, JFrame typeFrame) {
        selectionFrame.dispose();

        this.typeFrame = typeFrame;
        this.event = event;
        this.checkpoint = checkpoint;
        this.type = type;
        this.competitor = competitor;
        fileHandler = new FileHandler();

        //Setup frame:
        timeFrame = new JFrame("Time Of Record");

        if (type.equals("MC")) {
            status = getMedicalOptions();
        } else {
            status = 0; //Comeptitor status not a medical related status.
        }

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

    /**
     * Method to handle actions performed.
     *
     * @param evt The event triggered.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();

        if (actionCommand.equals("Submit Checkpoint Record")) {
            try {
                if (!fileHandler.readTimes(event.getFileNames()[3], event)) {
                    JOptionPane.showMessageDialog(timeFrame, "Failed to load time records from file.");
                }
            } catch (IOException | ParseException ex) {
                Logger.getLogger(TimeWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (event.checkNewRecord(checkpoint, status, competitor, (Date) spinner.getValue())) {
                char finalStatus = event.determineFinalStatus(checkpoint, status, competitor);

                Record record = new Record(checkpoint, finalStatus, competitor, (Date) spinner.getValue());
                event.getRecords().add(record);
                event.setLastLineRead(event.getLastLineRead() + 1);
                event.setLastRecordedTime((Date) spinner.getValue());

                fileHandler.appendTimeRecord(event.getFileNames()[3], record);
                JOptionPane.showMessageDialog(timeFrame, "Time record succesfully added.");
            } else {
                JOptionPane.showMessageDialog(timeFrame, "Non-valid record. Record will not added.");
            }

            timeFrame.dispose(); //Closes frame and releases resourses.
            this.dispose(); //Releases resources.
            TypeWindow typeFrame = new TypeWindow(event);

        }
    }

    /**
     * Method to get the user to select the status of the competitor at the
     * medical checkpoint.
     *
     * @return The status of the competitor at the medical checkpoint.
     */
    public int getMedicalOptions() {
        String[] options = new String[]{"Arriving", "Departing", "Excluded"};

        int selection = JOptionPane.showOptionDialog(timeFrame, "Is the competitor being marked as 'Arriving',"
                + " 'Departing' or as 'Excluded' on medical grounds?", "Medical Marking", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (selection == 0) {
            return 1; //Competitor status to be set to arriving at medical checkpoint.
        } else if (selection == 1) {
            return 2; //Competitor status to be set to departing medical checkpoint.
        } else if (selection == 2) {
            return 3; //Competitor status to be set to excluded based on medical grounds.
        }

        return 0;
    }
}