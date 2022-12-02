package GUI;

import UIController.UIController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class HomeFrame extends JFrame implements ActionListener {
    JLabel titleLabel = new JLabel("Welcome, ");
    JLabel statusLabel = new JLabel("Status:");
    JLabel completedLabel = new JLabel("");
    JButton logoutBTN = new JButton("LOG OUT");
    JButton changePasswordBTN = new JButton("Change Password");
    JButton findStudyBuddyBTN = new JButton("Find Study Buddy");
    JButton findInsBuddyBTN = new JButton("Find Interest Buddy");
    JButton statusBTN = new JButton("Status Labels");
    JButton insTagBTN = new JButton("Interest Tags");
    JButton profileBTN = new JButton("Profile");
    JButton uploadBTN = new JButton("Upload Courses");

    UIController uiController;

    public HomeFrame(UIController uiController){
        this.uiController = uiController;

        // setting up labels
        titleLabel.setBounds(10, 0, 100, 50);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        completedLabel.setBounds(222, 62, 110, 20);
        completedLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        statusLabel.setBounds(190, 62, 110, 20);
        statusLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        // setting up buttons
        logoutBTN.setBounds(250, 170, 80, 20);
        logoutBTN.addActionListener(this);
        changePasswordBTN.setBounds(200, 50, 130, 20);
        changePasswordBTN.addActionListener(this);
        uploadBTN.setBounds(80, 60, 110, 20);
        uploadBTN.addActionListener(this);
        findStudyBuddyBTN.setBounds(100, 120, 130, 20);
        findInsBuddyBTN.setBounds(180, 90, 150, 20);
        findInsBuddyBTN.addActionListener(this);
        statusBTN.setBounds(10, 115, 130, 20);
        statusBTN.addActionListener(this);
        insTagBTN.setBounds(180, 115, 150, 20);
        insTagBTN.addActionListener(this);
        profileBTN.setBounds(250, 10, 50, 20);
        profileBTN.addActionListener(this);


        // adding elements to frame
        this.add(titleLabel);
        this.add(completedLabel);
        this.add(statusLabel);
        this.add(logoutBTN);
        this.add(changePasswordBTN);
        this.add(findInsBuddyBTN);
        this.add(statusBTN);
        this.add(insTagBTN);
        this.add(profileBTN);
//        this.add(uploadBTN);

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

        if (e.getSource() == insTagBTN) {
            uiController.toTagSelect();
        }
        else if (e.getSource() == statusBTN) {
            uiController.toLabelSelect();
        }
        else if (e.getSource() == findInsBuddyBTN) {
            uiController.toTagMatch();
        }
        else if (e.getSource() == profileBTN) {
            uiController.toProfile();
        }

        if (e.getSource() == logoutBTN) {
            this.dispose();
            uiController.unloadUser();
            uiController.toLogin();
        }

        if (e.getSource() == findStudyBuddyBTN) {
            this.dispose();
            uiController.toMatch();
        }

    }
}

