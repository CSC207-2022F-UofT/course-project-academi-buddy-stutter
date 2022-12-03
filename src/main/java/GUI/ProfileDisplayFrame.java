package GUI;

import UIController.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileDisplayFrame extends JFrame implements ActionListener {
    JLabel nameLabel = new JLabel("Name:");
    JLabel emailLabel = new JLabel("Email:");
    JLabel infoLabel = new JLabel("About:");

    // creating textfields
    JTextField nameText = new JTextField();
    JTextField emailText = new JTextField();

    // creating textarea
    JTextArea infoText = new JTextArea();
    UIController uiController;

    JButton closeBTN = new JButton("Close");

    String userID;

    public ProfileDisplayFrame(UIController uiController, String userID){
        this.uiController = uiController;
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
        nameText.setText(uiController.getProfileDisplayUIControl().getName(userID));
        emailText.setText(uiController.getProfileDisplayUIControl().getEmail(userID));
        infoText.setText(uiController.getProfileDisplayUIControl().getInfo(userID));

        closeBTN.setBounds(330, 165, 50, 20);
        closeBTN.addActionListener(this);
        closeBTN.setFocusable(false);

        // adding elements to frame
        this.add(nameLabel);
        this.add(emailLabel);
        this.add(infoLabel);

        this.add(nameText);
        this.add(emailText);
        this.add(infoText);

        this.add(closeBTN);


        this.setTitle(uiController.getProfileDisplayUIControl().getName(userID)+ "'s Profile"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(400, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == closeBTN){
            this.dispose();
        }
    }
}
