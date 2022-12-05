package usecases;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import java.util.ArrayList;

public class AllStudentsManager extends UseCase{

    /**
     * Initiator
     * @param courseDatabase the course database
     * @param userDatabase the user database
     * @param tagDatabase the tag database
     */
    public AllStudentsManager(CourseDataAccess courseDatabase, UserDataAccess userDatabase, TagDataAccess tagDatabase) {
        super(courseDatabase, userDatabase, tagDatabase);
    }

    /**
     * Getting all students in a user database.
     * @return An ArrayList of Strings of user ids in the database.
     */
    public ArrayList<String> getAllStudents() {
        System.out.println("Requesting for student list");
        return this.ub.getUserIDList();
    }

    public ArrayList<String> getAdminIDs() {
        return this.ub.getAdminIDs();
    }
}