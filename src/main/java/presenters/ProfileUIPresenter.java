package presenters;

import database.accessinterfaces.CourseDataAccess;
import model.usecases.ProfileManager;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

/**
 * Implements ProfileUIPresenter for ProfileFrame
 */
public class ProfileUIPresenter {
    private final ProfileManager profileManager;
    private final String selfID;

    /**
     * Constructs ProfileUIPresenter
     * @param userID a user
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess an instance of UserDataManager
     */
    public ProfileUIPresenter(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.selfID = userID;
        this.profileManager = new ProfileManager(courseDataAccess, userDataAccess, tagDataAccess);
    }

    /**
     * @return user's full name
     */


    public String getName() {
        return profileManager.getName(selfID);
    }

    /**
     * @return user's Email
     */
    public String getEmail(){
        return profileManager.getUserEmail(selfID);
    }

    /**
     * @return user's courses
     */
    public String getCourse() {
        return profileManager.getCourseString(selfID);
    }

    /**
     * @return user's info
     */
    public String getInfo() {
        return profileManager.getUserInfo(selfID);
    }

    /**
     * updates Email
     * @param email new Email
     */
    public void updateEmail(String email) {
        profileManager.updateEmail(selfID, email);
    }

    /**
     * updates user's info
     * @param info new info
     */
    public void updateInfo(String info) {
        profileManager.updateInfo(selfID, info);
    }

    /**
     * @return user's id
     */
    public String getUserID() {
        return selfID;
    }
}
