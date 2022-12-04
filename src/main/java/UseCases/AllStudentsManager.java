package UseCases;

import java.util.ArrayList;

public class AllStudentsManager extends UseCase{

    /**
     * Initiator
     * @param courseDatabase the course database
     * @param userDatabase the user database
     */
    public AllStudentsManager(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        super(courseDatabase, userDatabase);
    }

    /**
     * Getting all students in a user database.
     * @return An ArrayList of Strings of user ids in the database.
     */
    public ArrayList<String> getAllStudents() {
        System.out.println("Requesting for student list");
        return this.ub.getUserIDList();
    }
}