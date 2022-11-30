package GUI;
import Entities.Student;
import External.JavaxAPI;
import com.sun.tools.jconsole.JConsoleContext;
import UseCases.TagMatchManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import UIController.UIController;

public class FriendListFrame extends JFrame implements ActionListener, ItemListener {

    JLabel friendLabel = new JLabel("Friend List");
    JLabel friendRequestLabel = new JLabel("Friend Requests");
    String[] friendData = {};
    String[] friendRequestData = {};
    JList<String> friendList = new JList(friendData);
    JList<String> friendRequestList = new JList(friendRequestData);
//    JLabel listLabel = new JLabel("Matched Students:");
//    JLabel tagSelectLabel = new JLabel("Select Tag:");
    JButton backBTN = new JButton("Back");

    JButton profileBTN = new JButton("Profile");
//    String[] tagType = {"Adventure", "Music", "Cat", "Outdoors", "Books", "Movies", "Beer", "Video Games", "Photography"};
//    JComboBox<String> tagComboBox = new JComboBox<>(tagType);

//    DefaultListModel<String> matchedStu = new DefaultListModel<>();
//    JList<String> matchedList = new JList<>(matchedStu);

    String[] columnNames = {"First Name", "Requests"};
    Object[][] data =
            {
                    {"Homer Simpson", "Accept"},
                    {"Madge Simpson","Accept"},
                    {"Bart Simpson", "Accept"},
                    {"Lisa Simpson", "Accept"},
            };

    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    JTable table = new JTable( model );

    UIController uiController;


    public FriendListFrame(UIController uiController){
        this.uiController = uiController;

        this.setTitle("Friend List and Requests"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(410, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor


        // places objects inside frame
        //table
        table.setBounds(215,40,180,100);
        table.getColumnModel().getColumn(1).setPreferredWidth(2);

        table.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String selectedData = null;

                int[] selectedRow = table.getSelectedRows();
                int[] selectedColumns = table.getSelectedColumns();

                for (int i = 0; i < selectedRow.length; i++) {
                    for (int j = 0; j < selectedColumns.length; j++) {
                        selectedData = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
                    }
                }
                System.out.println("Selected: " + selectedData);
            }

        });
        // buttons
        backBTN.setBounds(205, 165, 50, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);
//        profileBTN.setBounds(350, 35, 50, 20);
//        profileBTN.addActionListener(this);
//        profileBTN.setFocusable(false);
//        profileBTN.setEnabled(false);

        // labels
//        tagSelectLabel.setBounds(10,10,100,20);
//        listLabel.setBounds(10,35,120,20);

        friendLabel.setBounds(55,10,100,20);
        friendRequestLabel.setBounds(255, 10, 100, 20);

        //list
//        friendList.setBounds(35, 40, 120, 120);
//        friendRequestList.setBounds(255, 40, 120, 120);
//        friendData = new String[]{"friend 1", "friend 2"};
//        String[] data = {"new1", "new2"};
//        friendList.setListData(friendData);
//        friendRequestList.setListData(data);

        // textfields

        // combobox
//        tagComboBox.setBounds(130, 10, 120, 25); // set combobox position
//        tagComboBox.setEditable(false);
//        tagComboBox.addItemListener(this);

        //list
//        matchedList.setBounds(135, 35, 200, 150);

//        uiController.getTagMatchUIControl().setSelectedtag((String) tagComboBox.getSelectedItem());
//        matchedStu = uiController.getTagMatchUIControl().getNameList();
//        matchedList.setModel(matchedStu);
//        matchedList.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if(matchedList.isSelectionEmpty()){
//                    profileBTN.setEnabled(false);
//                }
//                profileBTN.setEnabled(true);
//            }
//        });

        // adds objects to the frame
        this.add(backBTN);
//        this.add(profileBTN);
        this.add(friendLabel);
        this.add(friendRequestLabel);
        this.add(friendList);
        this.add(friendRequestList);
        this.add(table);
//        this.add(listLabel);




//        this.add(tagSelectLabel);
//        this.add(tagComboBox);
//        this.add(matchedList);

        this.setVisible(true); // set frame to visible
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == profileBTN){
//            if(matchedList.getSelectedIndex() != -1){
//                String selectedName = matchedList.getSelectedValue();
//                String selectedID = uiController.getTagMatchUIControl().getSelectedUserID(matchedList.getSelectedIndex());
//                uiController.toProfileDisplay(selectedID);
//            }
        }
        else if(e.getSource() == backBTN){
            //TODO: go to home page
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
//        uiController.getTagMatchUIControl().setSelectedtag((String) tagComboBox.getSelectedItem());
//        matchedStu = uiController.getTagMatchUIControl().getNameList();
//        matchedList.setModel(matchedStu);
//        profileBTN.setEnabled(false);
    }
}
