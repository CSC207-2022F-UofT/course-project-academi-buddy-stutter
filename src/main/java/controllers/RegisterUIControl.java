package controllers;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import usecases.RegisterManager;

import java.io.IOException;

/**
 * Implements RegisterUIControl for RegisterFrame
 */
public class RegisterUIControl {
    private RegisterManager registerManager;

    /**
     * Constructs RegisterUIControl
     * @param courseDatabase an instance of CourseDataManager
     * @param userDatabase an instance of UserDataManager
     */
    public RegisterUIControl(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess) {
        this.registerManager = new RegisterManager(courseDataAccess, userDataAccess, tagDataAccess);
    }

    /**
     * Try to register a new user into database
     * @param fullName user's full name
     * @param id user's user_id
     * @param password user's password
     * @param confirm user reenters password
     * @return whether user has been successfully register into database
     * @throws IOException fails to register user
     */
    public boolean attemptRegister(String fullName, String id, String password, String confirm) throws IOException {
        return registerManager.register(fullName, id, password, confirm);
    }

    /**
     * Updates user's Email and info
     * @param email new Email
     * @param info new Info
     */
    public void updateEmailAndInfo(String email, String info){
        registerManager.updateEmailAndInfo(email, info);
    }

    /**
     * Gives user warnings about password strength
     * @param password user's original password
     * @return Warning messages according to strength of user's original password
     */
    public String getWarningString(String password){
        return registerManager.getWarningString(password);
    }

}
