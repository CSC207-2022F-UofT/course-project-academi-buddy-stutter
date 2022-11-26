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

    JButton profileBTN = new JButton("Go to Profile");
    String[] tagType = {"Adventure", "Music", "Cat", "Outdoors", "Books", "Movies", "Beer", "Video Games", "Photography"};
    JComboBox<String> tagCheckBox = new JComboBox<>(tagType);

    DefaultListModel<String> matchedStu = new DefaultListModel<>();
    JList<String> matchedList = new JList<>(matchedStu);

    TagMatchUIControl tagMatchUIControl;


    public TagMatchFrame(TagMatchUIControl tagMatchUIControl){
        this.tagMatchUIControl = tagMatchUIControl;

        this.setTitle("Match by Tag"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor


        // places objects inside frame
        // buttons
        backBTN.setBounds(650, 530, 100, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);
        profileBTN.setBounds(625, 75, 150, 20);
        profileBTN.addActionListener(this);
        profileBTN.setFocusable(false);

        // labels
        tagSelectLabel.setBounds(20,30,100,20);
        listLabel.setBounds(20,70,150,20);

        // textfields

        // combobox
        tagCheckBox.setBounds(110, 30, 150, 25); // set combobox position
        tagCheckBox.setEditable(false);
        tagCheckBox.addItemListener(this);

        //list
        matchedList.setBounds(200, 75, 400, 450);

        tagMatchUIControl.setSelectedtag((String) tagCheckBox.getSelectedItem());
        matchedStu = tagMatchUIControl.getNameList();
        matchedList.setModel(matchedStu);

        // adds objects to the frame
        this.add(backBTN);
        this.add(profileBTN);
        this.add(listLabel);




        this.add(tagSelectLabel);
        this.add(tagCheckBox);
        this.add(matchedList);

        this.setVisible(true); // set frame to visible
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == profileBTN){
            if(matchedList.getSelectedIndex() != -1){
                String selectedName = matchedList.getSelectedValue();
                String selectedID = tagMatchUIControl.getSelectedIndex(matchedList.getSelectedIndex());
                //TODO: go to profile page
            }
        }
        else






            if(e.getSource() == backBTN){
            //TODO: go to home page
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        tagMatchUIControl.setSelectedtag((String) tagCheckBox.getSelectedItem());
        matchedStu = tagMatchUIControl.getNameList();
        matchedList.setModel(matchedStu);
    }
}