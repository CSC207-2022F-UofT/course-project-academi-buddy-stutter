package GUI;

import UIController.UIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RegisterFrame extends JFrame implements ActionListener {
    // creating labels
    JLabel regTitleLabel = new JLabel("Registration");
    JLabel fullNameLabel = new JLabel("Full Name:");
    JLabel userIDLabel = new JLabel("User ID:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel confirmLabel = new JLabel("Confirm Password:");

    // creating textfields
    JTextField fullNameText = new JTextField();
    JTextField userIDText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    JPasswordField confirmText = new JPasswordField();

    // creating button
    JButton registerBTN = new JButton("Register");
    JButton cancelBTN = new JButton("Cancel");

    UIController uiController;
    public RegisterFrame(UIController uiController){
        this.uiController = uiController;

        // setting up Labels
        regTitleLabel.setBounds(120, 0, 300, 30);
        regTitleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        fullNameLabel.setBounds(10, 35, 80, 20);
        userIDLabel.setBounds(10, 60, 50, 20);
        passwordLabel.setBounds(10, 85, 70, 20);
        confirmLabel.setBounds(10, 110, 120, 20);

        // setting up textfields
        fullNameText.setBounds(130, 35, 200, 20);
        userIDText.setBounds(130, 60, 200, 20);
        passwordText.setBounds(130, 85, 200, 20);
        confirmText.setBounds(130, 110, 200, 20);

        // setting up buttons
        registerBTN.setBounds(120, 145, 100, 20);
        registerBTN.addActionListener(this);
        cancelBTN.setBounds(230, 145, 100, 20);
        cancelBTN.addActionListener(this);

        // adding elements to Frame
        this.add(regTitleLabel);
        this.add(fullNameLabel);
        this.add(userIDLabel);
        this.add(passwordLabel);
        this.add(confirmLabel);
        this.add(fullNameText);
        this.add(userIDText);
        this.add(passwordText);
        this.add(confirmText);
        this.add(registerBTN);
        this.add(cancelBTN);

        this.setTitle("Register Frame"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 225);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true); // set frame to visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == registerBTN){
            try {

                String fullName = fullNameText.getText();
                String id = userIDText.getText();
                String password = new String(passwordText.getPassword());
                String confirm = new String(confirmText.getPassword());


                if(this.uiController.registerUIControl.attemptRegister(fullName, id, password, confirm)){
                    this.dispose();
                    this.uiController.toLogin();
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (e.getSource() == cancelBTN){
            this.dispose();
            this.uiController.toLogin();
        }

    }
}
