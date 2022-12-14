package presenters;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.usecases.RegisterManager;

/**
 * Implements RegisterUIPresenter for RegisterFrame
 */
public class RegisterUIPresenter {
    private final RegisterManager registerManager;

    /**
     * Constructs RegisterUIPresenter
     *
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess   an instance of UserDataManager
     */
    public RegisterUIPresenter(CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess) {
        this.registerManager = new RegisterManager(courseDataAccess, userDataAccess, tagDataAccess);
    }

    /**
     * Try to register a new user into database
     * @param fullName user's full name
     * @param id user's user_id
     * @param password user's password
     * @param confirm user reenters password
     * @return whether user has been successfully register into database
     */
    public boolean attemptRegister(String fullName, String id, String password, String confirm) {
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
