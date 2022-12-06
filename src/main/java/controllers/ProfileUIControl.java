package controllers;

import database.accessinterfaces.CourseDataAccess;
import usecases.ProfileManager;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import java.io.IOException;

/**
 * Implements ProfileUIControl for ProfileFrame
 */
public class ProfileUIControl {
    private ProfileManager profileManager;
    private String selfID;

    /**
     * Constructs ProfileUIControl
     * @param user a user
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess an instance of UserDataManager
     */
    public ProfileUIControl(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.selfID = userID;
        this.profileManager = new ProfileManager(courseDataAccess, userDataAccess, tagDataAccess);
    }

    /**
     * @return user's full name
     */


    public String getName() throws IOException {
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
    public String getCourse() throws IOException {
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
    public void updateEmail(String email) throws IOException {
        profileManager.updateEmail(selfID, email);
    }

    /**
     * updates user's info
     * @param info new info
     */
    public void updateInfo(String info) throws IOException {
        profileManager.updateInfo(selfID, info);
    }

    /**
     * @return user's id
     */
    public String getUserID() throws IOException {
        return selfID;
    }
}
