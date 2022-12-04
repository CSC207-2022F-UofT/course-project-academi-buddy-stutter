package UseCases;

public class UseCase {

    protected CourseDataCloud cb;
    protected UserDataCloud ub;


    public UseCase(CourseDataCloud courseDatabase, UserDataCloud userDatabase){
        this.cb = courseDatabase;
        this.ub = userDatabase;
    }


}
