package UseCases;

public class UseCase {

    public CourseManager cb;
    public UserManager ub;

    public UseCase(CourseManager courseDatabase, UserManager userDatabase){
        this.cb = courseDatabase;
        this.ub = userDatabase;
    }


}