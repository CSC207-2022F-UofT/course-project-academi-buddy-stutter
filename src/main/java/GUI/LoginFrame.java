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

import com.sun.tools.jconsole.JConsoleContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel programTitle = new JLabel("STUDY BUDDY FINDER");
    JLabel userLabel = new JLabel("USER ID:");
    JLabel passwordLabel = new JLabel("Password:");
    JButton loginBTN = new JButton("LOGIN");
    JButton registerBTN = new JButton("REGISTER");
    JTextField userIDText = new JTextField("Enter your user ID here:");
    // JTextField passwordText = new JTextField("Enter your password here:");
    JPasswordField passwordText = new JPasswordField();

    UIController uiController;

    public LoginFrame(UIController uiController){
        this.uiController = uiController;

        this.setTitle("LoginManager Frame"); // sets frame's title
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
        registerBTN.setBounds(180, 160, 100, 20);
        registerBTN.addActionListener(this);
        registerBTN.setFocusable(false);

        // labels
        userLabel.setBounds(10,75,100,20);
        passwordLabel.setBounds(10,100,100,20);
        programTitle.setBounds(60, 0, 300, 50);
        programTitle.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        // textfields
        userIDText.setBounds(110, 75, 200, 20);
        passwordText.setBounds(110, 100, 200, 20);

        // combobox

        // adds objects to the frame
        this.add(loginBTN);
        this.add(registerBTN);
        this.add(userLabel);
        this.add(passwordLabel);



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


                if(this.uiController.attemptLogin(id, password)){
                    this.dispose();
                    HomeFrame homeFrame = new HomeFrame();
                }else{
                    System.out.println("wrong password!");
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }


    }
}