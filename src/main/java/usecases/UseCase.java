package usecases;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import entities.User;

import java.io.IOException;

/**
 * Parent class for other use cases.
 */
public class UseCase {

    protected CourseDataAccess cb;
    protected UserDataAccess ub;
    protected TagDataAccess tb;

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

    public User getUserByID(String id) throws IOException {
        return this.ub.getUserByID(id);
    }

}
