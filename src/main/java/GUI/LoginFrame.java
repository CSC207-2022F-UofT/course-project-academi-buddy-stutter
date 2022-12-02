package GUI;

/*
This class is to generate a LoginManager UI for the user to log in or register an account.
The user can select their user type from the combobox that contains two options: {Administer, Student}.
The user need to enter their user ID, which is the UTORID
The user need to enter their account password to login or register
The user need to enter their email for other people to contact them, when you are recommended as a study partner to
someone else, they can contact you by email.
There are two buttons at the bottom, one called "LoginManager" and the other called "Register", both buttons will bring the
user to the home page once the user has successfully registered or login (correct user ID and password are entered).
 */

import UIController.UIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class LoginFrame extends JFrame implements ActionListener, KeyListener {

    JLabel programTitle = new JLabel("STUDY BUDDY FINDER");
    JLabel userLabel = new JLabel("USER ID:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel errorLabel = new JLabel("Error: Incorrect login info");
    JButton loginBTN = new JButton("LOGIN");
    JButton registerBTN = new JButton("REGISTER");
    JTextField userIDText = new JTextField("");
    JPasswordField passwordText = new JPasswordField("");

    UIController uiController;

    public LoginFrame(UIController uiController){
        this.uiController = uiController;

        this.setTitle("Login"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor


        // places objects inside frame
        // buttons
        loginBTN.setBounds(60, 160, 100, 20);
        loginBTN.addActionListener(this);
        loginBTN.setFocusable(false);
        loginBTN.setEnabled(false);
        registerBTN.setBounds(180, 160, 100, 20);
        registerBTN.addActionListener(this);
        registerBTN.setFocusable(false);

        // labels
        userLabel.setBounds(10,75,100,20);
        passwordLabel.setBounds(10,100,100,20);
        programTitle.setBounds(60, 0, 300, 50);
        programTitle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        errorLabel.setBounds(110, 120,300,20);
        errorLabel.setVisible(false);
        errorLabel.setForeground(Color.red);

        // textfields
        userIDText.setBounds(110, 75, 200, 20);
        passwordText.setBounds(110, 100, 200, 20);
        userIDText.addKeyListener(this);
        passwordText.addKeyListener(this);

        // combobox

        // adds objects to the frame
        this.add(loginBTN);
        this.add(registerBTN);
        this.add(userLabel);
        this.add(passwordLabel);
        this.add(errorLabel);
        this.add(programTitle);
        this.add(userIDText);
        this.add(passwordText);

        this.setVisible(true); // set frame to visible
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBTN){
            try {

                String id = userIDText.getText();
                String password = new String(passwordText.getPassword());


                if(this.uiController.getLoginUIControl().attemptLogin(id, password)){
                    this.dispose();
                    this.uiController.updateUser();
                    this.uiController.toHome();
                }
                else{
                    this.errorLabel.setVisible(true);
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (e.getSource() == registerBTN){
            this.dispose();
            this.uiController.toRegister();
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        loginBTN.setEnabled(!userIDText.getText().equals("") && !new String(passwordText.getPassword()).equals(""));
    }
}