package UIController;

import Entities.Student;
import Entities.User;
import UseCases.CloudCourseData;
import UseCases.LabelSelectManager;
import UseCases.CloudUserData;

/**
 * Implements LabelSelectUIControl
 */
public class LabelSelectUIControl {
    private User self;
    private LabelSelectManager labelSelectManager;

    /**
     * Constructs LabelSelectUIControl
     * @param self a user
     * @param courseDataManager an instance of CourseDataManager
     * @param userDataManager an instance of UserDataManager
     */
    public LabelSelectUIControl(User self, CloudCourseData courseDataManager, CloudUserData userDataManager){
        this.self = self;
        this.labelSelectManager = new LabelSelectManager(courseDataManager, userDataManager);
    }

    /**
     * Get user's label
     * @param labelName name of label
     * @return label selected by user
     */
    public boolean getStudentLabelState(String labelName){
        return labelSelectManager.getStudentLabelState((Student) self, labelName);
    }

    /**
     * Updates user label
     * @param labelName name of label
     * @param selected determines whether label is selected
     */
    public void updateStudentLabel(String labelName, boolean selected){
        labelSelectManager.updateStudentLabel((Student) self, labelName, selected);
    }
}
