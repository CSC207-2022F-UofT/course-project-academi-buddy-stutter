package UIController;

import UseCases.CloudCourseData;
import UseCases.ProfileManager;
import UseCases.CloudUserData;

/**
 * Implements ProfileDisplayUIControl for ProfileDisplayFrame
 */
public class ProfileDisplayUIControl {
    private ProfileManager profileManager;

    /**
     * Constructs ProfileDisplayUIControl
     * @param courseDataManager an instance of CourseDataManager
     * @param userDataManager an instance of UserDataManager
     */
    public ProfileDisplayUIControl(CloudCourseData courseDataManager, CloudUserData userDataManager){
        this.profileManager = new ProfileManager(courseDataManager, userDataManager);
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
