package views;

import javax.swing.*;
import java.awt.event.*;

/**
 * Implements JFrame that shows common courses between 2 registered users
 */
public class CommonSessionFrame extends JFrame implements ActionListener{

    final JLabel numberOfCommonSessionLabel = new JLabel("");
    final JTextArea courseText = new JTextArea(10, 10);
    final JScrollPane courseTextScoll = new JScrollPane(courseText);
    final JButton backBTN = new JButton("Back");


    final int numberOfCommonSession;

    /**
     * constructs all UI components
     * @param targetUserID user id of the target user we want to compare
     */
    public CommonSessionFrame(FrameNavigator frameNavigator, String targetUserID) {
        // Labels
        numberOfCommonSessionLabel.setBounds(30,180, 300, 20);

        courseTextScoll.setBounds(10,20, 280, 150);
        courseText.setEditable(false);
        courseText.setText(frameNavigator.getCommonSessionUIPresenter().getCommonSessions(targetUserID));
        numberOfCommonSession = frameNavigator.getCommonSessionUIPresenter().getNumberOfCommonSessions();

        courseTextScoll.setViewportView(courseText);
        courseTextScoll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        courseTextScoll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        numberOfCommonSessionLabel.setText("You have " + numberOfCommonSession + " sessions in common.");


        backBTN.setBounds(305, 230, 80, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);


        // adding elements to frame
        this.add(numberOfCommonSessionLabel);
        this.getContentPane().add(courseTextScoll);
        this.add(backBTN);

        this.setTitle("Your Common Sessions with " + frameNavigator.getCommonSessionUIPresenter().getName(targetUserID)); // sets frame's title
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // closes the frame
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
