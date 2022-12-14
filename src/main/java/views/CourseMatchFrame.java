package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * This class implements MatchFrame that allows user to find study buddy through matching same courses.
 * User can select number of common sessions and desired match labels to find study buddies.
 */

public class CourseMatchFrame extends JFrame implements ActionListener, ItemListener{
    final JLabel numCommonLabel = new JLabel("Minimum Number of Common Sessions:");
    final JLabel selectLabel = new JLabel("Label:");
    final JLabel matchLabel = new JLabel("Matched Students:");
    final String[] userType = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    final JComboBox<String> numBox = new JComboBox<>(userType);
    final String[] labels = {"None", "Want to Meet", "Want to Collaborate", "Want to Discuss"};
    final JComboBox<String> labelBox = new JComboBox<>(labels);
    DefaultListModel<String> matchedStu = new DefaultListModel<>();
    final JList<String> matchedList = new JList<>(matchedStu);
    final JButton returnBTN = new JButton("Back");
    final JButton findBTN = new JButton("Find");
    final JButton profileBTN = new JButton("Go to Profile");

    final JButton commonSessionBTN = new JButton("Common Sessions");

    final Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);

    final FrameNavigator frameNavigator;
    /**
     * This constructor method implements all UI components for CourseMatchFrame.
     */
    public CourseMatchFrame(FrameNavigator frameNavigator) {
        this.frameNavigator = frameNavigator;
        // setting up labels:
        numCommonLabel.setBounds(10, 10, 290, 20);
        selectLabel.setBounds(10, 35, 100, 20);
        matchLabel.setBounds(10, 60, 120, 20);

        // setting up combobox for numSameSessions
        numBox.setEditable(false);
        numBox.setBounds(355, 10, 70, 25);

        // setting up combobox for labels
        labelBox.setEditable(false);
        labelBox.setBounds(210, 35, 215, 25);
        labelBox.addItemListener(this);

        // setting up buttons
        returnBTN.setBounds(410, 220, 70, 20);
        returnBTN.addActionListener(this);
        findBTN.setBounds(420, 10, 60, 20);
        findBTN.addActionListener(this);
        findBTN.setFocusable(false);

        profileBTN.setBounds(155, 190, 130, 20);
        profileBTN.addActionListener(this);
        profileBTN.setFocusable(false);
        profileBTN.setEnabled(false);

        commonSessionBTN.setBounds(290, 190, 150, 20);
        commonSessionBTN.setFocusable(false);
        commonSessionBTN.addActionListener(this);
        commonSessionBTN.setEnabled(false);


        // setting up textareas
        matchedList.setBounds(155, 60, 265, 120);
        matchedList.addListSelectionListener(e -> {
            if(matchedList.isSelectionEmpty()){
                profileBTN.setEnabled(false);
            }
            profileBTN.setEnabled(true);
            commonSessionBTN.setEnabled(true);
        });

        // adding elements to frame
        this.add(numCommonLabel);
        this.add(selectLabel);
        this.add(matchLabel);

        this.add(matchedList);

        this.add(numBox);
        this.add(labelBox);
        this.add(returnBTN);
        this.add(findBTN);
        this.add(profileBTN);
        this.add(commonSessionBTN);

        this.setTitle("Match by Course"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(510, 280);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);
    }

    /**
     * This method appends matched study buddies to the JList, displaying their full names instead of User ID.
     */
    private void addMatches(){
//        clearMatches();
        matchedList.setModel(matchedStu);
        matchedList.clearSelection();
        profileBTN.setEnabled(false);
        commonSessionBTN.setEnabled(false);
    }

    /**
     * This method clears all text inside the JList.
     */
    private void clearMatches(){
        matchedStu = new DefaultListModel<>();
        matchedList.setModel(matchedStu);
    }

    /**
     * When the "Find" button is clicked, this method calls corresponding controller to access the matching algorithm,
     * then displays the matched study buddies to JList.
     * User can also see matched study buddies' profile by highlighting their names and click the "Profile" button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == findBTN){
            this.setCursor(waitCursor);
            int numCommon = numBox.getSelectedIndex();

            matchedStu = frameNavigator.getMatchUIPresenter().createModelByLabel((String) labelBox.getSelectedItem(), numCommon + 1);//numbox index starts at 0.

            addMatches();
            this.setCursor(Cursor.getDefaultCursor());
        }

        else if (e.getSource() == profileBTN){
            this.setCursor(waitCursor);
            if(matchedList.getSelectedIndex() != -1){
                String selectedID = frameNavigator.getMatchUIPresenter().getSelectedUserID(matchedList.getSelectedIndex());
                frameNavigator.toProfileDisplay(selectedID);
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
        else if (e.getSource() == commonSessionBTN) {
            this.setCursor(waitCursor);
            String selectedID = frameNavigator.getMatchUIPresenter().getSelectedUserID(matchedList.getSelectedIndex());
            frameNavigator.toCommonSession(selectedID);
            this.setCursor(Cursor.getDefaultCursor());

        }
        if(e.getSource() == returnBTN){
            this.dispose();
        }
    }

    /**
     * This method applies filtering to the matching algorithm so that it only searches students that satisfy both
     * conditions. [Number of common sessions and Same Labels]
     */
    @Override
    public void itemStateChanged(ItemEvent e) {

        if(e.getSource() == labelBox){

            int numCommon = numBox.getSelectedIndex();

            matchedStu = frameNavigator.getMatchUIPresenter().createModelByLabel((String) labelBox.getSelectedItem(), numCommon);
            addMatches();

            if(matchedStu.size() == 0){
                profileBTN.setEnabled(false);
                commonSessionBTN.setEnabled(false);
            }
        }
    }
}
