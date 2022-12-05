package views;

import controllers.UIController;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Implements JFrame that shows common courses between 2 registered users
 */
public class CommonSessionFrame extends JFrame implements ActionListener{

    JLabel numberOfCommonSessionLabel = new JLabel("");
    JTextArea courseText = new JTextArea(10, 10);
    JScrollPane courseTextScoll = new JScrollPane(courseText);
    UIController uiController;
    JButton backBTN = new JButton("Back");

    String targetUserID;

    int numberOfCommonSession;

    /**
     * constructs all UI components
     * @param targetUserID user id of the target user we want to compare
     */
    public CommonSessionFrame(UIController uiController, String targetUserID) throws IOException {
        this.uiController = uiController;
        this.targetUserID = targetUserID;
        // Labels
        numberOfCommonSessionLabel.setBounds(30,180, 300, 20);

        courseTextScoll.setBounds(10,20, 280, 150);
        courseText.setEditable(false);
        courseText.setText(uiController.getCommonSessionUIControl().getCommonSessions(targetUserID));
        numberOfCommonSession = uiController.getCommonSessionUIControl().getNumberOfCommonSessions();

        courseTextScoll.setViewportView(courseText);
        courseTextScoll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        courseTextScoll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        numberOfCommonSessionLabel.setText("You have " + numberOfCommonSession + " sessions in common.");


        backBTN.setBounds(305, 230, 80, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);


        // adding elements to frame
        this.add(numberOfCommonSessionLabel);
        this.getContentPane().add(courseTextScoll);
        this.add(backBTN);

        this.setTitle("Your Common Sessions with " + uiController.getCommonSessionUIControl().getName(targetUserID)); // sets frame's title
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(400, 290);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);
    }


    /**
     * closes the JFrame
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBTN){
            this.dispose();
        }
    }

}
