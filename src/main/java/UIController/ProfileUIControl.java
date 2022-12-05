package UIController;

import Entities.Student;
import Entities.User;
import UseCases.CourseDataManager;
import UseCases.ProfileManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;

import java.io.IOException;

/**
 * Implements ProfileUIControl for ProfileFrame
 */
public class ProfileUIControl {
    private ProfileManager profileManager;
    private String self;

    /**
     * Constructs ProfileUIControl
     * @param user a user
     * @param courseDataManager an instance of CourseDataManager
     * @param userDataManager an instance of UserDataManager
     */
    public ProfileUIControl(String userID, CourseDataManager courseDataManager, UserDataManager userDataManager, TagDataManager tagDataManager){
        this.self = userID;
        this.profileManager = new ProfileManager(courseDataManager, userDataManager, tagDataManager);
    }

    /**
     * @return user's full name
     */

    private Student getStudent() throws IOException {
        Student stu = (Student) profileManager.getUserByID(this.self);
        return stu;
    }

    public String getName() throws IOException {
        String userID = getStudent().getUserID();
        return profileManager.getName(userID);
    }

    /**
     * @return user's Email
     */
    public String getEmail() throws IOException {
        String userID = getStudent().getUserID();
        return profileManager.getUserEmail(userID);
    }

    /**
     * @return user's courses
     */
    public String getCourse() throws IOException {
        String userID = getStudent().getUserID();
        return profileManager.getCourseString(userID);
    }

    /**
     * @return user's info
     */
    public String getInfo() throws IOException {
        String userID = getStudent().getUserID();
        return profileManager.getUserInfo(userID);
    }

    /**
     * updates Email
     * @param email new Email
     */
    public void updateEmail(String email) throws IOException {
        String userID = getStudent().getUserID();
        profileManager.updateEmail(userID, email);
    }

    /**
     * updates user's info
     * @param info new info
     */
    public void updateInfo(String info) throws IOException {
        String userID = getStudent().getUserID();
        profileManager.updateInfo(userID, info);
    }

    /**
     * @return user's id
     */
    public String getUserID() throws IOException {
        return self;
    }
}
