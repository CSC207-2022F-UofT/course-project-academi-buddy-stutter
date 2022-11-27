package GUI;

import UIController.UIController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TagSelectFrame extends JFrame implements ActionListener{


    JLabel tagSelectLabel = new JLabel("Select Tag:");

    ArrayList<JCheckBox> boxList = new ArrayList<>();

    JCheckBox adventureCB = new JCheckBox("Adventure");
    JCheckBox musicCB = new JCheckBox("Music");
    JCheckBox catCB = new JCheckBox("Cat");
    JCheckBox outdoorCB = new JCheckBox("Outdoors");
    JCheckBox bookCB = new JCheckBox("Books");
    JCheckBox movieCB = new JCheckBox("Movies");
    JCheckBox beerCB = new JCheckBox("Beer");
    JCheckBox gameCB = new JCheckBox("Video Games");
    JCheckBox photoCB = new JCheckBox("Photography");

    JButton backBTN = new JButton("Back");
    JButton applyBTN = new JButton("Apply");

    String[] tagType = {"Adventure", "Music", "Cat", "Outdoors", "Books", "Movies", "Beer", "Video Games", "Photography"};

    UIController uiController;


    public TagSelectFrame(UIController uiController){
        this.uiController = uiController;

        this.setTitle("Interest Tag Selection"); // sets frame's title
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
        adventureCB.setBounds(200, 150, 100,50);
        musicCB.setBounds(200, 200, 100, 50);
        catCB.setBounds(200, 250, 100, 50);
        outdoorCB.setBounds(350, 150, 100, 50);
        bookCB.setBounds(350, 200, 100, 50);
        movieCB.setBounds(350, 250, 100, 50);
        beerCB.setBounds(500, 150, 100, 50);
        gameCB.setBounds(500, 200, 150, 50);
        photoCB.setBounds(500, 250, 150, 50);
        boxList.add(adventureCB);
        boxList.add(musicCB);
        boxList.add(catCB);
        boxList.add(outdoorCB);
        boxList.add(bookCB);
        boxList.add(movieCB);
        boxList.add(beerCB);
        boxList.add(gameCB);
        boxList.add(photoCB);
        for(JCheckBox box: boxList){
            box.setSelected(uiController.getTagSelectUIControl().getStudentTagState(box.getText()));
        }

        // labels
        tagSelectLabel.setBounds(100,100,100,20);

        // textfields



        // adds objects to the frame
        this.add(backBTN);
        this.add(applyBTN);

        this.add(adventureCB);
        this.add(musicCB);
        this.add(catCB);
        this.add(outdoorCB);
        this.add(bookCB);
        this.add(movieCB);
        this.add(beerCB);
        this.add(gameCB);
        this.add(photoCB);




        this.add(tagSelectLabel);

        this.setVisible(true); // set frame to visible
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == applyBTN){
            for (JCheckBox box: boxList){
                uiController.getTagSelectUIControl().updateStudentTag(box.getText(), box.isSelected());
            }
        }
        else if(e.getSource() == backBTN){
            //TODO: go to home page
        }
    }
}