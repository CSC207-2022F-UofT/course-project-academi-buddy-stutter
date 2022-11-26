package GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.*;

public class HomeFrame extends JFrame implements ActionListener {
    JLabel fileLabel = new JLabel("Select Your ICS File:");
    JLabel findLabel = new JLabel("Click to Find Your Study Buddies!");
    JLabel completedLabel = new JLabel("");
    JButton fileBTN = new JButton("FILE");
    JButton findBTN = new JButton("FIND");
    JButton returnBTN = new JButton("Return to Login Screen");
    JTextArea nameList = new JTextArea();

    public HomeFrame() {
        // setting up labels
        fileLabel.setBounds(10, 10, 125, 20);
        findLabel.setBounds(10, 35, 210, 20);
        completedLabel.setBounds(200, 10, 210, 20);

        // setting up textarea
        nameList.setBounds(10, 60, 320, 100);
        nameList.setEditable(false);

        // setting up buttons
        fileBTN.setBounds(140, 10, 50, 20);
        fileBTN.setFocusable(false);
        fileBTN.addActionListener(this);
        findBTN.setBounds(225, 35, 50, 20);
        findBTN.setFocusable(false);
        findBTN.addActionListener(this);
        returnBTN.setBounds(65, 170, 200, 20);
        returnBTN.setFocusable(false);
        returnBTN.addActionListener(this);

        // adding components to the frame
        this.add(fileLabel);
        this.add(fileBTN);
        this.add(nameList);
        this.add(findLabel);
        this.add(findBTN);
        this.add(returnBTN);
        this.add(completedLabel);

        this.setTitle("Home Frame"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true); // set frame to visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // return to LoginFrame
        if (e.getSource() == returnBTN) {
            this.dispose();
            LoginFrame loginFrame = new LoginFrame();
        }

        // Select File
        if (e.getSource() == fileBTN) {
            JFileChooser fileChooser = new JFileChooser();

            //ics calender filter
            FileNameExtensionFilter filter = new FileNameExtensionFilter("coursesCalendar", "ics");
            fileChooser.setFileFilter(filter);

            //current file path
            File currentFile = new File("coursesCalendar.ics");

            //selected file path
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File filePath = new File(fileChooser.getSelectedFile().getAbsolutePath());

                //copying selected file to current file path
                try {
                    Files.copy(filePath.toPath(), currentFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    completedLabel.setForeground(Color.green);
                    completedLabel.setText("File Uploaded!");
                } catch (Exception ex) {
                    completedLabel.setForeground(Color.red);
                    completedLabel.setText("Error");
                    throw new RuntimeException(ex);
                }
            }


        }
    }
}

