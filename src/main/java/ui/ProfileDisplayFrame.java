package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This class implements ProfileDisplayFrame that displays the profile of a user.
 */
public class ProfileDisplayFrame extends JFrame implements ActionListener {
    JLabel nameLabel = new JLabel("Name:");
    JLabel emailLabel = new JLabel("Email:");
    JLabel infoLabel = new JLabel("About:");

    // creating textfields
    JTextField nameText = new JTextField();
    JTextField emailText = new JTextField();

    // creating textarea
    JTextArea infoText = new JTextArea();
    FrameNavigator frameNavigator;

    JButton closeBTN = new JButton("Close");
    JButton addFriendBTN = new JButton("Add Friend");

    String userID;

    /**
     * This constructor method implements all UI components for ProfileDisplayFrame.
     * @param userID the User ID of the user that the program is displaying, pulls user profile from database
     */
    public ProfileDisplayFrame(FrameNavigator frameNavigator, String userID){
        this.frameNavigator = frameNavigator;
        this.userID = userID;


        // Labels
        nameLabel.setBounds(10, 10, 100, 20);
        emailLabel.setBounds(10, 35, 100, 20);
        infoLabel.setBounds(10, 60, 100, 20);

        nameText.setBounds(70, 10, 200, 20);
        emailText.setBounds(70, 35, 200, 20);
        infoText.setBounds(70, 60, 200, 40);
        nameText.setEditable(false);
        emailText.setEditable(false);
        infoText.setEditable(false);
        nameText.setText(frameNavigator.getProfileDisplayUIControl().getName(userID));
        emailText.setText(frameNavigator.getProfileDisplayUIControl().getEmail(userID));
        infoText.setText(frameNavigator.getProfileDisplayUIControl().getInfo(userID));


        addFriendBTN.setBounds(280, 30, 100, 20);
        addFriendBTN.addActionListener(this);
        addFriendBTN.setFocusable(false);

        // Update addFriend button clickable status
        boolean canAdd = frameNavigator.getFriendListUIControl().isRequestSent(userID);
        addFriendBTN.setEnabled(canAdd);

//        addFriendBTN.setFocusable(false);
//        if (FrameNavigator.getFriendListUIControl().getFriendList().contains(userID) &&
//            FrameNavigator.getFriendListUIControl().getFriendRequestSentList().contains(userID)) {
//            addFriendBTN.setEnabled(false);
//        } else {
//            addFriendBTN.setEnabled(true);
//        }

        closeBTN.setBounds(310, 165, 70, 20);
        closeBTN.addActionListener(this);
//        closeBTN.setFocusable(false);

        // adding elements to frame
        this.add(nameLabel);
        this.add(emailLabel);
        this.add(infoLabel);

        this.add(nameText);
        this.add(emailText);
        this.add(infoText);

        this.add(addFriendBTN);
        this.add(closeBTN);


        this.setTitle(frameNavigator.getProfileDisplayUIControl().getName(userID)+ "'s Profile"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(400, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);
    }

    /**
     * When the "Add Friend" button is clicked, the program calls corresponding controllers to send friend request to
     * the user (with userID).
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == closeBTN){
            this.dispose();
        }
        else if (e.getSource() == addFriendBTN) {
//            if (!FrameNavigator.getFriendListUIControl().isRequestSent(userID)) {
            String viewerID = null;
            try {
                viewerID = frameNavigator.getProfileUIControl().getUserID();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            frameNavigator.getFriendListUIControl().sendFriendRequest(viewerID, userID);
                frameNavigator.getFriendListUIControl().receiveFriendRequest(viewerID, userID);
                System.out.println("Sending friend request to: " + viewerID);
                addFriendBTN.setEnabled(false);
//            }
        }
    }
}
