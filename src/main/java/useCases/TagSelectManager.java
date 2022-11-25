package useCases;

import Database.CourseManager;
import Database.TagManager;
import Database.UserManager;
import Users.InterestTag;
import Users.Student;
import Users.User;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TagSelectManager extends UseCase {
    private TagManager tb;

    public TagSelectManager(CourseManager courseDatabase, UserManager userDatabase, TagManager tagManager) {
        super(courseDatabase, userDatabase);
        this.tb= tagManager;
    }

    public boolean getStudentTagState(Student self,String tagName){
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
                tb.addStudent(tag, self);
                self.updateStudentTOI(tag, true);
                ub.addStudentUser(self);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                tb.removeStudent(tag, self);
                self.updateStudentTOI(tag, false);
                ub.addStudentUser(self);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}