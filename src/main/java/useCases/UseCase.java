package useCases;

import Database.CourseManager;
import Database.UserManager;

public class UseCase {

    protected CourseManager cb;
    protected UserManager ub;

    public UseCase(CourseManager courseDatabase, UserManager userDatabase){
        this.cb = courseDatabase;
        this.ub = userDatabase;
    }


}
