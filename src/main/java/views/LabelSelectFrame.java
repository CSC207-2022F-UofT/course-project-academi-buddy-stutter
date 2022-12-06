package views;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class implements LabelSelectFrame that allows the user to select labels from:
 * - Want to Meet
 * - Want to Collaborate
 * - Want to Discuss
 */
public class LabelSelectFrame extends JFrame implements ActionListener, ChangeListener {


    JLabel LabelSelectLabel = new JLabel("Select label:");

    ArrayList<JCheckBox> boxList = new ArrayList<>();

    JCheckBox meetCB = new JCheckBox("Want to Meet");
    JCheckBox collaborateCB = new JCheckBox("Want to Collaborate");
    JCheckBox discussCB = new JCheckBox("Want to Discuss");

    JButton backBTN = new JButton("Back");
    JButton applyBTN = new JButton("Apply");

    FrameNavigator frameNavigator;
    private ArrayList<Boolean> initialState = new ArrayList<Boolean>();
    Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);

    /**
     * This constructor method implements all UI components for LabelSelectFrame
     */
    public LabelSelectFrame(FrameNavigator frameNavigator) throws IOException {
        this.frameNavigator = frameNavigator;
        this.setTitle("Status Label Selection"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        // places objects inside frame
        // buttons
        backBTN.setBounds(260, 170, 70, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);
        applyBTN.setBounds(115, 130, 100, 20);
        applyBTN.addActionListener(this);
        applyBTN.setFocusable(false);
        applyBTN.setEnabled(false);

        //checkbox
        meetCB.setBounds(80, 20, 250,50);
        collaborateCB.setBounds(80, 50, 250, 50);
        discussCB.setBounds(80, 80, 250, 50);
        boxList.add(meetCB);
        boxList.add(collaborateCB);
        boxList.add(discussCB);
        for(JCheckBox box: boxList){
            box.setSelected(frameNavigator.getLabelSelectPresenter().getStudentLabelState(box.getText()));
            initialState.add(frameNavigator.getLabelSelectPresenter().getStudentLabelState(box.getText()));
        }
        System.out.println(initialState);
        meetCB.addChangeListener(this);
        collaborateCB.addChangeListener(this);
        discussCB.addChangeListener(this);

        // labels
        LabelSelectLabel.setBounds(10,10,100,20);

        // adds objects to the frame
        this.add(backBTN);
        this.add(applyBTN);

        this.add(meetCB);
        this.add(collaborateCB);
        this.add(discussCB);


        this.add(LabelSelectLabel);

        this.setVisible(true); // set frame to visible
    }

    /**
     * When the "Apply" button is clicked, the program will access database and updates user's profile with selected
     * labels. User can select multiple labels and updates them to their profile.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == applyBTN){
            this.setCursor(waitCursor);
            ArrayList<Boolean> newState = new ArrayList<>();
            for (JCheckBox box: boxList){
                try {
                    frameNavigator.getLabelSelectPresenter().updateStudentLabel(box.getText(), box.isSelected());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                newState.add(box.isSelected());
            }
            initialState = newState;
            applyBTN.setEnabled(false);
            this.setCursor(Cursor.getDefaultCursor());
        }
        else if(e.getSource() == backBTN){
            this.dispose();
        }
    }

    /**
     * This method makes sure the user has changed the labels, otherwise the "Apply" will be disabled.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        int count = 0;
        boolean enable = false;
        for(JCheckBox box: boxList){
            if(initialState.get(count) != box.isSelected()){
                enable = true;
            }
            count += 1;
        }
        applyBTN.setEnabled(enable);

    }
}