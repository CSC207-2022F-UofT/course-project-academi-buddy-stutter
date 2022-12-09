package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class informs the user that he/she has successfully registered an account.
 */

public class RegCmplFrame extends JFrame implements ActionListener {
    final JLabel titleLabel = new JLabel("Your Registration is Complete!");
    final JButton returnBTN = new JButton("Return to Login Page");

    final FrameNavigator frameNavigator;
    public RegCmplFrame(FrameNavigator frameNavigator){
        this.frameNavigator = frameNavigator;
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

    /**
     * Takes user to LoginFrame when "Return" button is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnBTN){
            this.dispose();
            frameNavigator.toLogin();
        }
    }
}
