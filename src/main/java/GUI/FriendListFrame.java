package GUI;
import Entities.Student;
import External.JavaxAPI;
import com.sun.tools.jconsole.JConsoleContext;
import UseCases.TagMatchManager;

import javax.swing.*;
import javax.swing.event.*;
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
    JButton backBTN = new JButton("Back");

    JButton profileBTN = new JButton("Profile");

    String[] columnNames = {"First Name", "Requests", "userID"};
    Object[][] requestData =
            {
                    {"Elon Musk", "Accept", "u1"},
                    {"Bill Gates","Accept", "u2"},
                    {"Elon Ma", "Accept", "u3"},
                    {"John Wick", "Accept", "u4"},
            };

    DefaultTableModel model = new DefaultTableModel(requestData, columnNames) {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    JTable friendRequestTable = new JTable( model );

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

        // buttons
        backBTN.setBounds(180, 165, 50, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);

        // labels
        friendLabel.setBounds(55,10,100,20);
        friendRequestLabel.setBounds(255, 10, 100, 20);

        //list
        friendData = new String[]{"friend 1", "friend 2", "friend 3", "friend 4", "friend 5", "friend 6", "friend 7"};
        friendList.setListData(friendData);

        //table
        friendRequestTable.setBounds(215,40,180,50);
        friendRequestTable.getColumnModel().getColumn(1).setPreferredWidth(3);

        friendRequestTable.setCellSelectionEnabled(true);

        // set userID column width to 0 so it doesn't show to normal user
        friendRequestTable.getColumnModel().getColumn(2).setMinWidth(0);
        friendRequestTable.getColumnModel().getColumn(2).setMaxWidth(0);
        friendRequestTable.getColumnModel().getColumn(2).setWidth(0);

        friendRequestTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Return value of a table where mouse is clicked
                // Accept incoming friend request and delete accepted row
                int row = friendRequestTable.rowAtPoint(e.getPoint());
                int col = friendRequestTable.columnAtPoint(e.getPoint());

                String selectedData = (String) friendRequestTable.getValueAt(row, col);
                if (selectedData.equals("Accept")) {
                    String name = (String) friendRequestTable.getValueAt(row, 0);
                    String userID = (String) friendRequestTable.getValueAt(row, 2);
                    System.out.println("Accepted " + name + "userID: " + userID);
                    model.removeRow(row);
                } else {
                    System.out.println("Clicked " + selectedData);
                }
            }
        });
//

        //scroll pane
        JScrollPane scrollPaneFriend = new JScrollPane(friendList);
        scrollPaneFriend.setBounds(35,40,120,100);
        this.getContentPane().add(scrollPaneFriend, BorderLayout.CENTER);

        JScrollPane scrollPaneRequest = new JScrollPane(friendRequestTable);
        scrollPaneRequest.setBounds(200,40,200,100);
        this.getContentPane().add(scrollPaneRequest, BorderLayout.CENTER);

        //get friend list from student data

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
        this.add(friendLabel);
        this.add(friendRequestLabel);

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
