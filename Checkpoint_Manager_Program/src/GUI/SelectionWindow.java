/* File Name: SelectionWindow.java
 * Description: SelectionWindow GUI class using swing.
 * First Created: 16/03/2013
 * Last Modified: 17/03/2013
 */
package GUI;

import Data_Structures.Competitor;
import Data_Structures.Event;
import Data_Structures.Node;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Chris Savill, chs17@aber.ac.uk
 */
public class SelectionWindow extends JFrame implements ActionListener, ListSelectionListener {

    private Event event;
    private int checkpoint;
    private String type;
    private int competitor;
    private boolean checkpointSelected = false;
    private boolean competitorSelected = false;
    private JFrame selectionFrame;
    private JPanel checkpointPanel, competitorPanel, bottomPanel;
    private JLabel checkpointLabel, competitorLabel;
    private DefaultListModel checkpointListModel, competitorListModel;
    private JList checkpointList, competitorList;
    private JScrollPane checkpointListScrollBar, competitorListScrollBar;
    private JButton next;

    public SelectionWindow(Event event, String type, JFrame typeFrame) {
        typeFrame.dispose();
        this.event = event;
        this.type = type;
        
        //Setup frame:
        selectionFrame = new JFrame("Checkpoint and Competitor Selection");
        selectionFrame.setLocation(400, 200);
        selectionFrame.setLayout(new BorderLayout());
        selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sets the default close operation
        selectionFrame.setIconImage(new ImageIcon("horse.jpg").getImage()); //Loads an image and sets it as the frame icon
        //////////////////////////////////////////////////////////////

        //Setup panels:
        checkpointPanel = new JPanel(new BorderLayout()); //Creates new JPanel.
        checkpointPanel.setBorder(new EmptyBorder(10, 25, 10, 25));  //Sets an invisible border to simulate a padding effect
        selectionFrame.add(checkpointPanel, BorderLayout.WEST); //Adds panel to frame and places it in WEST container.
        competitorPanel = new JPanel(new BorderLayout());
        competitorPanel.setBorder(new EmptyBorder(10, 25, 10, 25));
        selectionFrame.add(competitorPanel, BorderLayout.EAST); //Adds panel to frame and places it in EASTcontainer.
        bottomPanel = new JPanel();
        selectionFrame.add(bottomPanel, BorderLayout.SOUTH); //Adds panel to frame and places it in SOUTH container.
        //////////////////////////////////////////////////////////////

        //Setup checkpoint panel components:
        checkpointLabel = new JLabel("Select Checkpoint Below: ");
        checkpointPanel.add(checkpointLabel, BorderLayout.NORTH);

        checkpointListModel = new DefaultListModel();
        checkpointList = new JList(checkpointListModel);
        checkpointList.setBorder(new LineBorder(Color.BLACK));
        checkpointPanel.add(checkpointList, BorderLayout.CENTER);
        checkpointList.addListSelectionListener(this);

        checkpointListScrollBar = new JScrollPane(checkpointList);
        checkpointListScrollBar.setPreferredSize(new Dimension(50, 100));
        checkpointListScrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); //Adds vertical scrollbar to JList
        checkpointListScrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED); //Adds horizontal scrollbar to JList
        checkpointPanel.add(checkpointListScrollBar);
        //////////////////////////////////////////////////////////////

        //Setup competitor panel components:
        competitorLabel = new JLabel("Select Competitor Below: ");
        competitorPanel.add(competitorLabel, BorderLayout.NORTH);

        competitorListModel = new DefaultListModel();
        competitorList = new JList(competitorListModel);
        competitorList.setBorder(new LineBorder(Color.BLACK));
        competitorPanel.add(competitorList, BorderLayout.CENTER);
        competitorList.addListSelectionListener(this);

        competitorListScrollBar = new JScrollPane(competitorList);
        competitorListScrollBar.setPreferredSize(new Dimension(400, 300));
        competitorListScrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); //Adds vertical scrollbar to JList
        competitorListScrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED); //Adds horizontal scrollbar to JList
        competitorPanel.add(competitorListScrollBar);
        //////////////////////////////////////////////////////////////

        //Setup bottom panel components:
        next = new JButton("Next");
        next.setPreferredSize(new Dimension(100, 50));
        bottomPanel.add(next);
        next.addActionListener(this);
        //////////////////////////////////////////////////////////////

        //Finialise frame setup:
        addCheckpoints();
        addCompetitors();
        selectionFrame.pack();
        selectionFrame.setVisible(true); //Makes the frame visible
        //////////////////////////////////////////////////////////////
    }
    /**
     * Method that adds the checkpoint checkpoints to the checkpoint JList
     */
    public void addCheckpoints() {
        checkpointListModel.removeAllElements();

        for (Node currentCheckpoint : event.getCheckpoints()) {
            if (currentCheckpoint.getType().equals(type)) {
                checkpointListModel.addElement(currentCheckpoint.getNumber() + ": " + currentCheckpoint.getType());
            }
        }
    }

    /**
     * Method that adds the competitors to the competitor JList
     */
    public void addCompetitors() {
        competitorListModel.removeAllElements();

        for (Competitor currentCompetitor : event.getCompetitors()) {
            competitorListModel.addElement("Competitor: " + currentCompetitor.getNumber()
                    + "   Course: " + currentCompetitor.getCourse() + "   Name: " + currentCompetitor.getName());
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();

        if (actionCommand.equals("Next")) {
            if (checkpointSelected == true && competitorSelected == true) {
                selectionFrame.setVisible(false);
                TimeWindow timeWindow = new TimeWindow(event, checkpoint, type, competitor, selectionFrame);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(selectionFrame, "Please select both a checkpoint and competitor.");
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent evt) {

        if (!evt.getValueIsAdjusting()) {
            JList list = (JList) evt.getSource();

            if (list.equals(checkpointList)) {
                checkpoint = event.getCheckpoints().get(list.getSelectedIndex()).getNumber();
                checkpointSelected = true;
            } else if (list.equals(competitorList)) {
                competitor = event.getCompetitors().get(list.getSelectedIndex()).getNumber();
                competitorSelected = true;
            }
        }
    }
}
