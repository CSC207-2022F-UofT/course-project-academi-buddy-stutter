package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class implements ProfileDisplayFrame that displays the profile of a user.
 */
public class ProfileDisplayFrame extends JFrame implements ActionListener {
    final JLabel nameLabel = new JLabel("Name:");
    final JLabel emailLabel = new JLabel("Email:");
    final JLabel infoLabel = new JLabel("About:");

    // creating textfields
    final JTextField nameText = new JTextField();
    final JTextField emailText = new JTextField();

    // creating textarea
    final JTextArea infoText = new JTextArea();
    final FrameNavigator frameNavigator;

    final JButton closeBTN = new JButton("Close");
    final JButton addFriendBTN = new JButton("Add Friend");

    final String userID;

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
        nameText.setText(frameNavigator.getProfileDisplayUIPresenter().getName(userID));
        emailText.setText(frameNavigator.getProfileDisplayUIPresenter().getEmail(userID));
        infoText.setText(frameNavigator.getProfileDisplayUIPresenter().getInfo(userID));


        addFriendBTN.setBounds(280, 30, 100, 20);
        addFriendBTN.addActionListener(this);
        addFriendBTN.setFocusable(false);

        // Update addFriend button clickable status
        boolean canAdd = frameNavigator.getFriendListUIPresenter().isRequestSent(userID);
        addFriendBTN.setEnabled(canAdd);
        closeBTN.setBounds(310, 165, 70, 20);
        closeBTN.addActionListener(this);

        // adding elements to frame
        this.add(nameLabel);
        this.add(emailLabel);
        this.add(infoLabel);

        this.add(nameText);
        this.add(emailText);
        this.add(infoText);

        this.add(addFriendBTN);
        this.add(closeBTN);


        this.setTitle(frameNavigator.getProfileDisplayUIPresenter().getName(userID)+ "'s Profile"); // sets frame's title
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
//            if (!FrameNavigator.getFriendListUIPresenter().isRequestSent(userID)) {
            String viewerID = null;
            viewerID = frameNavigator.getProfileUIPresenter().getUserID();
            frameNavigator.getFriendListUIPresenter().sendFriendRequest(viewerID, userID);
                frameNavigator.getFriendListUIPresenter().receiveFriendRequest(viewerID, userID);
                System.out.println("Sending friend request to: " + viewerID);
                addFriendBTN.setEnabled(false);
//            }
        }
    }
}
