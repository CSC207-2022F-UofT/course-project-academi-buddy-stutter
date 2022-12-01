package GUI;

import UIController.UIController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LabelSelectFrame extends JFrame implements ActionListener, ChangeListener {


    JLabel LabelSelectLabel = new JLabel("Select label:");

    ArrayList<JCheckBox> boxList = new ArrayList<>();

    JCheckBox meetCB = new JCheckBox("Want to Meet");
    JCheckBox collaborateCB = new JCheckBox("Want to Collaborate");
    JCheckBox discussCB = new JCheckBox("Want to Discuss");

    JButton backBTN = new JButton("Back");
    JButton applyBTN = new JButton("Apply");

    UIController uiController;
    private ArrayList<Boolean> initialState = new ArrayList<Boolean>();

    public LabelSelectFrame(UIController uiController){
        this.uiController = uiController;
        this.setTitle("Status Label Selection"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
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
            box.setSelected(uiController.getLabelSelectUIControl().getStudentLabelState(box.getText()));
            initialState.add(uiController.getLabelSelectUIControl().getStudentLabelState(box.getText()));
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


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == applyBTN){
            ArrayList<Boolean> newState = new ArrayList<>();
            for (JCheckBox box: boxList){
                uiController.getLabelSelectUIControl().updateStudentLabel(box.getText(), box.isSelected());
                newState.add(box.isSelected());
            }
            initialState = newState;
            applyBTN.setEnabled(false);
        }
        else if(e.getSource() == backBTN){
            this.dispose();
        }
    }

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