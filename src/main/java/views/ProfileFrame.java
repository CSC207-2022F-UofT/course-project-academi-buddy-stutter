package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * This class implements ProfileFrame that allows user to view and modify personal information.
 */
public class ProfileFrame extends JFrame implements ActionListener, MouseListener, KeyListener {
    JLabel nameLabel = new JLabel("Name:");
    JLabel emailLabel = new JLabel("Email:");
    JLabel classLabel = new JLabel("Enrolled Courses:");
    JLabel infoLabel = new JLabel("About:");

    // creating textfields
    JTextField nameText = new JTextField();
    JTextField emailText = new JTextField();

    // creating textarea
    JTextArea infoText = new JTextArea();

    JTextArea courseText = new JTextArea(10, 10);
    JScrollPane courseTextScoll = new JScrollPane(courseText);
    FrameNavigator frameNavigator;

    JButton changeEmail = new JButton("Change");
    JButton changeInfo = new JButton("Change");
    JButton backBTN = new JButton("Back");

    JButton updateCourse = new JButton("Reupload");


    String currentEmail;
    String currentInfo;

    /**
     * This constructor method implements all UI components for ProfileFrame.
     */
    public ProfileFrame(FrameNavigator frameNavigator) throws IOException {
        this.frameNavigator = frameNavigator;
        // Labels
        nameLabel.setBounds(10, 10, 100, 20);
        emailLabel.setBounds(10, 35, 100, 20);
        classLabel.setBounds(10, 60, 150, 20);
        infoLabel.setBounds(10, 180, 100, 20);

        nameText.setBounds(130, 10, 200, 20);
        emailText.setBounds(130, 35, 200, 20);
        courseTextScoll.setBounds(130,65, 200, 110);
        infoText.setBounds(130, 180, 200, 100);
        updateCourse.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        courseText.setEditable(false);
        nameText.setEditable(false);
        emailText.setEditable(true);
        infoText.setEditable(true);
        nameText.setText(frameNavigator.getProfileUIPresenter().getName());
        currentEmail = frameNavigator.getProfileUIPresenter().getEmail();
        if(currentEmail == null){
            currentEmail = "";
        }
        emailText.setText(currentEmail);
        currentInfo = frameNavigator.getProfileUIPresenter().getInfo();
        infoText.setText(currentInfo);
        courseText.setText(frameNavigator.getProfileUIPresenter().getCourse());
        courseTextScoll.setViewportView(courseText);
        courseTextScoll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        courseTextScoll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        emailText.addKeyListener(this);
        infoText.addKeyListener(this);


        backBTN.setBounds(345, 260, 80, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);
        changeEmail.setBounds(345, 35, 80, 20);
        changeEmail.addActionListener(this);
        changeEmail.setEnabled(false);
        changeEmail.setFocusable(false);
        changeInfo.setBounds(345, 180, 80, 20);
        changeInfo.setEnabled(false);
        changeInfo.addActionListener(this);
        updateCourse.setBounds(345, 65, 80, 20);
        updateCourse.setEnabled(true);
        updateCourse.setFocusable(false);
        updateCourse.addActionListener(this);


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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(460, 320);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);
    }


    /**
     * The "Change" button is only enabled when there is actual change to the text of Email and About.
     * User can reupload a calendar by clicking the "Reupload" button, this will take user to FileUploadFrame.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBTN){
            this.dispose();
        } else if (e.getSource() == changeEmail) {
            try {
                frameNavigator.getProfileUIPresenter().updateEmail(emailText.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            currentEmail = emailText.getText();
            changeEmail.setEnabled(false);
        }
        else if (e.getSource() == changeInfo) {
            System.out.println(infoText.getText() + currentInfo);
            try {
                frameNavigator.getProfileUIPresenter().updateInfo(infoText.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            currentInfo = infoText.getText();
            changeInfo.setEnabled(false);
        }
        else if (e.getSource() == updateCourse) {
            frameNavigator.toCalendarUpload();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == emailText){
            System.out.println(emailText.getText());
            System.out.println(currentEmail);
            if(!emailText.getText().equals(currentEmail)){
                changeEmail.setEnabled(true);
            }
            else{
                changeEmail.setEnabled(false);
            }
        } else if (e.getSource() == infoText) {
            if(!infoText.getText().equals(currentInfo)){
                changeInfo.setEnabled(true);
            }
            else{
                changeInfo.setEnabled(false);
            }
        }
    }
}
