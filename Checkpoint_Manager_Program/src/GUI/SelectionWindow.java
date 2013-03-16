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
 * @author Chris Savill, chs17@aber.ac.uk * File Name: SelectionWindow.java
 * Description: SelectionWindow GUI class using swing. First Created: 16/03/2013
 * Last Modified: 16/03/2013
 */
public class SelectionWindow extends JFrame implements ActionListener, ListSelectionListener {

    private Event event;
    private JFrame selectionFrame;
    private JPanel nodePanel, competitorPanel, bottomPanel;
    private JLabel nodeLabel, competitorLabel;
    private DefaultListModel nodeListModel, competitorListModel;
    private JList nodeList, competitorList;
    private JScrollPane nodeListScrollBar, competitorListScrollBar;
    private JButton next;

    public SelectionWindow(Event event) {
        this.event = event;

        //Setup frame:
        selectionFrame = new JFrame("Checkpoint Selection");
        JOptionPane.showMessageDialog(selectionFrame, "Data files loaded successfully.");
        selectionFrame.setLocation(400, 200);
        selectionFrame.setLayout(new BorderLayout());
        selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sets the default close operation
        selectionFrame.setIconImage(new ImageIcon("horse.jpg").getImage()); //Loads an image and sets it as the frame icon
        //////////////////////////////////////////////////////////////

        //Setup panels:
        nodePanel = new JPanel(new BorderLayout()); //Creates new JPanel.
        nodePanel.setBorder(new EmptyBorder(10, 50, 20, 25));  //Sets an invisible border to simulate a padding effect
        selectionFrame.add(nodePanel, BorderLayout.WEST); //Adds panel to frame and places it in WEST container.
        competitorPanel = new JPanel(new BorderLayout());
        competitorPanel.setBorder(new EmptyBorder(10, 50, 20, 25));
        selectionFrame.add(competitorPanel, BorderLayout.EAST); //Adds panel to frame and places it in EASTcontainer.
        bottomPanel = new JPanel();
        selectionFrame.add(bottomPanel, BorderLayout.SOUTH); //Adds panel to frame and places it in SOUTH container.
        //////////////////////////////////////////////////////////////

        //Setup node panel components:
        nodeLabel = new JLabel("Select Checkpoint Below: ");
        nodePanel.add(nodeLabel, BorderLayout.NORTH);

        nodeListModel = new DefaultListModel();
        nodeList = new JList(nodeListModel);
        nodeList.setBorder(new LineBorder(Color.BLACK));
        nodePanel.add(nodeList, BorderLayout.CENTER);
        nodeList.addListSelectionListener(this);

        nodeListScrollBar = new JScrollPane(nodeList);
        nodeListScrollBar.setPreferredSize(new Dimension(50, 100));
        nodeListScrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); //Adds vertical scrollbar to JList
        nodeListScrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED); //Adds horizontal scrollbar to JList
        nodePanel.add(nodeListScrollBar);
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

    @Override
    public void actionPerformed(ActionEvent actionCommand) {
        if (actionCommand.equals("Next")) {
            //TimeWindow timeWindow = new TimeWindow(event);
        }
    }

    /**
     * Method that adds the node checkpoints to the node JList
     */
    public void addCheckpoints() {
        nodeListModel.removeAllElements();

        for (Node node : event.getNodes()) {
            nodeListModel.addElement(node.getNumber() + ": " + node.getType());
        }
    }

    /**
     * Method that adds the competitors to the competitor JList
     */
    public void addCompetitors() {
        competitorListModel.removeAllElements();

        for (Competitor competitor : event.getCompetitors()) {
            competitorListModel.addElement("Competitor: " + competitor.getNumber()
                    + "   Course: " + competitor.getCourse() + "   Name: " + competitor.getName());
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
