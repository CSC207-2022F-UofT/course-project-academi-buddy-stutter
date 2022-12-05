package controllers;

import usecases.*;
import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import entities.Student;

import java.io.IOException;

/**
 * Implements TagSelectUIControl for TagSelectFrame
 */
public class TagSelectUIControl {
    private TagSelectManager tagSelectManager;
    private String self;

    /**
     * Constructs TagSelectUIControl
     * @param userID a user
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess an instance of UserDataManager
     * @param tagDataAccess an instance of TagDataManager
     */
    public TagSelectUIControl(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.tagSelectManager = new TagSelectManager(courseDataAccess, userDataAccess, tagDataAccess);
        this.self = userID;
    }

    /**
     * Get user's tag state
     * @param tagName name of tags
     * @return whether a tag is selected or not
     */
    public boolean getStudentTagState(String tagName) throws IOException {
        Student stu = (Student) tagSelectManager.getUserByID(this.self);
        return tagSelectManager.getStudentTagState(stu, tagName);
    }

    /**
     * Updates user's tags
     * @param tagName name of tags
     * @param selected whether the tags are selected or not
     */
    public void updateStudentTag(String tagName, boolean selected) throws IOException {
        Student stu = (Student) tagSelectManager.getUserByID(this.self);
        tagSelectManager.updateStudentTag(stu, tagName, selected);
    }
}
