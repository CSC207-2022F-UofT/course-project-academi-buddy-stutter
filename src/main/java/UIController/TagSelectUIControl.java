package UIController;

import UseCases.CourseManager;
import UseCases.TagManager;
import UseCases.UserManager;
import Entities.Student;
import Entities.User;
import UseCases.TagSelectManager;

public class TagSelectUIControl {
    private TagSelectManager tagSelectManager;
    private User self;
    public TagSelectUIControl(User self, CourseManager courseManager, UserManager userManager, TagManager tagManager){
        this.tagSelectManager = new TagSelectManager(courseManager, userManager, tagManager);
        this.self = self;
    }

    public boolean getStudentTagState(String tagName){
        return tagSelectManager.getStudentTagState((Student) self, tagName);
    }

    public void updateStudentTag(String tagName, boolean selected){
        tagSelectManager.updateStudentTag((Student) self, tagName, selected);
    }
}
