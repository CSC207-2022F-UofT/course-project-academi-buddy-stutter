package GUI;

import UIController.UIController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileUploadFrame extends JFrame implements ActionListener {
    JLabel titleLabel = new JLabel("Upload Your Calendar:");
    JLabel completedLabel = new JLabel();
    JButton uploadBTN = new JButton("File");
    JButton nextBTN = new JButton("Next");


    UIController uiController;

    public FileUploadFrame(UIController uiController){
        this.uiController = uiController;

        // setting up title label
        titleLabel.setBounds(60, 0, 300, 50);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        completedLabel.setBounds(135, 75, 50, 20);

        // setting up buttons
        uploadBTN.setBounds(135, 50, 50, 20);
        uploadBTN.addActionListener(this);
        nextBTN.setBounds(135, 170, 50, 20);
        nextBTN.addActionListener(this);

        // adding elements to the frame
        this.add(titleLabel);
        this.add(uploadBTN);
        this.add(nextBTN);
        this.add(completedLabel);

        this.setTitle("Calendar Upload"); // sets frame's title
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == nextBTN){
            this.dispose();
            uiController.toProfileCompleteFrame();
        }

        else if (e.getSource() == uploadBTN) {
            if (uiController.getFileUploadUIControl().upload()) {
                try {
                    uiController.getFileUploadUIControl().copyFileToPath();
                    completedLabel.setForeground(Color.green);
                    completedLabel.setText("Uploaded!");
                    uiController.getFileUploadUIControl().updateDatabase();
                } catch (Exception ex) {
                    completedLabel.setForeground(Color.red);
                    completedLabel.setText("Error!");
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}