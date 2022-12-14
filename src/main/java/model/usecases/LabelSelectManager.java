package model.usecases;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.entities.Label;
import model.entities.Student;

/**
 * User Cases for operations on labels
 */
public class LabelSelectManager extends UseCase{

    /**
     * Initializer.
     * @param courseDatabase the course database.
     * @param userDatabase the user database.
     */
    public LabelSelectManager(CourseDataAccess courseDatabase, UserDataAccess userDatabase, TagDataAccess tagDatabase) {
        super(courseDatabase, userDatabase, tagDatabase);
    }

    /**
     * To show the state of all labels for a student.
     * @param selfID the student to access.
     * @param labelName the name String of the label to access.
     * @return true if the label for the student is selected.
     */
    public boolean getStudentLabelState(String selfID, String labelName){
        Student self = (Student) this.ub.getUserByID(selfID);
        Label label = new Label(labelName);
        return (self.isLabelSelected(label));
    }

    /**
     * Updates the student's corresponding label.
     * @param selfID The Student to access.
     * @param labelName The label to check.
     * @param selected To see if the label is selected or not.
     */
    public void updateStudentLabel(String selfID, String labelName, boolean selected){
        Student self = (Student) this.getUserByID(selfID);
        Label label = new Label(labelName);
        if (selected) {
            self.updateLabel(label, true);
            ub.addStudentUser(self);
        } else {
            self.updateLabel(label, false);
            ub.addStudentUser(self);
        }
    }
}