package GUI;
import Entities.Student;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import UIController.UIController;

public class FriendListFrame extends JFrame implements ActionListener, ItemListener {
    UIController uiController;
    JLabel friendLabel = new JLabel("Friend List");
    JLabel friendRequestLabel = new JLabel("Friend Requests");
    String[] requestColumnNames = {"Full Name", "Requests", "userID"};
    DefaultTableModel requestsModel = new DefaultTableModel(0, 3) {
        @Override
        public boolean isCellEditable(int row, int column) {return false;}
    };
    ArrayList<String> friendList;
    ArrayList<String> friendRequestList;
    JTable friendListTable;
    JTable friendRequestTable;
    String[] friendColumnNames = {"Name", "userID"};
    DefaultTableModel friendModel = new DefaultTableModel(0, 2) {
        @Override
        public boolean isCellEditable(int row, int column) {return false;}
    };
    JScrollPane friendsScroll = new JScrollPane(friendListTable);
    JScrollPane requestsScroll = new JScrollPane(friendRequestTable);



    public FriendListFrame(UIController uiController){
        this.uiController = uiController;

        this.setTitle("Friend List and Requests"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(410, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        // places objects inside frame
        refreshFriendListTable();
        refreshFriendRequestListTable();

        // labels
        friendLabel.setBounds(55,10,100,20);
        friendRequestLabel.setBounds(255, 10, 100, 20);

        // get friend list from the user
//        ArrayList<String> friendArray = uiController.getFriendListUIControl().getFriendList();
//        String[] friendToString;
//        friendToString = uiController.getFriendListUIControl().IdsToFullNames(friendArray);
//        friendList.setListData(friendToString);
//
//        MouseListener mouseListener = new MouseAdapter() {
//            public void mouseClicked(MouseEvent mouseEvent) {
//                JList theList = (JList) mouseEvent.getSource();
//                if (mouseEvent.getClickCount() == 2) {
//                    int index = theList.locationToIndex(mouseEvent.getPoint());
//                    if (index >= 0) {
//                        Object o = theList.getModel().getElementAt(index);
//                        System.out.println("Double-clicked on: " + o.toString());
////                        uiController.toProfileDisplay();
//                    }
//                }
//            }
//        };
//        friendList.addMouseListener(mouseListener);
        //scroll pane

//        JScrollPane scrollPaneFriend = new JScrollPane(friendList);
//        scrollPaneFriend.setBounds(35,40,120,100);
//        this.getContentPane().add(scrollPaneFriend, BorderLayout.CENTER);
//
//        JScrollPane scrollPaneRequest = new JScrollPane(friendRequestTable);
//        scrollPaneRequest.setBounds(200,40,200,100);
//        this.getContentPane().add(scrollPaneRequest, BorderLayout.CENTER);
        //JScrollPane
        friendsScroll.setBounds(10,40,180,100);
        requestsScroll.setBounds(215,40,180,100);



        //friend list table
        friendList = uiController.getFriendListUIControl().getFriendList();

        friendModel.setColumnIdentifiers(friendColumnNames);
        friendListTable = new JTable( friendModel );

        // Add every friend request as an Object array into JTable
//        for (String friendID : friendList) {
//
//            friendID = friendID.trim().strip();
//            Object[] row = new Object[2];
//            ArrayList<String> stringRow = new ArrayList<>();
//            stringRow.add(uiController.getFriendListUIControl().getFriendFullName(friendID));
//            stringRow.add(friendID);
//            row = stringRow.toArray(row);
//            friendModel.addRow(row);
//        }

        // position table
        friendListTable.setBounds(10,40,180,50);
        friendListTable.getColumnModel().getColumn(1).setPreferredWidth(3);
        friendListTable.setCellSelectionEnabled(true);

        // set userID column width to 0 so it doesn't show to normal user
        friendListTable.getColumnModel().getColumn(1).setMinWidth(0);
        friendListTable.getColumnModel().getColumn(1).setMaxWidth(0);
        friendListTable.getColumnModel().getColumn(1).setWidth(0);

        //friend request table
        friendRequestList = uiController.getFriendListUIControl().getFriendRequestList();

        requestsModel.setColumnIdentifiers(requestColumnNames);
        friendRequestTable = new JTable( requestsModel );

        // position table
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
                    refreshFriendListTable();
                    refreshFriendRequestListTable();

//                    requestsModel.removeRow(row);
                }
            }
        });
//
        friendsScroll.setViewportView(friendListTable);
        requestsScroll.setViewportView(friendRequestTable);
        friendsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        friendsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        requestsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        requestsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // adds objects to the frame
        this.add(friendLabel);
        this.add(friendRequestLabel);
        this.getContentPane().add(friendsScroll, BorderLayout.CENTER);
        this.getContentPane().add(requestsScroll, BorderLayout.CENTER);
//        this.add(friendListTable);
//        this.add(friendRequestTable);
        this.setTitle("Friends");
        this.setVisible(true); // set frame to visible
    }

    public void actionPerformed(ActionEvent e) {}

    @Override
    public void itemStateChanged(ItemEvent e) {
//        uiController.getTagMatchUIControl().setSelectedtag((String) tagComboBox.getSelectedItem());
//        matchedStu = uiController.getTagMatchUIControl().getNameList();
//        matchedList.setModel(matchedStu);
//        profileBTN.setEnabled(false);
    }

    public void refreshFriendListTable() {
        // helper method that refresh friend list table data

        // get updated friend list data from firebase
//        if (friendListTable != null) {
//            friendListTable.removeRowSelectionInterval(0, friendListTable.getRowCount()-1);
//        }
        friendModel.setRowCount(0);
        friendList = uiController.getFriendListUIControl().getFriendList();

        for (String friendID : friendList) {
            friendID = friendID.trim().strip();
            Object[] row = new Object[2];
            ArrayList<String> stringRow = new ArrayList<>();
            stringRow.add(uiController.getFriendListUIControl().getFriendFullName(friendID));
            stringRow.add(friendID);
            row = stringRow.toArray(row);
            friendModel.addRow(row);
        }
    }

    private void refreshFriendRequestListTable() {
        // helper method that refresh friendrequestlist table data

        // get updated friend request list from firebase
//        if (friendRequestTable != null) {
//            friendRequestTable.removeRowSelectionInterval(0, friendRequestTable.getRowCount()-1);
//        }
        requestsModel.setRowCount(0);
        friendRequestList = uiController.getFriendListUIControl().getFriendRequestList();


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