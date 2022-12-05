package controllers;

import entities.Student;
import database.accessinterfaces.CourseDataAccess;
import usecases.LabelSelectManager;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import java.io.IOException;

/**
 * Implements LabelSelectUIControl
 */
public class LabelSelectUIControl {
    private String self;
    private LabelSelectManager labelSelectManager;

    /**
     * Constructs LabelSelectUIControl
     * @param self a user
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess an instance of UserDataManager
     */
    public LabelSelectUIControl(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.self = userID;
        this.labelSelectManager = new LabelSelectManager(courseDataAccess, userDataAccess, tagDataAccess);
    }

    /**
     * Get user's label
     * @param labelName name of label
     * @return label selected by user
     */
    public boolean getStudentLabelState(String labelName) throws IOException {
        Student stu = (Student) labelSelectManager.getUserByID(this.self);
        return labelSelectManager.getStudentLabelState(stu, labelName);
    }

    /**
     * Updates user label
     * @param labelName name of label
     * @param selected determines whether label is selected
     */
    public void updateStudentLabel(String labelName, boolean selected) throws IOException {
        Student stu = (Student) labelSelectManager.getUserByID(this.self);
        labelSelectManager.updateStudentLabel(stu, labelName, selected);
    }
}
