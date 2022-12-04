package UseCases;

import Entities.Label;
import Entities.Student;

import java.io.IOException;

/**
 * User Cases for operations on labels
 */
public class LabelSelectManager extends UseCase{

    /**
     * Initializer.
     * @param courseDatabase the course database.
     * @param userDatabase the user database.
     */
    public LabelSelectManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

    /**
     * To show the state of all labels for a student.
     * @param self the student to access.
     * @param labelName the name String of the label to access.
     * @return true if the label for the student is selected.
     */
    public boolean getStudentLabelState(Student self, String labelName){
        Label label = new Label(labelName);
        System.out.println(self.getLabels());
        return (self.isLabelSelected(label));
    }

    /**
     * Updates the student's corresponding label.
     * @param self The Student to access.
     * @param labelName The label to check.
     * @param selected To see if the label is selected or not.
     */
    public void updateStudentLabel(Student self, String labelName, boolean selected){
        Label label = new Label(labelName);
        if(selected){
            try {
                self.updateLabel(label, true);
                ub.addStudentUser(self);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                self.updateLabel(label, false);
                ub.addStudentUser(self);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
