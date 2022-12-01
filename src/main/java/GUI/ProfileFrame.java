package GUI;

import UIController.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileFrame extends JFrame implements ActionListener {
    JLabel nameLabel = new JLabel("Name:");
    JLabel emailLabel = new JLabel("Email:");
    JLabel classLabel = new JLabel("Enrolled Courses:");
    JLabel infoLabel = new JLabel("About:");

    // creating text fields
    JTextField nameText = new JTextField();
    JTextField emailText = new JTextField();

    // creating textarea
    JTextArea infoText = new JTextArea();

    JTextArea courseText = new JTextArea(10, 10);
    JScrollPane courseTextScoll = new JScrollPane(courseText);
    UIController uiController;

    JButton backBTN = new JButton("Back");

    public ProfileFrame(UIController uiController){
        this.uiController = uiController;
        // Labels
        nameLabel.setBounds(10, 10, 100, 20);
        emailLabel.setBounds(10, 35, 100, 20);
        classLabel.setBounds(10, 60, 150, 20);
        infoLabel.setBounds(10, 180, 100, 20);

        nameText.setBounds(130, 10, 200, 20);
        emailText.setBounds(130, 35, 200, 20);
        courseTextScoll.setBounds(130,65, 200, 110);
        infoText.setBounds(130, 180, 200, 40);
        courseText.setEditable(false);
        nameText.setEditable(false);
        emailText.setEditable(false);
        infoText.setEditable(false);
        nameText.setText(uiController.getProfileUIControl().getName());
        emailText.setText(uiController.getProfileUIControl().getEmail());
        courseText.setText(uiController.getProfileUIControl().getCourse());
        infoText.setText(uiController.getProfileUIControl().getInfo());
        courseTextScoll.setViewportView(courseText);
        courseTextScoll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        courseTextScoll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        backBTN.setBounds(345, 230, 80, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);

        // adding elements to frame
        this.add(nameLabel);
        this.add(emailLabel);
        this.add(classLabel);
        this.add(infoLabel);


        this.add(nameText);
        this.add(emailText);
        this.add(infoText);

        this.getContentPane().add(courseTextScoll);

        this.add(backBTN);



        this.setTitle("File Upload Frame"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(440, 300);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);
    }





    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
