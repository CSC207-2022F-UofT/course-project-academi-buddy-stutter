package UIController;

import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;
import Entities.Student;
import Entities.User;
import UseCases.TagSelectManager;

/**
 * Implements TagSelectUIControl for TagSelectFrame
 */
public class TagSelectUIControl {
    private TagSelectManager tagSelectManager;
    private User self;

    /**
     * Constructs TagSelectUIControl
     * @param self a user
     * @param courseManager an instance of CourseDataManager
     * @param userManager an instance of UserDataManager
     * @param tagManager an instance of TagDataManager
     */
    public TagSelectUIControl(User self, CourseDataManager courseManager, UserDataManager userManager, TagDataManager tagManager){
        this.tagSelectManager = new TagSelectManager(courseManager, userManager, tagManager);
        this.self = self;
    }

    /**
     * Get user's tag state
     * @param tagName name of tags
     * @return whether a tag is selected or not
     */
    public boolean getStudentTagState(String tagName){
        return tagSelectManager.getStudentTagState((Student) self, tagName);
    }

    /**
     * Updates user's tags
     * @param tagName name of tags
     * @param selected whether the tags are selected or not
     */
    public void updateStudentTag(String tagName, boolean selected){
        tagSelectManager.updateStudentTag((Student) self, tagName, selected);
    }
}
