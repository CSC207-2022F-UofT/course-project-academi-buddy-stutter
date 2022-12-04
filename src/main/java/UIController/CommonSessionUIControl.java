package UIController;

import Entities.Student;
import Entities.User;
import GUI.CommonSessionFrame;
import UseCases.CommonSessionManager;
import UseCases.CourseDataManager;
import UseCases.UserDataManager;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Implements CommonSessionUIControl for CommonSessionFrame
 */
public class CommonSessionUIControl {
    private CommonSessionManager commonSessionManager;
    private User self;

    /**
     * Constructs CommonSessionUIControl
     * @param self a suer
     * @param courseDataManager an instance of CourseDataManager
     * @param userDataManager an instance of UserDataManager
     */
    public CommonSessionUIControl(User self, CourseDataManager courseDataManager, UserDataManager userDataManager){
        this.self = self;
        this.commonSessionManager = new CommonSessionManager(courseDataManager, userDataManager);
    }

    /**
     * Get Common sessions between 2 users
     * @param targetUserID the user that we want to compare to
     * @return Common sessions with targetUserID
     */
    public String getCommonSessions(String targetUserID){
        return commonSessionManager.getCommonSessions(self.getUserID(), targetUserID);
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
