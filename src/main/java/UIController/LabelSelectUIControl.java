package UIController;

import Entities.Student;
import Entities.User;
import UseCases.CourseDataManager;
import UseCases.LabelSelectManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;

import java.io.IOException;

/**
 * Implements LabelSelectUIControl
 */
public class LabelSelectUIControl {
    private String self;
    private LabelSelectManager labelSelectManager;

    /**
     * Constructs LabelSelectUIControl
     * @param self a user
     * @param courseDataManager an instance of CourseDataManager
     * @param userDataManager an instance of UserDataManager
     */
    public LabelSelectUIControl(String userID, CourseDataManager courseDataManager, UserDataManager userDataManager, TagDataManager tagDataManager){
        this.self = userID;
        this.labelSelectManager = new LabelSelectManager(courseDataManager, userDataManager, tagDataManager);
    }

    /**
     * Get user's label
     * @param labelName name of label
     * @return label selected by user
     */
    public boolean getStudentLabelState(String labelName) throws IOException {
        Student stu = (Student) labelSelectManager.getUserByID(this.self);
        return labelSelectManager.getStudentLabelState(stu, labelName);
    }

    /**
     * Updates user label
     * @param labelName name of label
     * @param selected determines whether label is selected
     */
    public void updateStudentLabel(String labelName, boolean selected) throws IOException {
        Student stu = (Student) labelSelectManager.getUserByID(this.self);
        labelSelectManager.updateStudentLabel(stu, labelName, selected);
    }
}
