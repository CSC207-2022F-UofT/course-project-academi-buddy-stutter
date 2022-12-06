package controllers;

import database.accessinterfaces.CourseDataAccess;
import usecases.LoginManager;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import java.io.IOException;

/**
 * Implements LoginUIControl for LoginFrame
 */
public class LoginUIControl{
    private LoginManager loginManager;

    public String selfID;
    private int userType;
    public static int STUDENT = 0;
    public static int ADMIN = 1;

    /**
     * Constructs LoginUIControl
     * @param courseDatabase an instance of CourseDataManager
     * @param userDatabase an instance of UserDataManager
     */
    public LoginUIControl(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.loginManager = new LoginManager(courseDataAccess, userDataAccess, tagDataAccess);
    }

    /**
     * Try to log in with user id and password
     * @param id user id entered by user
     * @param password password entered by user
     * @return whether user successfully logged in
     * @throws IOException fails to log in
     */
    public boolean attemptLogin(String id, String password) throws IOException {

        boolean loggedIn =  loginManager.login(id, password);
        selfID = loginManager.getActiveUserID();
        if(loggedIn){
            loadUser();
        }

        return loggedIn;
    }

    /**
     * set UIController's self to user
     */
    private void loadUser(){
        String userTypeString = loginManager.getUserTypeString();
        if(userTypeString.equals("Student")){
            this.userType = STUDENT;
        }
        else if(userTypeString.equals("Admin")){
            this.userType = ADMIN;
        }
    }

    /**
     * @return current user
     */
    public String getUserID() throws IOException {
        return selfID;
    }

    public int getUserType(){
        return userType;
    }
}
