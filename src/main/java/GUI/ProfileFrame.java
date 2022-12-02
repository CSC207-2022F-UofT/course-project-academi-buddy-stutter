package GUI;

import UIController.UIController;

import javax.swing.*;
import java.awt.event.*;

public class ProfileFrame extends JFrame implements ActionListener, MouseListener {
    JLabel nameLabel = new JLabel("Name:");
    JLabel emailLabel = new JLabel("Email:");
    JLabel classLabel = new JLabel("Enrolled Courses:");
    JLabel infoLabel = new JLabel("About:");

    // creating textfields
    JTextField nameText = new JTextField();
    JTextField emailText = new JTextField();

    // creating textarea
    JTextField infoText = new JTextField();

    JTextArea courseText = new JTextArea(10, 10);
    JScrollPane courseTextScoll = new JScrollPane(courseText);
    UIController uiController;

    JButton changeEmail = new JButton("Change");
    JButton changeInfo = new JButton("Change");
    JButton backBTN = new JButton("Back");

    JButton updateCourse = new JButton("Reupload");

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
        emailText.setEditable(true);
        infoText.setEditable(true);
        nameText.setText(uiController.getProfileUIControl().getName());
        emailText.setText(uiController.getProfileUIControl().getEmail());
        courseText.setText(uiController.getProfileUIControl().getCourse());
        infoText.setText(uiController.getProfileUIControl().getInfo());
        courseTextScoll.setViewportView(courseText);
        courseTextScoll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        courseTextScoll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        emailText.addMouseListener(this);
        infoText.addMouseListener(this);
        updateCourse.addActionListener(this);


        backBTN.setBounds(345, 230, 80, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);
        changeEmail.setBounds(340, 35, 80, 20);
        changeEmail.addActionListener(this);
        changeEmail.setEnabled(false);
        changeEmail.setFocusable(false);
        changeInfo.setBounds(345, 180, 80, 20);
        changeInfo.setEnabled(false);
        updateCourse.setBounds(345, 65, 80, 20);
        updateCourse.setEnabled(true);
        updateCourse.setFocusable(false);

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
        this.add(changeEmail);
        this.add(changeInfo);
        this.add(updateCourse);



        this.setTitle("Profile"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(440, 300);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBTN){
            this.dispose();
        } else if (e.getSource() == changeEmail) {
            uiController.getProfileUIControl().updateEmail(emailText.getText());
            changeEmail.setEnabled(false);
        }
        else if (e.getSource() == changeInfo) {
            uiController.getProfileUIControl().updateInfo(infoText.getText());
            changeInfo.setEnabled(false);
        }
        else if (e.getSource() == updateCourse) {
            uiController.toFileUpload();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == emailText) {
            changeEmail.setEnabled(true);

        } else if (e.getSource() == infoText) {
            changeInfo.setEnabled(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
