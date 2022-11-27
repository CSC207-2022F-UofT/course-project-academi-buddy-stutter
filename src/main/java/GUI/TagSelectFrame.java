package GUI;
import Users.Student;
import com.sun.tools.jconsole.JConsoleContext;
import org.checkerframework.checker.units.qual.A;
import useCases.TagMatchManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

public class TagSelectFrame extends JFrame implements ActionListener, ChangeListener {


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
    boolean initialized = false;
    ArrayList<Boolean> initialState = new ArrayList<Boolean>();

    public TagSelectFrame(UIController uiController){
        this.uiController = uiController;

        this.setTitle("Interest Tag Selection"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(330, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor


        // places objects inside frame
        // buttons
        backBTN.setBounds(170, 150, 100, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);
        applyBTN.setBounds(50, 150, 100, 20);
        applyBTN.addActionListener(this);
        applyBTN.setFocusable(false);
        applyBTN.setEnabled(false);

        //checkbox
        adventureCB.setBounds(10, 30, 100,50);
        musicCB.setBounds(10, 60, 100, 50);
        catCB.setBounds(10, 90, 100, 50);
        outdoorCB.setBounds(110, 30, 100, 50);
        bookCB.setBounds(110, 60, 100, 50);
        movieCB.setBounds(110, 90, 100, 50);
        beerCB.setBounds(210, 30, 100, 50);
        gameCB.setBounds(210, 60, 150, 50);
        photoCB.setBounds(210, 90, 150, 50);
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
            box.setSelected(uiController.getStudentTagState(box.getText()));
            initialState.add(uiController.getStudentTagState(box.getText()));
        }
        adventureCB.addChangeListener(this);
        musicCB.addChangeListener(this);
        catCB.addChangeListener(this);
        outdoorCB.addChangeListener(this);
        bookCB.addChangeListener(this);
        movieCB.addChangeListener(this);
        beerCB.addChangeListener(this);
        gameCB.addChangeListener(this);
        photoCB.addChangeListener(this);

        // labels
        tagSelectLabel.setBounds(10,10,100,20);

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
                uiController.updateStudentTag(box.getText(), box.isSelected());
            }
        }
        else if(e.getSource() == backBTN){
            //TODO: go to home page
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