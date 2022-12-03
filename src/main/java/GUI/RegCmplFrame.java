package GUI;

import UIController.UIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegCmplFrame extends JFrame implements ActionListener {
    JLabel titleLabel = new JLabel("Your Registration is Complete!");
    JButton returnBTN = new JButton("Return to Login Page");

    UIController uiController;
    public RegCmplFrame(UIController uiController){
        this.uiController = uiController;
        // setting labels
        titleLabel.setBounds(30, 20, 300, 50);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        // setting buttons
        returnBTN.setBounds(67, 150, 200, 20);
        returnBTN.addActionListener(this);

        // adding elements to the frame
        this.add(titleLabel);
        this.add(returnBTN);

        this.setTitle("Registration Complete"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnBTN){
            this.dispose();
            uiController.toLogin();

        }
    }
}
