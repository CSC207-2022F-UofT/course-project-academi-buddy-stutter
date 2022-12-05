package controllers;

import entities.Student;
import usecases.CommonSessionManager;
import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import java.io.IOException;

/**
 * Implements CommonSessionUIControl for CommonSessionFrame
 */
public class CommonSessionUIControl {
    private CommonSessionManager commonSessionManager;
    private String self;

    /**
     * Constructs CommonSessionUIControl
     * @param self a suer
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess an instance of UserDataManager
     */
    public CommonSessionUIControl(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.self = userID;
        this.commonSessionManager = new CommonSessionManager(courseDataAccess, userDataAccess, tagDataAccess);
    }

    /**
     * Get Common sessions between 2 users
     * @param targetUserID the user that we want to compare to
     * @return Common sessions with targetUserID
     */
    public String getCommonSessions(String targetUserID) throws IOException {
        Student stu = (Student) commonSessionManager.getUserByID(this.self);
        return commonSessionManager.getCommonSessions(stu.getUserID(), targetUserID);
    }

    /**
     * Get target user's name from targetUserID
     * @param targetUserID the user that want to get full name of
     * @return target user's full name
     */
    public String getName(String targetUserID){
        return commonSessionManager.getName(targetUserID);
    }

    /**
     * Get Number of Common Sessions
     * @return the number of common sessions
     */
    public int getNumberOfCommonSessions(){
        return commonSessionManager.getNumberOfCommonSessions();
    }
}
