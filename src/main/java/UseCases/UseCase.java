package UseCases;

/**
 * Parent class for other use cases.
 */
public class UseCase {

    protected CourseDataManager cb;
    protected UserDataManager ub;

    /**
     * Initializer.
     * @param courseDatabase The course database.
     * @param userDatabase The user database.
     */
    public UseCase(CourseDataManager courseDatabase, UserDataManager userDatabase){
        this.cb = courseDatabase;
        this.ub = userDatabase;
    }


}
