package GUI;

import Entities.Student;
import UIController.UIController;
import org.checkerframework.checker.guieffect.qual.UI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements MatchFrame that allows user to find study buddy through matching same courses.
 * User can select number of common sessions and desired match labels to find study buddies.
 */
public class MatchFrame extends JFrame implements ActionListener, ItemListener{
    JLabel numCommonLabel = new JLabel("Enter the Number of Common Sessions:");
    JLabel selectLabel = new JLabel("Select Label:");
    JLabel matchLabel = new JLabel("Matched Students:");
    String[] userType = {"1", "2", "3", "4", "5", "6"};
    JComboBox<String> numBox = new JComboBox<>(userType);
    String[] labels = {"None", "Want to Meet", "Want to Collaborate", "Want to Discuss"};
    JComboBox<String> labelBox = new JComboBox<>(labels);
    DefaultListModel<String> matchedStu = new DefaultListModel<>();
    JList<String> matchedList = new JList<>(matchedStu);
    JButton returnBTN = new JButton("Back");
    JButton findBTN = new JButton("Find");
    JButton profileBTN = new JButton("Profile");

    /**
     * This constructor method implements all UI components for MatchFrame.
     */
    UIController uiController;
    public MatchFrame(UIController uiController) {
        this.uiController = uiController;
        // setting up labels:
        numCommonLabel.setBounds(10, 10, 270, 20);
        selectLabel.setBounds(10, 35, 100, 20);
        matchLabel.setBounds(10, 60, 120, 20);

        // setting up combobox for numSameSessions
        numBox.setEditable(false);
        numBox.setBounds(280, 10, 70, 20);

        // setting up combobox for labels
        labelBox.setEditable(false);
        labelBox.setBounds(135, 35, 215, 20);
        labelBox.addItemListener(this);

        // setting up buttons
        returnBTN.setBounds(380, 160, 50, 20);
        findBTN.setBounds(380, 10, 50, 20);
        findBTN.addActionListener(this);
        findBTN.setFocusable(false);

        profileBTN.setBounds(380, 60, 50, 20);
        profileBTN.addActionListener(this);
        profileBTN.setFocusable(false);
        profileBTN.setEnabled(false);
        returnBTN.addActionListener(this);

        // setting up textareas
        matchedList.setBounds(135, 60, 200, 120);
        matchedList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(matchedList.isSelectionEmpty()){
                    profileBTN.setEnabled(false);
                }
                profileBTN.setEnabled(true);
            }
        });

        // adding elements to frame
        this.add(numCommonLabel);
        this.add(selectLabel);
        this.add(matchLabel);

        this.add(matchedList);

        this.add(numBox);
        this.add(labelBox);
        this.add(returnBTN);
        this.add(findBTN);
        this.add(profileBTN);

        this.setTitle("Match by Course"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(450, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);
    }

    /**
     * This method appends matched study buddies to the JList, displaying their full names instead of User ID.
     * @param students this list contains matched study buddies for the user
     */
    private void addMatches(ArrayList<Student> students){
        clearMatches();
        for(Student s: students){
            matchedStu.addElement(s.getFullName());
        }
        matchedList.setModel(matchedStu);
    }

    /**
     * This method clears all text inside the JList.
     */
    private void clearMatches(){
        matchedStu = new DefaultListModel<>();
        matchedList.setModel(matchedStu);
    }

    /**
     * When the "Find" button is clicked, this method calls corresponding controller to access the matching algorithm,
     * then displays the matched study buddies to JList.
     * User can also see matched study buddies' profile by highlighting their names and click the "Profile" button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == findBTN){
            int numCommon = numBox.getSelectedIndex();
            ArrayList<Student> matches;
            try {
                if((String)labelBox.getSelectedItem() == "None"){
                    matches = this.uiController.getMatchUIControl().getMatches(numCommon);
                }else{

                    this.uiController.getMatchUIControl().getMatches(numCommon);
                    matches = uiController.getMatchUIControl().
                            getLabeledMatches((String)labelBox.getSelectedItem());
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            addMatches(matches);

        }

        else if (e.getSource() == profileBTN){

            if(matchedList.getSelectedIndex() != -1){
                String selectedName = matchedList.getSelectedValue();
                String selectedID = uiController.getMatchUIControl().getSelectedUserID(matchedList.getSelectedIndex());
                System.out.println(selectedName + selectedID);
                uiController.toProfileDisplay(selectedID);
            }
        }
        if(e.getSource() == returnBTN){
            this.dispose();
        }
    }

    /**
     * This method applies filtering to the matching algorithm so that it only searches students that satisfy both
     * conditions. [Number of common sessions and Same Labels]
     */
    @Override
    public void itemStateChanged(ItemEvent e) {

        if(e.getSource() == labelBox){

            ArrayList<Student> filteredStudents = uiController.getMatchUIControl().
                    getLabeledMatches((String)labelBox.getSelectedItem());

            addMatches(filteredStudents);

            if(filteredStudents.size() == 0){
                profileBTN.setEnabled(false);
            }
        }
    }
}
