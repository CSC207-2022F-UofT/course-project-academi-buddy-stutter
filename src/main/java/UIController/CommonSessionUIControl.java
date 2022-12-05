package UIController;

import Entities.Student;
import Entities.User;
import UseCases.CommonSessionManager;
import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;

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
     * @param courseDataManager an instance of CourseDataManager
     * @param userDataManager an instance of UserDataManager
     */
    public CommonSessionUIControl(String userID, CourseDataManager courseDataManager, UserDataManager userDataManager, TagDataManager tagDataManager){
        this.self = userID;
        this.commonSessionManager = new CommonSessionManager(courseDataManager, userDataManager, tagDataManager);
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
