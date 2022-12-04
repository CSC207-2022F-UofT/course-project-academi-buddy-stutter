package UIController;

import Entities.Student;
import Entities.User;
import UseCases.CourseDataCloud;
import UseCases.LabelSelectManager;
import UseCases.UserDataCloud;

public class LabelSelectUIControl {
    private User self;
    private LabelSelectManager labelSelectManager;

    public LabelSelectUIControl(User self, CourseDataCloud courseDataManager, UserDataCloud userDataManager){
        this.self = self;
        this.labelSelectManager = new LabelSelectManager(courseDataManager, userDataManager);
    }

    public boolean getStudentLabelState(String labelName){
        return labelSelectManager.getStudentLabelState((Student) self, labelName);
    }

    public void updateStudentLabel(String labelName, boolean selected){
        labelSelectManager.updateStudentLabel((Student) self, labelName, selected);
    }

}
