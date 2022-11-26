package GUI;

import Database.CourseManager;
import Database.TagManager;
import Database.UserManager;
import Users.Student;
import Users.User;
import useCases.TagSelectManager;

public class TagSelectUIControl extends UIController{

    private TagSelectManager tagSelectManager;
    private TagManager tb;



    public TagSelectUIControl(User self, CourseManager courseDatabase, UserManager userDatabase) {
        super(self, courseDatabase, userDatabase);
        this.tb = tb;
        this.tagSelectManager = new TagSelectManager(courseDatabase, userDatabase, tb);
    }


    public boolean getStudentTagState(String tagName){
        return tagSelectManager.getStudentTagState((Student) self, tagName);
    }

    public void updateStudentTag(String tagName, boolean selected){
        tagSelectManager.updateStudentTag((Student) self, tagName, selected);
    }
}
