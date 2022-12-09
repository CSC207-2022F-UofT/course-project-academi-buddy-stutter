package presenters;

import database.accessinterfaces.CourseDataAccess;
import model.usecases.LoginManager;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

/**
 * Implements LoginUIPresenter for LoginFrame
 */
public class LoginUIPresenter {
    private final LoginManager loginManager;

    public String selfID;
    private int userType;
    public static final int STUDENT = 0;
    public static final int ADMIN = 1;

    /**
     * Constructs LoginUIPresenter
     *
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess   an instance of UserDataManager
     */
    public LoginUIPresenter(CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.loginManager = new LoginManager(courseDataAccess, userDataAccess, tagDataAccess);
    }

    /**
     * Try to log in with user id and password
     * @param id user id entered by user
     * @param password password entered by user
     * @return whether user successfully logged in
     */
    public boolean attemptLogin(String id, String password) {

        boolean loggedIn =  loginManager.login(id, password);
        selfID = loginManager.getActiveUserID();
        if(loggedIn){
            loadUser();
        }

        return loggedIn;
    }

    /**
     * set FrameNavigator's self to user
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
    public String getUserID() {
        return selfID;
    }

    public int getUserType(){
        return userType;
    }
}
