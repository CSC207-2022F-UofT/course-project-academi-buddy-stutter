package GUI;

import UIController.UIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

public class AdminFrame extends JFrame implements ActionListener {
    JLabel coursesLabel = new JLabel("Available Courses:");
    JLabel finderLabel = new JLabel("Student in Course:");

    // creating text-fields
    JTextField studentIDText = new JTextField();
    JTextField courseIDText = new JTextField();

    // creating textarea
    JTextArea findStudents = new JTextArea();
    JTextArea courses = new JTextArea();
    UIController uiController;

    JButton removeStudentBTN = new JButton("Remove Student");

    public AdminFrame(UIController uiController){
        this.uiController = uiController;

        coursesLabel.setBounds(10, 10, 180, 20);
        coursesLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        finderLabel.setBounds(150,10,180,20);
        finderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        studentIDText.setEditable(true);
        studentIDText.setBounds(300,50,100,20);
        courseIDText.setEditable(true);
        courseIDText.setBounds(300,100,100,20);
        findStudents.setEditable(false);
        findStudents.setBounds(150,30,100,200);
        courses.setEditable(false);
        courses.setBounds(10,30,100,200);

        removeStudentBTN.setBounds(290, 230, 130, 20);
        removeStudentBTN.addActionListener(this);

        // adding elements to frame
        this.add(coursesLabel);
        this.add(finderLabel);
        this.add(studentIDText);
        this.add(courseIDText);
        this.add(courses);
        this.add(findStudents);
        this.add(removeStudentBTN);


        this.setTitle("Admin Frame"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(440, 300);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);

    }

    public void paint(Graphics gp){
        super.paint(gp); Graphics2D graphics = (Graphics2D) gp;
        Line2D line = new Line2D.Float(270, 0, 270, 300);
        graphics.draw(line);
    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

