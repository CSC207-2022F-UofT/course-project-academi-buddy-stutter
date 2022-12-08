package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Implements JFrame that displays all registered users
 */
public class AllStudentsFrame extends JFrame implements ActionListener, ItemListener {

    final FrameNavigator frameNavigator;
    final JLabel allStudentLabel = new JLabel("All Students");
    final String[] studentColumnNames = {"Full Name", "userID"};
    final DefaultTableModel studentModel = new DefaultTableModel(0, 2) {
        @Override
        public boolean isCellEditable(int row, int column) {return false;}
    };
    ArrayList<String> studentList;
    JTable studentTable;
    final JScrollPane studentScroll = new JScrollPane(studentTable);

    /**
     * Constructs all UI components
     */
    public AllStudentsFrame(FrameNavigator frameNavigator) throws IOException {
        this.frameNavigator = frameNavigator;
        this.setTitle("All Students");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(250, 250);
        this.setLocationRelativeTo(null);

        refreshStudentsTable();

        // User Interface

        // Labels
        allStudentLabel.setBounds(80, 10, 100, 20);

        //JScrollPane
        studentScroll.setBounds(50, 40, 150, 150);

        //Set Table
        studentList = frameNavigator.getAllStudentsUIPresenter().getAllStudents();

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
                    frameNavigator.toProfileDisplay(friendID);
                }
            }
        });

        studentScroll.setViewportView(studentTable);
        studentScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        studentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

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

    /**
     * refreshes student table
     */
    public void refreshStudentsTable() throws IOException {
        studentModel.setRowCount(0);
        studentList = frameNavigator.getAllStudentsUIPresenter().getAllStudents();

        addStudentsToList(studentList, frameNavigator, studentModel);
    }

    /**
     * appends student to table
     * @param studentList contains a list of registered user
     * @param frameNavigator an instance of FrameNavigator
     * @param studentModel an instance of DefaultTableModel
     */
    static void addStudentsToList(ArrayList<String> studentList, FrameNavigator frameNavigator, DefaultTableModel studentModel) throws IOException {
        System.out.println(studentList.toString());
        String userID = frameNavigator.getProfileUIPresenter().getUserID();
        ArrayList<String> adminIDs = frameNavigator.getAllStudentsUIPresenter().getAdminIDs();
        for (String studentID : studentList) {
            studentID = studentID.trim().strip();
            Object[] row = new Object[2];
            ArrayList<String> stringRow = new ArrayList<>();
            stringRow.add(frameNavigator.getFriendListUIPresenter().getFriendFullName(studentID));
            stringRow.add(studentID);
            if (!userID.equals(studentID) && !adminIDs.contains(studentID)) {
                row = stringRow.toArray(row);
                studentModel.addRow(row);
            }
        }
    }
}
