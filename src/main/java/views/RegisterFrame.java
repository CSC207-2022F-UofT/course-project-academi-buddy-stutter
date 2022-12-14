package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class implements the RegisterFrame that allows user to enter:
 * - full name
 * - user id
 * - password
 * - confirm password
 */
public class RegisterFrame extends JFrame implements ActionListener {
    // creating labels
    final JLabel regTitleLabel = new JLabel("Registration");
    final JLabel fullNameLabel = new JLabel("Full Name:");
    final JLabel userIDLabel = new JLabel("User ID:");
    final JLabel passwordLabel = new JLabel("Password:");
    final JLabel confirmLabel = new JLabel("Confirm Password:");
    final JLabel errorLabel = new JLabel("Error: User Exists/Passwords Doesn't Match");

    final JLabel strengthLabel = new JLabel();

    // creating textfields
    final JTextField fullNameText = new JTextField();
    final JTextField userIDText = new JTextField();
    final JPasswordField passwordText = new JPasswordField();
    final JPasswordField confirmText = new JPasswordField();

    // creating button
    final JButton registerBTN = new JButton("Next");
    final JButton cancelBTN = new JButton("Cancel");

    final Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);

    final FrameNavigator frameNavigator;

    /**
     * This method implements all UI components for RegisterFrame.
     */
    public RegisterFrame(FrameNavigator frameNavigator){
        this.frameNavigator = frameNavigator;

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

            String fullName = fullNameText.getText();
            String id = userIDText.getText();
            String password = new String(passwordText.getPassword());
            String confirm = new String(confirmText.getPassword());
            this.setCursor(waitCursor);
            boolean registered = frameNavigator.getRegisterUIControl().attemptRegister(fullName, id, password, confirm);
            this.strengthLabel.setText(frameNavigator.getRegisterUIControl().getWarningString(password));
            int strength = frameNavigator.getRegisterUIControl().getWarningString(password).length();
            if(registered){
                this.frameNavigator.toRegisterProfile();
                this.dispose();
                this.frameNavigator.getLoginUIPresenter().attemptLogin(id, password);
                this.frameNavigator.updateUser();
            }
            else if (strength == 23){
                this.errorLabel.setVisible(true);
            }

            this.setCursor(Cursor.getDefaultCursor());
        }

        if (e.getSource() == cancelBTN){
            this.dispose();
            this.frameNavigator.toLogin();
        }

    }
}
