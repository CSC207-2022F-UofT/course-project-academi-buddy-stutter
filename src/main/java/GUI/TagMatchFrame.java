package GUI;
import Users.Student;
import com.sun.tools.jconsole.JConsoleContext;
import useCases.TagMatchManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class TagMatchFrame extends JFrame implements ActionListener, ItemListener {


    JLabel listLabel = new JLabel("Matched Students:");
    JLabel tagSelectLabel = new JLabel("Select Tag:");
    JButton backBTN = new JButton("Back");

    JButton profileBTN = new JButton("Profile");
    String[] tagType = {"Adventure", "Music", "Cat", "Outdoors", "Books", "Movies", "Beer", "Video Games", "Photography"};
    JComboBox<String> tagComboBox = new JComboBox<>(tagType);

    DefaultListModel<String> matchedStu = new DefaultListModel<>();
    JList<String> matchedList = new JList<>(matchedStu);

    UIController uiController;


    public TagMatchFrame(UIController uiController){
        this.uiController = uiController;

        this.setTitle("Match by Tag"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(410, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor


        // places objects inside frame
        // buttons
        backBTN.setBounds(350, 165, 50, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);
        profileBTN.setBounds(350, 35, 50, 20);
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

        uiController.setSelectedtag((String) tagComboBox.getSelectedItem());
        matchedStu = uiController.getNameList();
        uiController.getTagMatchUIControl().setSelectedtag((String) tagCheckBox.getSelectedItem());
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


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == profileBTN){
            if(matchedList.getSelectedIndex() != -1){
                String selectedName = matchedList.getSelectedValue();
                String selectedID = uiController.getTagMatchUIControl().getSelectedIndex(matchedList.getSelectedIndex());
                //TODO: go to profile page
            }
        }
        else if(e.getSource() == backBTN){
            //TODO: go to home page
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        uiController.setSelectedtag((String) tagComboBox.getSelectedItem());
        matchedStu = uiController.getNameList();
        matchedList.setModel(matchedStu);
        profileBTN.setEnabled(false);
    }
}