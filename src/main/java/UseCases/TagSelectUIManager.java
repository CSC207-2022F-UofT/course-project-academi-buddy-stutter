package UseCases;

import Entities.InterestTag;
import Entities.Student;

import java.io.IOException;

public class TagSelectUIManager extends UseCase {
    private TagDataManager tb;

    public TagSelectUIManager(CourseDataManager courseDatabase, UserDataManager userDatabase, TagDataManager tagManager) {
        super(courseDatabase, userDatabase);
        this.tb= tagManager;
    }

    public boolean getStudentTagState(Student self, String tagName){
        InterestTag tag = new InterestTag(tagName);
        return (self.isTagSelected(tag));
    }

    public void updateStudentTag(Student self, String tagName, boolean selected){
        InterestTag tag = new InterestTag(tagName);
        if(!tb.getTagNameList().contains(tagName)) {
            try {
                tb.addTag(tag);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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