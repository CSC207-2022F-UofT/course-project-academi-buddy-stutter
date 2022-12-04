package UseCases;

import Entities.InterestTag;
import Entities.Student;

import java.io.IOException;

public class TagSelectManager extends UseCase {
    private TagDataCloud tb;

    public TagSelectManager(CourseDataCloud courseDatabase, UserDataCloud userDatabase, TagDataCloud tagManager) {
        super(courseDatabase, userDatabase);
        this.tb= tagManager;
    }

    public boolean getStudentTagState(Student self, String tagName){
        InterestTag tag = new InterestTag(tagName);
        return (self.isTagSelected(tag));
    }

    public void updateStudentTag(Student self, String tagName, boolean selected){
        InterestTag tag = new InterestTag(tagName);
        if(selected){
            try {
                System.out.println("selected");
                tb.addStudent(tag, self);
                self.updateStudentTOI(tag, true);
                ub.addStudentUser(self);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                System.out.println("unselected");
                tb.removeStudent(tag, self);
                self.updateStudentTOI(tag, false);
                ub.addStudentUser(self);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}