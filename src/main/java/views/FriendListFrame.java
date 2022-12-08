package views;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Implements JFrame that shows accepted friends and pending accepting friends to user
 */
public class FriendListFrame extends JFrame implements ActionListener, ItemListener {
    final FrameNavigator frameNavigator;
    final JLabel friendLabel = new JLabel("Friend List");
    final JLabel friendRequestLabel = new JLabel("Friend Requests");
    final String[] requestColumnNames = {"Full Name", "Requests", "userID"};
    final DefaultTableModel requestsModel = new DefaultTableModel(0, 3) {
        @Override
        public boolean isCellEditable(int row, int column) {return false;}
    };
    ArrayList<String> friendList;
    ArrayList<String> friendRequestList;
    JTable friendListTable;
    JTable friendRequestTable;
    final String[] friendColumnNames = {"Full Name", "userID"};
    final DefaultTableModel friendModel = new DefaultTableModel(0, 2) {
        @Override
        public boolean isCellEditable(int row, int column) {return false;}
    };
    final JScrollPane friendsScroll = new JScrollPane(friendListTable);
    final JScrollPane requestsScroll = new JScrollPane(friendRequestTable);


    /**
     * implements all UI components
     */
    public FriendListFrame(FrameNavigator frameNavigator) throws IOException {
        this.frameNavigator = frameNavigator;

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
        friendList = frameNavigator.getFriendListUIPresenter().getFriendList();

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
                    frameNavigator.toProfileDisplay(friendID);
                }
            }
        });

        //friend request table
        friendRequestList = frameNavigator.getFriendListUIPresenter().getFriendRequestList();

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

                    String userID = frameNavigator.getFriendListUIPresenter().getUserId();
                    userID = userID.trim().strip();

                    frameNavigator.getFriendListUIPresenter().acceptFriendRequest(userID, friendID);
                    frameNavigator.getFriendListUIPresenter().acceptedRequest(friendID, userID);
                    try {
                        refreshFriendListTable();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    refreshFriendRequestListTable();

                } else {
                    String friendID = (String) friendRequestTable.getValueAt(row, 2);
                    friendID = friendID.trim().strip();
                    frameNavigator.toProfileDisplay(friendID);
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
    public void refreshFriendListTable() throws IOException {
        friendModel.setRowCount(0);
        friendList = frameNavigator.getFriendListUIPresenter().getFriendList();

        AllStudentsFrame.addStudentsToList(friendList, frameNavigator, friendModel);
    }

    /**
     * Refreshes friend request list table
     */
    private void refreshFriendRequestListTable() {
        requestsModel.setRowCount(0);
        friendRequestList = frameNavigator.getFriendListUIPresenter().getFriendRequestList();

        for (String friendID : friendRequestList) {
            friendID = friendID.trim().strip();
            Object[] row = new Object[3];
            ArrayList<String> stringRow = new ArrayList<>();
            System.out.println(friendID);
            stringRow.add(frameNavigator.getFriendListUIPresenter().getFriendFullName(friendID));
            stringRow.add("Accept");
            stringRow.add(friendID);
            row = stringRow.toArray(row);
            requestsModel.addRow(row);
        }
    }
}