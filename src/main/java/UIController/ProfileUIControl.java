package UIController;

import Entities.User;
import UseCases.CourseDataManager;
import UseCases.ProfileManager;
import UseCases.UserDataManager;

/**
 * Implements ProfileUIControl for ProfileFrame
 */
public class ProfileUIControl {
    private ProfileManager profileManager;
    private User user;

    /**
     * Constructs ProfileUIControl
     * @param user a user
     * @param courseDataManager an instance of CourseDataManager
     * @param userDataManager an instance of UserDataManager
     */
    public ProfileUIControl(User user, CourseDataManager courseDataManager, UserDataManager userDataManager){
        this.user = user;
        this.profileManager = new ProfileManager(courseDataManager, userDataManager);
    }

    /**
     * @return user's full name
     */
    public String getName(){
        String userID = user.getUserID();
        return profileManager.getName(userID);
    }

    /**
     * @return user's Email
     */
    public String getEmail(){
        String userID = user.getUserID();
        return profileManager.getUserEmail(userID);
    }

    /**
     * @return user's courses
     */
    public String getCourse(){
        String userID = user.getUserID();
        return profileManager.getCourseString(userID);
    }

    /**
     * @return user's info
     */
    public String getInfo(){
        String userID = user.getUserID();
        return profileManager.getUserInfo(userID);
    }

    /**
     * updates Email
     * @param email new Email
     */
    public void updateEmail(String email){
        String userID = user.getUserID();
        profileManager.updateEmail(userID, email);
    }

    /**
     * updates user's info
     * @param info new info
     */
    public void updateInfo(String info){
        String userID = user.getUserID();
        profileManager.updateInfo(userID, info);
    }

    /**
     * @return user's id
     */
    public String getUserID() {
        return user.getUserID();
    }
}
