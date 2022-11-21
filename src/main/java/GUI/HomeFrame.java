package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFrame extends JFrame implements ActionListener {
    JLabel fileLabel = new JLabel("Select Your ICS File:");
    JLabel findLabel = new JLabel("Click to Find Your Study Buddies!");
    JButton fileBTN = new JButton("FILE");
    JButton findBTN = new JButton("FIND");
    JButton returnBTN = new JButton("Return to Login Screen");
    JTextArea nameList = new JTextArea();

    HomeFrame(){
        // setting up labels
        fileLabel.setBounds(10, 10, 125, 20);
        findLabel.setBounds(10, 35, 210, 20);

        // setting up textarea
        nameList.setBounds(10, 60, 320, 100);
        nameList.setEditable(false);

        // setting up buttons
        fileBTN.setBounds(140, 10, 50, 20);
        fileBTN.setFocusable(false);
        findBTN.setBounds(225, 35, 50, 20);
        findBTN.setFocusable(false);
        returnBTN.setBounds(65, 170, 200, 20);
        returnBTN.setFocusable(false);
        returnBTN.addActionListener(this);

        // adding components to the frame
        this.add(fileLabel);
        this.add(fileBTN);
        this.add(nameList);
        this.add(findLabel);
        this.add(findBTN);
        this.add(returnBTN);

        this.setTitle("Home Frame"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true); // set frame to visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnBTN){
            this.dispose();
            LoginFrame loginFrame = new LoginFrame();
        }
    }
}

