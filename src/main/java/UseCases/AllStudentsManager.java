package UseCases;

import java.io.IOException;
import java.util.ArrayList;

public class AllStudentsManager extends UseCase{
    public AllStudentsManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

    public ArrayList<String> getAllStudents() {
        return this.ub.getUserIDList();
    }
}
