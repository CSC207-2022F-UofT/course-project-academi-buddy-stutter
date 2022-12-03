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

/**
 * This class constructs a home frame for the program. For which the user can access:
 * - personal profile
 * - Course Match Frame
 * - Interest Match Frame
 * - Label Select Frame
 * - Interest Tag Select Frame
 * This JFrame is considered as the main body of the program where the user can use different features of our program
 * by clicking buttons on the JFrame.
 */
public class HomeFrame extends JFrame implements ActionListener {
    JLabel titleLabel = new JLabel();
    JLabel completedLabel = new JLabel("");
    JButton logoutBTN = new JButton("LOG OUT");
    JButton findStudyBuddyBTN = new JButton("Find Study Buddy");
    JButton findInsBuddyBTN = new JButton("Find Interest Buddy");
    JButton statusBTN = new JButton("Status Labels");
    JButton insTagBTN = new JButton("Interest Tags");
    JButton profileBTN = new JButton("Profile");
    JButton uploadBTN = new JButton("Upload Courses");
    Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
    UIController uiController;

    /**
     * Constructor Method: generates HomeFrame and positions the UI components
     * - logoutBTN: returns to LoginFrame
     * - findStudyBuddyBTN: goes to CourseMatchFrame
     * - findInsBuddyBTN: goes to TagMatchFrame
     * - statusBTN: goes to LabelSelectFrame
     * - insTagBTN: goes to TagSelectFrame
     * - profileBTN: goes to ProfileFrame
     */
    public HomeFrame(UIController uiController){
        this.uiController = uiController;

        // setting up labels
        titleLabel.setBounds(10, 0, 200, 50);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        titleLabel.setText("Welcome, " + uiController.getHomeUIControl().getName().split("\\s+")[0]);
        completedLabel.setBounds(222, 62, 110, 20);
        completedLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        // setting up buttons
        logoutBTN.setBounds(210, 170, 100, 20);
        logoutBTN.addActionListener(this);
        uploadBTN.setBounds(80, 60, 110, 20);
        uploadBTN.addActionListener(this);
        findStudyBuddyBTN.setBounds(10, 90, 150, 20);
        findStudyBuddyBTN.addActionListener(this);
        findInsBuddyBTN.setBounds(180, 90, 150, 20);
        findInsBuddyBTN.addActionListener(this);
        statusBTN.setBounds(10, 115, 150, 20);
        statusBTN.addActionListener(this);
        insTagBTN.setBounds(180, 115, 150, 20);
        insTagBTN.addActionListener(this);
        profileBTN.setBounds(230, 10, 80, 20);
        profileBTN.addActionListener(this);

        // adding elements to frame
        this.add(titleLabel);
        this.add(completedLabel);
        this.add(logoutBTN);
        this.add(findStudyBuddyBTN);
        this.add(findInsBuddyBTN);
        this.add(statusBTN);
        this.add(insTagBTN);
        this.add(profileBTN);

        this.setTitle("Home"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(360, 230);
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
            this.setCursor(waitCursor);
            uiController.toProfile();
            this.setCursor(Cursor.getDefaultCursor());
        }

        if (e.getSource() == logoutBTN) {
            this.dispose();
            uiController.unloadUser();
            uiController.toLogin();
        }

        if (e.getSource() == findStudyBuddyBTN) {
            uiController.toMatch();
        }

    }
}

