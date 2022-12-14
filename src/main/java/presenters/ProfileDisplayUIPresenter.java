package presenters;

import database.accessinterfaces.CourseDataAccess;
import model.usecases.ProfileManager;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

/**
 * Implements ProfileDisplayUIPresenter for ProfileDisplayFrame
 */
public class ProfileDisplayUIPresenter {

    private final ProfileManager profileManager;

    /**
     * Constructs ProfileDisplayUIPresenter
     *
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess   an instance of UserDataManager
     */
    public ProfileDisplayUIPresenter(CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.profileManager = new ProfileManager(courseDataAccess, userDataAccess, tagDataAccess);
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
