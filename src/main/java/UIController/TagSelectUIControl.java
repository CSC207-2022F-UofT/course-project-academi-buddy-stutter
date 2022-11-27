package UIController;

import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;
import Entities.Student;
import Entities.User;
import UseCases.TagSelectUIManager;

public class TagSelectUIControl {
    private TagSelectUIManager tagSelectManager;
    private User self;
    public TagSelectUIControl(User self, CourseDataManager courseManager, UserDataManager userManager, TagDataManager tagManager){
        this.tagSelectManager = new TagSelectUIManager(courseManager, userManager, tagManager);
        this.self = self;
    }

    public boolean getStudentTagState(String tagName){
        return tagSelectManager.getStudentTagState((Student) self, tagName);
    }

    public void updateStudentTag(String tagName, boolean selected){
        tagSelectManager.updateStudentTag((Student) self, tagName, selected);
    }
}
