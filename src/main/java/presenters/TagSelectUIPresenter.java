package presenters;

import model.usecases.TagSelectManager;
import usecases.*;
import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import java.io.IOException;

/**
 * Implements TagSelectUIPresenter for TagSelectFrame
 */
public class TagSelectUIPresenter {
    private TagSelectManager tagSelectManager;
    private String selfID;

    /**
     * Constructs TagSelectUIPresenter
     * @param userID a user
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess an instance of UserDataManager
     * @param tagDataAccess an instance of TagDataManager
     */
    public TagSelectUIPresenter(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.tagSelectManager = new TagSelectManager(courseDataAccess, userDataAccess, tagDataAccess);
        this.selfID = userID;
    }

    /**
     * Get user's tag state
     * @param tagName name of tags
     * @return whether a tag is selected or not
     */
    public boolean getStudentTagState(String tagName) throws IOException {
        return tagSelectManager.getStudentTagState(selfID, tagName);
    }

    /**
     * Updates user's tags
     * @param tagName name of tags
     * @param selected whether the tags are selected or not
     */
    public void updateStudentTag(String tagName, boolean selected) throws IOException {
        tagSelectManager.updateStudentTag(selfID, tagName, selected);
    }
}
