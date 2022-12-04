package UseCases;

import java.util.ArrayList;

public class AllStudentsManager extends UseCase{
    public AllStudentsManager(CourseDataCloud courseDatabase, UserDataCloud userDatabase) {
        super(courseDatabase, userDatabase);
    }

    public ArrayList<String> getAllStudents() {
        System.out.println("Requesting for student list");
        return this.ub.getUserIDList();
    }
}
