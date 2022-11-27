package GUI;

import UIController.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LabelSelectFrame extends JFrame implements ActionListener{


    JLabel LabelSelectLabel = new JLabel("Select label:");

    ArrayList<JCheckBox> boxList = new ArrayList<>();

    JCheckBox meetCB = new JCheckBox("Want to Meet");
    JCheckBox collaborateCB = new JCheckBox("Want to Collaborate");
    JCheckBox discussCB = new JCheckBox("Want to Discuss");

    JButton backBTN = new JButton("Back");
    JButton applyBTN = new JButton("Apply");

    UIController uiController;

    public LabelSelectFrame(UIController uiController){
        this.uiController = uiController;
        this.setTitle("Status Label Selection"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        // places objects inside frame
        // buttons
        backBTN.setBounds(650, 530, 100, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);
        applyBTN.setBounds(350, 350, 100, 20);
        applyBTN.addActionListener(this);
        applyBTN.setFocusable(false);

        //checkbox
        meetCB.setBounds(200, 150, 250,50);
        collaborateCB.setBounds(200, 200, 250, 50);
        discussCB.setBounds(200, 250, 250, 50);
        boxList.add(meetCB);
        boxList.add(collaborateCB);
        boxList.add(discussCB);
        for(JCheckBox box: boxList){
            System.out.println(box.getText());
             box.setSelected(uiController.getLabelSelectUIControl().getStudentLabelState(box.getText()));
        }

        // labels
        LabelSelectLabel.setBounds(100,100,100,20);

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
            for (JCheckBox box: boxList){
                uiController.getLabelSelectUIControl().updateStudentLabel(box.getText(), box.isSelected());
            }
        }
        else if(e.getSource() == backBTN){
            //TODO: go to home page
        }
    }
}