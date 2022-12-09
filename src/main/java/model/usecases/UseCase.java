package model.usecases;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.entities.User;

/**
 * Parent class for other use cases.
 */
public class UseCase {

    protected final CourseDataAccess cb;
    protected final UserDataAccess ub;
    protected final TagDataAccess tb;

    /**
     * Initializer.
     * @param courseDatabase The course database.
     * @param userDatabase The user database.
     */
    public UseCase(CourseDataAccess courseDatabase, UserDataAccess userDatabase, TagDataAccess tagDatabase){
        this.cb = courseDatabase;
        this.ub = userDatabase;
        this.tb = tagDatabase;
    }

    public User getUserByID(String id){
        return this.ub.getUserByID(id);
    }

}
