package UseCases;

public class UseCase {

    protected CourseDataManager cb;
    protected UserDataManager ub;


    public UseCase(CourseDataManager courseDatabase, UserDataManager userDatabase){
        this.cb = courseDatabase;
        this.ub = userDatabase;
    }


}
