package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import UIController.UIController;

/**
 * This class implements TagMatchFrame so that user can find buddies by matching same interest tags.
 */
public class TagMatchFrame extends JFrame implements ActionListener, ItemListener {
    JLabel listLabel = new JLabel("Matched Students:");
    JLabel tagSelectLabel = new JLabel("Select TagDataManager:");
    JButton backBTN = new JButton("Back");

    JButton profileBTN = new JButton("Profile");
    String[] tagType = {"Adventure", "Music", "Cat", "Outdoors", "Books", "Movies", "Beer", "Video Games", "Photography"};
    JComboBox<String> tagComboBox = new JComboBox<>(tagType);

    DefaultListModel<String> matchedStu = new DefaultListModel<>();
    JList<String> matchedList = new JList<>(matchedStu);

    UIController uiController;
    Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);

    /**
     * Implements all UI components.
     */
    public TagMatchFrame(UIController uiController) throws IOException {
        this.uiController = uiController;

        this.setTitle("Match by TagDataManager"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(450, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor


        // places objects inside frame
        // buttons
        backBTN.setBounds(350, 165, 70, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);
        profileBTN.setBounds(350, 35, 80, 20);
        profileBTN.addActionListener(this);
        profileBTN.setFocusable(false);
        profileBTN.setEnabled(false);

        // labels
        tagSelectLabel.setBounds(10,10,100,20);
        listLabel.setBounds(10,35,120,20);

        // textfields

        // combobox
        tagComboBox.setBounds(130, 10, 120, 25); // set combobox position
        tagComboBox.setEditable(false);
        tagComboBox.addItemListener(this);

        //list
        matchedList.setBounds(135, 35, 200, 150);

        uiController.getTagMatchUIControl().setSelectedtag((String) tagComboBox.getSelectedItem());
        matchedStu = uiController.getTagMatchUIControl().getNameList();
        matchedList.setModel(matchedStu);
        matchedList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(matchedList.isSelectionEmpty()){
                    profileBTN.setEnabled(false);
                }
                profileBTN.setEnabled(true);
            }
        });

        // adds objects to the frame
        this.add(backBTN);
        this.add(profileBTN);
        this.add(listLabel);




        this.add(tagSelectLabel);
        this.add(tagComboBox);
        this.add(matchedList);

        this.setVisible(true); // set frame to visible
    }

    /**
     * The user can view matched buddies' profile by clicking the "Profile" button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == profileBTN){
            this.setCursor(waitCursor);
            if(matchedList.getSelectedIndex() != -1){
                String selectedName = matchedList.getSelectedValue();
                String selectedID = uiController.getTagMatchUIControl().getSelectedUserID(matchedList.getSelectedIndex());
                System.out.println(selectedName + selectedID);
                uiController.toProfileDisplay(selectedID);
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
        else if(e.getSource() == backBTN){
            this.dispose();
        }

    }

    /**
     * Calls tag match algorithm, finds matched buddies, display all buddies onto JList.
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        this.setCursor(waitCursor);
        uiController.getTagMatchUIControl().setSelectedtag((String) tagComboBox.getSelectedItem());
        try {
            matchedStu = uiController.getTagMatchUIControl().getNameList();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        matchedList.setModel(matchedStu);
        profileBTN.setEnabled(false);
        this.setCursor(Cursor.getDefaultCursor());
    }
}