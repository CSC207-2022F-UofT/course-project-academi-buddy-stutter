package GUI;

import UIController.UIController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AllStudentsFrame extends JFrame implements ActionListener, ItemListener {

    UIController uiController;
    JLabel allStudentLabel = new JLabel("All Students");
    String[] studentColumnNames = {"Full Name", "userID"};
    DefaultTableModel studentModel = new DefaultTableModel(0, 2) {
        @Override
        public boolean isCellEditable(int row, int column) {return false;}
    };
    ArrayList<String> studentList;
    JTable studentTable;
    JScrollPane studentScroll = new JScrollPane(studentTable);

    public AllStudentsFrame(UIController uiController) {
        this.uiController = uiController;
        this.setTitle("All Students");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(300, 230);
        this.setLocationRelativeTo(null);

        // User Interface

        // Labels
        allStudentLabel.setBounds(150, 10, 100, 20);

        //JScrollPane
        studentScroll.setBounds(100, 30, 150, 200);

        //Set Table
        studentList = uiController.getAllStudentsUIControl().getAllStudents();

        studentModel.setColumnIdentifiers(studentColumnNames);
        studentTable = new JTable(studentModel);

        //Position Table
        studentTable.setBounds(100,30,150,200);
//        studentTable.getColumnModel().getColumn(0).setPreferredWidth(100);

        // Hide userID column
        studentTable.getColumnModel().getColumn(1).setMinWidth(0);
        studentTable.getColumnModel().getColumn(1).setMaxWidth(0);
        studentTable.getColumnModel().getColumn(1).setWidth(0);

        studentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // go to friend's profile display page when clicked
                int row = studentTable.rowAtPoint(e.getPoint());
                int col = studentTable.columnAtPoint(e.getPoint());
                String selectedData = "";
                System.out.println(studentModel.getRowCount());
                if (studentModel.getRowCount() > 0) {
                    selectedData = (String) studentTable.getValueAt(row, col);
                }
                System.out.println(selectedData);
                if (!selectedData.equals("")) {
                    String friendID = (String) studentTable.getValueAt(row, 1);
                    friendID = friendID.trim().strip();
                    uiController.toProfileDisplay(friendID);
                }
            }
        });

        this.add(allStudentLabel);
        this.getContentPane().add(studentScroll, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
