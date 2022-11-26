package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame implements ActionListener {
    JLabel titleLabel = new JLabel("Welcome, ");

    JButton logoutBTN = new JButton("LOG OUT");
    JButton changePasswordBTN = new JButton("Change Password");
    JButton findStudyBuddyBTN = new JButton("Find Study Buddy");
    JButton findInsBuddyBTN = new JButton("Find Interest Buddy");
    JButton statusBTN = new JButton("Status Labels");
    JButton insTagBTN = new JButton("Interest Tags");
    JButton profileBTN = new JButton("Profile");

    UIController uiController;

    public HomeFrame(UIController uiController){
        this.uiController = uiController;

        // setting up labels
        titleLabel.setBounds(10, 0, 100, 50);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        // setting up buttons
        logoutBTN.setBounds(250, 10, 80, 20 );
        logoutBTN.addActionListener(this);
        changePasswordBTN.setBounds(200, 35, 130, 20);
        changePasswordBTN.addActionListener(this);
        findStudyBuddyBTN.setBounds(10, 90, 130, 20);
        findInsBuddyBTN.setBounds(180, 90, 150, 20);
        statusBTN.setBounds(10, 115, 130, 20);
        insTagBTN.setBounds(180, 115, 150, 20);
        profileBTN.setBounds(280, 170, 50, 20);


        // adding elements to frame
        this.add(titleLabel);
        this.add(logoutBTN);
        this.add(changePasswordBTN);
        this.add(findStudyBuddyBTN);
        //this.add(findInsBuddyBTN);
        //this.add(statusBTN);
        //this.add(insTagBTN);
        this.add(profileBTN);

        this.setTitle("Home Frame"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true); // set frame to visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == logoutBTN){
            this.dispose();
            this.uiController.unloadUser();
            this.uiController.toLogin();
        }

    }
}

