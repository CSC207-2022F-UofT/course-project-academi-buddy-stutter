package UseCases;

import Entities.InterestTag;
import Entities.Label;
import Entities.Student;

import java.io.IOException;

public class LabelSelectManager extends UseCase{
    public LabelSelectManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

    public boolean getStudentLabelState(Student self, String labelName){
        Label label = new Label(labelName);
        System.out.println(self.getLabels());
        return (self.isLabelSelected(label));
    }

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