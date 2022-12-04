package UseCases;

import java.io.IOException;
import java.util.ArrayList;

public class AllStudentsManager extends UseCase{
    public AllStudentsManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

    public ArrayList<String> getAllStudents() {
        System.out.println("Requesting for student list");
        return this.ub.getUserIDList();
    }
}