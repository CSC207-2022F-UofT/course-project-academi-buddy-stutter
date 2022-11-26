package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatchFrame extends JFrame implements ActionListener {
    JLabel numCommonLabel = new JLabel("Enter the Number of Common Sessions:");
    JLabel selectLabel = new JLabel("Select Label:");
    JLabel matchLabel = new JLabel("Matched Students:");

    String[] userType = {"1", "2", "3", "4", "5", "6"};
    JComboBox<String> numBox = new JComboBox<>(userType);
    JTextArea outputText = new JTextArea();

    JButton returnBTN = new JButton("BACK");
    JButton findBTN = new JButton("FIND");
    public MatchFrame() {
        // setting up labels:
        numCommonLabel.setBounds(10, 10, 270, 20);
        // selectLabel.setBounds(10, 35, 100, 20);
        matchLabel.setBounds(10, 60, 120, 20);

        // setting up combobox
        numBox.setEditable(false);
        numBox.setBounds(280, 10, 70, 20);

        // setting up buttons
        returnBTN.setBounds(380, 160, 50, 20);
        findBTN.setBounds(10, 35, 50, 20);

        // setting up textareas
        outputText.setBounds(135, 60, 200, 120);
        outputText.setEditable(false);

        // adding elements to frame
        this.add(numCommonLabel);
        // this.add(selectLabel);
        this.add(matchLabel);
        this.add(outputText);
        this.add(numBox);
        this.add(returnBTN);
        this.add(findBTN);

        this.setTitle("Match Frame"); // sets frame's title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(450, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
