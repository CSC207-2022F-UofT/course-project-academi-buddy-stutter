package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileUploadFrame extends JFrame implements ActionListener {
    JLabel titleLabel = new JLabel("Upload Your Calendar:");
    JButton uploadBTN = new JButton("File");
    JButton nextBTN = new JButton("Next");
    public FileUploadFrame(){
        // setting up title label
        titleLabel.setBounds(60, 0, 300, 50);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        // setting up buttons
        uploadBTN.setBounds(135, 50, 50, 20);
        uploadBTN.addActionListener(this);
        nextBTN.setBounds(135, 170, 50, 20);
        nextBTN.addActionListener(this);

        // adding elements to the frame
        this.add(titleLabel);
        this.add(uploadBTN);
        this.add(nextBTN);

        this.setTitle("File Upload Frame"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
