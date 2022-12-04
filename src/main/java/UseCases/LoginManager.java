package UseCases;

import Entities.User;

import java.io.IOException;

/**
 * Use case of operations on login.
 */
public class LoginManager extends UseCase{

    User activeUser = null;

    /**
     * Initializer
     * @param courseDatabase the course database.
     * @param userDatabase the user database.
     */
    public LoginManager(CourseDataManager courseDatabase, UserDataManager userDatabase){
        super(courseDatabase, userDatabase);
    }

    /**
     * To check if the user exists in the user database.
     * @param id the user id to check.
     * @return true if the user exists in the user database, false otherwise.
     */
    private boolean userExists(String id){
        return this.ub.existByID(id);
    }

    /**
     * The login operation of the program. If user id and password are matched and in database then return true
     * @param id The user id to check.
     * @param password The password to check
     * @return True if the id and password are established in the user database and matches, false otherwise.
     */
    public boolean login(String id, String password) throws IOException {

        if(!userExists(id)){
            return false;
        }else {
            this.activeUser = this.ub.getUserByID(id);
            return this.activeUser.getUserPassword().equals(password);
        }
    }

    /**
     * Get the active user.
     * @return the active user.
     */
    public User getActiveUser(){
        return activeUser;
    }

}