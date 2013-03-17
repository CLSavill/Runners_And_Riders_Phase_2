/* File Name: TypeWindow.java
 * Description: TypeWindow GUI class using swing.
 * First Created: 17/03/2013
 * Last Modified: 17/03/2013
 */
package GUI;

import Data_Structures.Competitor;
import Data_Structures.Event;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author Chris Savill, chs17@aber.ac.uk
 */
public class TypeWindow extends JFrame implements ActionListener {
    
    private Event event;
    private boolean medicalSelected;
    private JFrame typeFrame;
    private JPanel typePanel, bottomPanel;
    private JLabel typeLabel;
    private JRadioButton time, medical;
    private ButtonGroup typeGroup;
    private JButton next;
    
    public TypeWindow(Event event) {
        this.event = event;
        medicalSelected = false;

        //Setup frame:
        typeFrame = new JFrame("Checkpoint Type Selection");
        JOptionPane.showMessageDialog(typeFrame, "Data files loaded successfully.");
        typeFrame.setLocation(400, 200);
        typeFrame.setLayout(new BorderLayout());
        typeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sets the default close operation
        typeFrame.setIconImage(new ImageIcon("horse.jpg").getImage()); //Loads an image and sets it as the frame icon
        //////////////////////////////////////////////////////////////

        //Setup panels:
        typePanel = new JPanel(new BorderLayout()); //Creates new JPanel.
        typePanel.setBorder(new EmptyBorder(10, 25, 10, 25));  //Sets an invisible border to simulate a padding effect
        typeFrame.add(typePanel, BorderLayout.NORTH); //Adds panel to frame and places it in NORTH container.
        bottomPanel = new JPanel();
        typeFrame.add(bottomPanel, BorderLayout.SOUTH); //Adds panel to frame and places it in SOUTH container.
        //////////////////////////////////////////////////////////////

        //Setup checkpoint panel components:
        typeLabel = new JLabel("Select Checkpoint Type Below: ");
        typePanel.add(typeLabel, BorderLayout.NORTH);
        
        time = new JRadioButton("Time Checkpoint");
        time.setActionCommand("time");
        time.addActionListener(this);
        time.setSelected(true); //Defaults this button to be selected. 
        medical = new JRadioButton("Medical Checkpoint");
        medical.setActionCommand("medical");
        medical.addActionListener(this);
        medical.setSelected(false);

        typeGroup = new ButtonGroup(); //Creates a group for the radio buttons to prevent both from being selected.
        typeGroup.add(time);
        typeGroup.add(medical);
        //////////////////////////////////////////////////////////////

        //Setup bottom panel components:
        next = new JButton("Next");
        next.setPreferredSize(new Dimension(100, 50));
        bottomPanel.add(next);
        next.addActionListener(this);
        //////////////////////////////////////////////////////////////

        //Finialise frame setup:
        typeFrame.pack();
        typeFrame.setVisible(true); //Makes the frame visible
        //////////////////////////////////////////////////////////////
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();
        
        switch (actionCommand) {
            case "Next":
                if (medicalSelected == true) {
                    typeFrame.setVisible(false);
                    SelectionWindow selectionWindow = new SelectionWindow(event, "MC", typeFrame);
                    this.dispose();
                } else {
                    typeFrame.setVisible(false);
                    SelectionWindow selectionWindow = new SelectionWindow(event, "CP", typeFrame);
                    this.dispose();
                }
                break;
            case "time":
                medicalSelected = false;
                break;
            case "medical":
                medicalSelected = true;
                break;
        }
    }
}
