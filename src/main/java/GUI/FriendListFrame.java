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

/**
 * Implements JFrame that shows accepted friends and pending accepting friends to user
 */
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
    String[] friendColumnNames = {"Full Name", "userID"};
    DefaultTableModel friendModel = new DefaultTableModel(0, 2) {
        @Override
        public boolean isCellEditable(int row, int column) {return false;}
    };
    JScrollPane friendsScroll = new JScrollPane(friendListTable);
    JScrollPane requestsScroll = new JScrollPane(friendRequestTable);


    /**
     * implements all UI components
     */
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

        //JScrollPane
        friendsScroll.setBounds(10,40,180,100);
        requestsScroll.setBounds(215,40,180,100);

        //friend list table
        friendList = uiController.getFriendListUIControl().getFriendList();

        friendModel.setColumnIdentifiers(friendColumnNames);
        friendListTable = new JTable( friendModel );

        // position table
        friendListTable.setBounds(10,40,180,50);
        friendListTable.getColumnModel().getColumn(1).setPreferredWidth(3);
        friendListTable.setCellSelectionEnabled(true);

        // set userID column width to 0 so it doesn't show to normal user
        friendListTable.getColumnModel().getColumn(1).setMinWidth(0);
        friendListTable.getColumnModel().getColumn(1).setMaxWidth(0);
        friendListTable.getColumnModel().getColumn(1).setWidth(0);

        friendListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // go to friend's profile display page when clicked
                int row = friendListTable.rowAtPoint(e.getPoint());
                int col = friendListTable.columnAtPoint(e.getPoint());
                String selectedData = "";
                System.out.println(friendModel.getRowCount());
                if (friendModel.getRowCount() > 0) {
                    selectedData = (String) friendListTable.getValueAt(row, col);
                }
                System.out.println(selectedData);
                if (!selectedData.equals("")) {
                    String friendID = (String) friendListTable.getValueAt(row, 1);
                    friendID = friendID.trim().strip();
                    uiController.toProfileDisplay(friendID);
                }
            }
        });

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
                // Approve incoming friend request and delete accepted row
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

                } else {
                    String friendID = (String) friendRequestTable.getValueAt(row, 2);
                    friendID = friendID.trim().strip();
                    uiController.toProfileDisplay(friendID);
                }
            }
        });

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
        this.setTitle("Friends");
        this.setVisible(true); // set frame to visible
    }

    public void actionPerformed(ActionEvent e) {}

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    /**
     * refreshes friend list table
     */
    public void refreshFriendListTable() {
        friendModel.setRowCount(0);
        friendList = uiController.getFriendListUIControl().getFriendList();

        AllStudentsFrame.addStudentsToList(friendList, (UIController) uiController, friendModel);
    }

    /**
     * Refreshes friend request list table
     */
    private void refreshFriendRequestListTable() {
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

    /**
     * Convert accepted student arraylist to string array with only student full name
     * @param students a list of Student objects
     * @return student name array
     */
    public String[] convertToArray(ArrayList<Student> students) {
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