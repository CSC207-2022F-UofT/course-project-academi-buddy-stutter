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
import java.sql.Array;
import java.util.ArrayList;

import UIController.UIController;

public class FriendListFrame extends JFrame implements ActionListener, ItemListener {
    String userID;
    JLabel friendLabel = new JLabel("Friend List");
    JLabel friendRequestLabel = new JLabel("Friend Requests");
    String[] friendData = {};
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

    JTable friendRequestTable;

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
        // _____> delete the below line -> only for testing
        //
        //
        //
        // get friend list from the user
        ArrayList<String> friendArray = uiController.getFriendListUIControl().getFriendList();
        String[] friendToString;
        friendToString = uiController.getFriendListUIControl().IdsToFullNames(friendArray);
        friendList.setListData(friendToString);


        //table
        ArrayList<String> friendRequestList = uiController.getFriendListUIControl().getFriendRequestList();

        DefaultTableModel requestsModel = new DefaultTableModel(0, 3) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        requestsModel.setColumnIdentifiers(columnNames);
        friendRequestTable = new JTable( requestsModel );

        // Add every friend request as an Object array into JTable
        for (String friendID : friendRequestList) {
            friendID = friendID.trim().strip();
            Object[] row = new Object[3];
            ArrayList<String> stringRow = new ArrayList<>();
            stringRow.add(uiController.getFriendListUIControl().getFriendFullName(friendID));
            stringRow.add("Accept");
            stringRow.add(friendID);
            row = stringRow.toArray(row);
            requestsModel.addRow(row);
        }


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
                String selectedData = "";
                if (requestsModel.getRowCount() > 0) {
                    selectedData = (String) friendRequestTable.getValueAt(row, col);
                }
                if (selectedData.equals("Accept")) {
                    String name = (String) friendRequestTable.getValueAt(row, 0);
                    String friendID = (String) friendRequestTable.getValueAt(row, 2);
                    friendID = friendID.trim().strip();

                    System.out.println("Accepted " + name + " userID: " + friendID);

                    String userID = uiController.getFriendListUIControl().getUserId();
                    userID = userID.trim().strip();

                    uiController.getFriendListUIControl().acceptFriendRequest(userID, friendID);
                    uiController.getFriendListUIControl().acceptedRequest(friendID, userID);
                    refreshFriendList();

                    requestsModel.removeRow(row);
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

    public void refreshFriendList() {
        ArrayList<String> friendArray = uiController.getFriendListUIControl().getFriendList();
        String[] friendToString;
        friendToString = uiController.getFriendListUIControl().IdsToFullNames(friendArray);
        friendList.setListData(friendToString);
    }
    public String[] convertToArray(ArrayList<Student> students) {
        // Convert accepted student arraylist to string array with only student full name
        // then return student name array

        String[] stringArray = new String[students.size()];
        ArrayList<String> temp = new ArrayList<>();
        // convert ArrayList to String Array for JList
        for (Student friend: students) {
            temp.add(friend.getFullName());
        }
        stringArray = temp.toArray(stringArray);
        return stringArray;
    }
}
