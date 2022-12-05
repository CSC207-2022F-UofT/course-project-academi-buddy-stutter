package UseCases;

import Entities.User;

import java.io.IOException;

/**
 * Parent class for other use cases.
 */
public class UseCase {

    protected CourseDataManager cb;
    protected UserDataManager ub;
    protected TagDataManager tb;

    /**
     * Initializer.
     * @param courseDatabase The course database.
     * @param userDatabase The user database.
     */
    public UseCase(CourseDataManager courseDatabase, UserDataManager userDatabase, TagDataManager tagDatabase){
        this.cb = courseDatabase;
        this.ub = userDatabase;
        this.tb = tagDatabase;
    }

    public User getUserByID(String id) throws IOException {
        return this.ub.getUserByID(id);
    }

}
