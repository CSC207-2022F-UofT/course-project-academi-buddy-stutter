package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame implements ActionListener {
    // creating labels
    JLabel regTitleLabel = new JLabel("Registration");
    JLabel userIDLabel = new JLabel("User ID:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel confirmLabel = new JLabel("Confirm Password:");

    // creating textfields
    JTextField userIDText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    JPasswordField confirmText = new JPasswordField();

    // creating button
    JButton registerBTN = new JButton("Register");
    public RegisterFrame(){
        // setting up Labels
        regTitleLabel.setBounds(120, 0, 300, 30);
        regTitleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        userIDLabel.setBounds(10, 35, 50, 20);
        passwordLabel.setBounds(10, 60, 70, 20);
        confirmLabel.setBounds(10, 85, 120, 20);

        // setting up textfields
        userIDText.setBounds(130, 35, 200, 20);
        passwordText.setBounds(130, 60, 200, 20);
        confirmText.setBounds(130, 85, 200, 20);

        // setting up buttons
        registerBTN.setBounds(120, 120, 100, 20);
        registerBTN.addActionListener(this);

        // adding elements to Frame
        this.add(regTitleLabel);
        this.add(userIDLabel);
        this.add(passwordLabel);
        this.add(confirmLabel);
        this.add(userIDText);
        this.add(passwordText);
        this.add(confirmText);
        this.add(registerBTN);

        this.setTitle("Register Frame"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 200);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true); // set frame to visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
