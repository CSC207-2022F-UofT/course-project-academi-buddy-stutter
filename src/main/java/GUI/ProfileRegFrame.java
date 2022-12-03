package GUI;

import UIController.UIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileRegFrame extends JFrame implements ActionListener {
    // creating labels
    JLabel proTitleLabel = new JLabel("Profile");
    JLabel emailLabel = new JLabel("UofT Email:");
    JLabel infoLabel = new JLabel("About:");

    // creating textfields
    JTextField emailText = new JTextField();

    // creating textarea
    JTextArea infoText = new JTextArea();
    JButton completeBTN = new JButton("Next");

    UIController uiController;

    public ProfileRegFrame(UIController uiController){
        this.uiController = uiController;
        // setting up labels
        proTitleLabel.setBounds(140, 0, 300, 30);
        proTitleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        emailLabel.setBounds(10, 55, 100, 20);
        infoLabel.setBounds(10, 80, 100, 20);

        // setting up textfields
        emailText.setBounds(110, 55, 200, 20);
        infoText.setBounds(110, 80, 200, 80);

        // setting up buttons
        completeBTN.setBounds(95, 170, 150, 20 );
        completeBTN.addActionListener(this);

        // adding elements in frame
        this.add(proTitleLabel);
        this.add(emailLabel);
        this.add(infoLabel);
        this.add(emailText);
        this.add(infoText);
        this.add(completeBTN);

        this.setTitle("Complete Profile Register"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true); // set frame to visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == completeBTN){
            uiController.getRegisterUIControl().updateEmailAndInfo(emailText.getText(), infoText.getText());
            this.dispose();
            uiController.toCalendarUpload();
        }
    }
}