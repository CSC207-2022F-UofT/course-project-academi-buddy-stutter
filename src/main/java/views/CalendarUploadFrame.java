package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class allows user to upload an ics calendar by clicking the "Upload" button.
 */
public class CalendarUploadFrame extends JFrame implements ActionListener {
    final JLabel titleLabel = new JLabel("Upload Your Calendar:");
    final JLabel completedLabel = new JLabel();
    final JButton uploadBTN = new JButton("Upload");
    final JButton nextBTN = new JButton("Next");

    final JButton backBTN = new JButton("Back");
    private final int status;

    final FrameNavigator frameNavigator;

    /**
     * This constructor method implments all UI components for FileUploadFrame.
     * @param source keeps track of the status of user [registered, not registered]
     */
    public CalendarUploadFrame(FrameNavigator frameNavigator, int source){
        this.frameNavigator = frameNavigator;
        this.status = source;
        // setting up title label
        titleLabel.setBounds(60, 0, 300, 50);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        completedLabel.setBounds(135, 75, 150, 20);

        // setting up buttons
        uploadBTN.setBounds(115, 50, 100, 20);
        uploadBTN.addActionListener(this);
        nextBTN.setBounds(115, 170, 100, 20);
        nextBTN.addActionListener(this);
        backBTN.setBounds(135, 140, 100, 20);
        backBTN.addActionListener(this);


        // adding elements to the frame
        this.add(titleLabel);
        this.add(uploadBTN);
        this.add(nextBTN);
        this.add(completedLabel);

        this.setTitle("Calendar Upload"); // sets frame's title
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(340, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        this.setVisible(true);
    }


    /**
     * This method checks whether the user is uploading a calendar as a registered student or not.
     * An "Uploading..." message will be shown when the calendar is being uploaded to database
     * An "Uploaded!" message will be shown when the calendar is successfully uploaded to database
     * An "Error!" message will be shown when the calendar fails to upload to database.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == nextBTN && status == FrameNavigator.FROM_REGISTER){
            this.dispose();
            frameNavigator.toProfileCompleteFrame();
        } else if (e.getSource() == nextBTN && status == FrameNavigator.FROM_PROFILE) {
            this.dispose();
            frameNavigator.toProfile();
        } else if (e.getSource() == uploadBTN && status == FrameNavigator.FROM_REGISTER) {
            completedLabel.setForeground(Color.black);
            completedLabel.setText("Uploading...");
            if (frameNavigator.getFileUploadUIPresenter().upload()) {
                try {
                    frameNavigator.getFileUploadUIPresenter().copyFileToPath();
                    completedLabel.setForeground(Color.green);
                    completedLabel.setText("Uploaded!");
                    frameNavigator.getFileUploadUIPresenter().updateDatabase();
                } catch (Exception ex) {
                    completedLabel.setForeground(Color.red);
                    completedLabel.setText("Error!");
                    throw new RuntimeException(ex);
                }
            }
            else {
                completedLabel.setText("");
            }
        } else if (e.getSource() == uploadBTN && status == FrameNavigator.FROM_PROFILE) {
            completedLabel.setForeground(Color.black);
            completedLabel.setText("Uploading. This may take a while...");
            if (frameNavigator.getFileUploadUIPresenter().upload()) {
                try {
                    frameNavigator.getFileUploadUIPresenter().copyFileToPath();
                    completedLabel.setForeground(Color.green);
                    completedLabel.setText("Uploaded!");
                    frameNavigator.getFileUploadUIPresenter().reuploadCalendar();
                } catch (Exception ex) {
                    completedLabel.setForeground(Color.red);
                    completedLabel.setText("Error!");
                    throw new RuntimeException(ex);
                }
            }
            else {
                completedLabel.setText("");
            }
        }
    }
}