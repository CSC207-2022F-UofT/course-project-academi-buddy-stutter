package views;
import controllers.UIController;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class implements TagSelectFrame so that user can choose different interest tags.
 */
public class TagSelectFrame extends JFrame implements ActionListener, ChangeListener {
    JLabel tagSelectLabel = new JLabel("Select Interest Tags:");
    ArrayList<JCheckBox> boxList = new ArrayList<>();
    JCheckBox adventureCB = new JCheckBox("Adventure");
    JCheckBox musicCB = new JCheckBox("Music");
    JCheckBox catCB = new JCheckBox("Cat");
    JCheckBox outdoorCB = new JCheckBox("Writing Javadocs");
    JCheckBox bookCB = new JCheckBox("Books");
    JCheckBox movieCB = new JCheckBox("Movies");
    JCheckBox beerCB = new JCheckBox("Beer");
    JCheckBox gameCB = new JCheckBox("Video Games");
    JCheckBox photoCB = new JCheckBox("Photography");
    JButton backBTN = new JButton("Back");
    JButton applyBTN = new JButton("Apply");
    String[] tagType = {"Adventure", "Music", "Cat", "Writing Javadocs", "Books", "Movies", "Beer", "Video Games", "Photography"};
    UIController uiController;
    private ArrayList<Boolean> initialState = new ArrayList<Boolean>();
    Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);

    /**
     * Implements all UI components
     */
    public TagSelectFrame(UIController uiController) throws IOException {
        this.uiController = uiController;

        this.setTitle("Interest Tag Selection"); // sets frame's title
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // closes the frame
        this.setResizable(false); // fixed size for frame
        this.setLayout(null);
        this.setSize(370, 230);
        this.setLocationRelativeTo(null); // centers the frame relative to the monitor

        // places objects inside frame
        // buttons
        backBTN.setBounds(190, 150, 100, 20);
        backBTN.addActionListener(this);
        backBTN.setFocusable(false);
        applyBTN.setBounds(70, 150, 100, 20);
        applyBTN.addActionListener(this);
        applyBTN.setFocusable(false);
        applyBTN.setEnabled(false);

        //checkbox
        adventureCB.setBounds(10, 30, 100,50);
        musicCB.setBounds(10, 60, 100, 50);
        catCB.setBounds(10, 90, 100, 50);
        outdoorCB.setBounds(110, 30, 150, 50);
        bookCB.setBounds(110, 60, 100, 50);
        movieCB.setBounds(110, 90, 100, 50);
        beerCB.setBounds(240, 30, 100, 50);
        gameCB.setBounds(240, 60, 150, 50);
        photoCB.setBounds(240, 90, 150, 50);
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
            initialState.add(uiController.getTagSelectUIControl().getStudentTagState(box.getText()));
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
        tagSelectLabel.setBounds(10,10,150,20);

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

    /**
     * Updates usre profile in database with selected interest tags.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == applyBTN){
            this.setCursor(waitCursor);
            ArrayList<Boolean> newState = new ArrayList<>();
            for(int i = 0; i < initialState.size(); i++){
                boolean selection = boxList.get(i).isSelected();
                newState.add(selection);
                if(selection != initialState.get(i)){
                    try {
                        uiController.getTagSelectUIControl().updateStudentTag(boxList.get(i).getText(), boxList.get(i).isSelected());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            initialState = newState;
            applyBTN.setEnabled(false);
            this.setCursor(Cursor.getDefaultCursor());
        }
        else if(e.getSource() == backBTN){
            this.dispose();
        }
    }

    /**
     * "Apply" button is only enabled when user chooses different interest tags from last round.
     */
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