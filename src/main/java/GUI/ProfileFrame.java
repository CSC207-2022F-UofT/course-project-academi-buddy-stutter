package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileFrame extends JFrame implements ActionListener {
    // creating labels
    JLabel proTitleLabel = new JLabel("Profile");
    JLabel nameLabel = new JLabel("Full Name:");
    JLabel emailLabel = new JLabel("UofT Email:");
    JLabel infoLabel = new JLabel("About:");

    // creating textfields
    JTextField nameText = new JTextField();
    JTextField emailText = new JTextField();

    // creating textarea
    JTextArea infoText = new JTextArea();
    JButton completeBTN = new JButton("Complete Profile");

    public ProfileFrame(){
        // setting up labels
        proTitleLabel.setBounds(140, 0, 300, 30);
        proTitleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        nameLabel.setBounds(10, 30, 100, 20);
        emailLabel.setBounds(10, 55, 100, 20);
        infoLabel.setBounds(10, 80, 100, 20);

        // setting up textfields
        nameText.setBounds(110, 30, 200, 20);
        emailText.setBounds(110, 55, 200, 20);
        infoText.setBounds(110, 80, 200, 80);

        // setting up buttons
        completeBTN.setBounds(95, 170, 150, 20 );
        completeBTN.addActionListener(this);

        // adding elements in frame
        this.add(proTitleLabel);
        this.add(nameLabel);
        this.add(emailLabel);
        this.add(infoLabel);
        this.add(nameText);
        this.add(emailText);
        this.add(infoText);
        this.add(completeBTN);

        this.setTitle("Profile Frame"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true); // set frame to visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
