package UIController;

import Entities.User;
import UseCases.CourseDataManager;
import UseCases.ProfileManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;

/**
 * Implements ProfileDisplayUIControl for ProfileDisplayFrame
 */
public class ProfileDisplayUIControl {

    private String userID;
    private ProfileManager profileManager;

    /**
     * Constructs ProfileDisplayUIControl
     * @param courseDataManager an instance of CourseDataManager
     * @param userDataManager an instance of UserDataManager
     */
    public ProfileDisplayUIControl(String userID, CourseDataManager courseDataManager, UserDataManager userDataManager, TagDataManager tagDataManager){
        this.userID = userID;
        this.profileManager = new ProfileManager(courseDataManager, userDataManager, tagDataManager);
    }

    /**
     * Get full name of user
     * @param userID user id
     * @return user's full name
     */
    public String getName(String userID){
        return profileManager.getName(userID);
    }

    /**
     * Get user's Email
     * @param userID user id
     * @return user's Email
     */
    public String getEmail(String userID){
        return profileManager.getUserEmail(userID);
    }

    /**
     * Get user's info
     * @param userID user id
     * @return user's info
     */
    public String getInfo(String userID){
        return profileManager.getUserInfo(userID);
    }
}
