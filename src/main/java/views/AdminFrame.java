package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

/**
 * This class implements a AdminFrame that only displays to Administrative Users.
 * Administrative user can remove user by searching User ID.
 */
public class AdminFrame extends JFrame implements ActionListener {
    JLabel titleLabel = new JLabel();
    JLabel resultLabel = new JLabel();
    JButton logoutBTN = new JButton("LOG OUT");
    JButton removeUserBTN = new JButton("remove");
    JLabel enterLabel = new JLabel("Enter User ID:");
    JTextField userIDField = new JTextField();
    Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
    FrameNavigator frameNavigator;



    /**
     * This constructor method implements all UI elements for the AdminFrame.
     */

    public AdminFrame(FrameNavigator frameNavigator) throws IOException {
        this.frameNavigator = frameNavigator;

        // setting up labels
        titleLabel.setBounds(10, 0, 200, 50);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        titleLabel.setText("Welcome, " + frameNavigator.getAdminUIPresenter().getName().split("\\s+")[0]);
        resultLabel.setBounds(270, 50, 100, 20);
        enterLabel.setBounds(10, 50, 100, 20);

        // setting up buttons
        logoutBTN.setBounds(250, 170, 80, 20);
        logoutBTN.addActionListener(this);
        removeUserBTN.setBounds(120, 100, 100, 20);
        removeUserBTN.addActionListener(this);

        userIDField.setBounds(110, 50, 150, 20);


        // adding elements to frame
        this.add(resultLabel);
        this.add(titleLabel);
        this.add(logoutBTN);
        this.add(removeUserBTN);
        this.add(userIDField);
        this.add(enterLabel);

        this.setTitle("Admin Frame"); // sets frame's title
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true); // set frame to visible
    }

    /**
     * When user clicks on the [Remove] button, the program searches User ID and try to remove that user from database,
     * A "Successful" message will be shown when user is removed from database.
     * A "Failed" message will be shown when user cannot be removed from database.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == removeUserBTN){
            String userID = userIDField.getText();
            boolean removed = frameNavigator.getAdminUIPresenter().removeUser(userID);
            if(removed){
                resultLabel.setForeground(Color.GREEN);
                resultLabel.setText("Successful");
            }else {
                resultLabel.setForeground(Color.RED);
                resultLabel.setText("Failed");
            }
            userIDField.setText("");
        }
        else if (e.getSource() == logoutBTN){
            this.dispose();
            frameNavigator.toLogin();
        }

    }

}

