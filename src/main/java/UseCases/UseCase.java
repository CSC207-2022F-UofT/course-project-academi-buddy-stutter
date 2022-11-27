package UseCases;

public class UseCase {

    public CourseDataManager cb;
    public UserDataManager ub;


    public UseCase(CourseDataManager courseDatabase, UserDataManager userDatabase){
        this.cb = courseDatabase;
        this.ub = userDatabase;
    }


}
