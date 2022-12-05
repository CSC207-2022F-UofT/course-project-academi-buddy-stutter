package views;

import controllers.UIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This class implements the RegisterFrame that allows user to enter:
 * - full name
 * - user id
 * - password
 * - confirm password
 */
public class RegisterFrame extends JFrame implements ActionListener {
    // creating labels
    JLabel regTitleLabel = new JLabel("Registration");
    JLabel fullNameLabel = new JLabel("Full Name:");
    JLabel userIDLabel = new JLabel("User ID:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel confirmLabel = new JLabel("Confirm Password:");
    JLabel errorLabel = new JLabel("Error: User Exists/Passwords Doesn't Match");

    JLabel strengthLabel = new JLabel();

    // creating textfields
    JTextField fullNameText = new JTextField();
    JTextField userIDText = new JTextField();
    JPasswordField passwordText = new JPasswordField();
    JPasswordField confirmText = new JPasswordField();

    // creating button
    JButton registerBTN = new JButton("Next");
    JButton cancelBTN = new JButton("Cancel");

    Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);

    UIController uiController;

    /**
     * This method implements all UI components for RegisterFrame.
     */
    public RegisterFrame(UIController uiController){
        this.uiController = uiController;

        // setting up Labels
        regTitleLabel.setBounds(120, 0, 300, 30);
        regTitleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        fullNameLabel.setBounds(10, 35, 80, 20);
        userIDLabel.setBounds(10, 60, 50, 20);
        passwordLabel.setBounds(10, 85, 70, 20);
        confirmLabel.setBounds(10, 110, 120, 20);
        errorLabel.setBounds(10,130,300,20);
        errorLabel.setForeground(Color.red);
        errorLabel.setVisible(false);
        strengthLabel.setBounds(130,130,300,50);
        strengthLabel.setForeground(Color.red);

        // setting up textfields
        fullNameText.setBounds(130, 35, 200, 20);
        userIDText.setBounds(130, 60, 200, 20);
        passwordText.setBounds(130, 85, 200, 20);
        confirmText.setBounds(130, 110, 200, 20);

        // setting up buttons
        registerBTN.setBounds(70, 200, 100, 20);
        registerBTN.addActionListener(this);
        cancelBTN.setBounds(180, 200, 100, 20);
        cancelBTN.addActionListener(this);

        // adding elements to Frame
        this.add(regTitleLabel);
        this.add(fullNameLabel);
        this.add(userIDLabel);
        this.add(passwordLabel);
        this.add(errorLabel);
        this.add(confirmLabel);
        this.add(fullNameText);
        this.add(userIDText);
        this.add(passwordText);
        this.add(confirmText);
        this.add(registerBTN);
        this.add(cancelBTN);
        this.add(strengthLabel);

        this.setTitle("Account Register"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(360, 280);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true); // set frame to visible
    }

    /**
     * Gives user a warning when user id already exists.
     * Gives user a warning when password is not long enough
     * Gives user a warning when password does not contain digits
     * Gives user a warning when password does not contain letters
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == registerBTN){
            try {

                String fullName = fullNameText.getText();
                String id = userIDText.getText();
                String password = new String(passwordText.getPassword());
                String confirm = new String(confirmText.getPassword());
                this.setCursor(waitCursor);
                boolean registered = uiController.getRegisterUIControl().attemptRegister(fullName, id, password, confirm);
                this.strengthLabel.setText(uiController.getRegisterUIControl().getWarningString(password));
                int strength = uiController.getRegisterUIControl().getWarningString(password).length();
                if(registered){
                    this.uiController.toRegisterProfile();
                    this.dispose();
                    this.uiController.getLoginUIControl().attemptLogin(id, password);
                    this.uiController.updateUser();
                }
                else if (strength == 23){
                    this.errorLabel.setVisible(true);
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            this.setCursor(Cursor.getDefaultCursor());
        }

        if (e.getSource() == cancelBTN){
            this.dispose();
            this.uiController.toLogin();
        }

    }
}
