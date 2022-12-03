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

public class AdminFrame extends JFrame implements ActionListener {
    JLabel titleLabel = new JLabel();

    JLabel resultLabel = new JLabel();
    JButton logoutBTN = new JButton("LOG OUT");
    JButton removeUserBTN = new JButton("remove");


    JTextField userIDField = new JTextField();

    Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);

    UIController uiController;

    public AdminFrame(UIController uiController){
        this.uiController = uiController;

        // setting up labels
        titleLabel.setBounds(10, 0, 200, 50);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        titleLabel.setText("Welcome, " + uiController.getAdminUIControl().getName().split("\\s+")[0]);
        resultLabel.setBounds(10, 100, 100, 50);
        resultLabel.setBounds(80, 100, 200, 50);
        // setting up buttons
        logoutBTN.setBounds(250, 170, 80, 20);
        logoutBTN.addActionListener(this);
        removeUserBTN.setBounds(10, 115, 150, 20);
        removeUserBTN.addActionListener(this);
        removeUserBTN.addActionListener(this);

        userIDField.setBounds(80, 50, 200, 50);


        // adding elements to frame
        this.add(resultLabel);
        this.add(titleLabel);
        this.add(logoutBTN);
        this.add(removeUserBTN);
        this.add(userIDField);

        this.setTitle("Admin"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true); // set frame to visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == removeUserBTN){
            String userID = userIDField.getText();
            boolean removed = uiController.getAdminUIControl().removeUser(userID);
            if(removed){
                resultLabel.setForeground(Color.GREEN);
                resultLabel.setText("Successful");
            }else {
                resultLabel.setForeground(Color.RED);
                resultLabel.setText("Failed");
            }
            userIDField.setText("");
        }

    }
}

