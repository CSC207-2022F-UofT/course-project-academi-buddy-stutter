package GUI;

import Entities.Student;
import UIController.UIController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.io.IOException;
import java.util.ArrayList;

public class CourseMatchFrame extends JFrame implements ActionListener, ItemListener{
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

    Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);

    UIController uiController;
    public CourseMatchFrame(UIController uiController) {
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

    private void addMatches(ArrayList<Student> students){
        clearMatches();
        for(Student s: students){
            matchedStu.addElement(s.getFullName());
        }
        matchedList.setModel(matchedStu);
        matchedList.clearSelection();
        profileBTN.setEnabled(false);
    }

    private void clearMatches(){
        matchedStu = new DefaultListModel<>();
        matchedList.setModel(matchedStu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == findBTN){
            this.setCursor(waitCursor);
            int numCommon = numBox.getSelectedIndex();
            ArrayList<Student> matches;
            try {
                if(labelBox.getSelectedItem().equals("None")){
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
            this.setCursor(Cursor.getDefaultCursor());
        }

        else if (e.getSource() == profileBTN){
            this.setCursor(waitCursor);
            if(matchedList.getSelectedIndex() != -1){
                String selectedName = matchedList.getSelectedValue();
                String selectedID = uiController.getMatchUIControl().getSelectedUserID(matchedList.getSelectedIndex());
                System.out.println(selectedName + selectedID);
                uiController.toProfileDisplay(selectedID);
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
        if(e.getSource() == returnBTN){
            this.dispose();
        }
    }

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
