package usecaseio;

import usecases.AllStudentsManager;
import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import java.util.ArrayList;

/**
 * Implements AllStudentsIO for AllStudentsFrame
 */
public class AllStudentsIO {
    private AllStudentsManager allStudentsManager;
    private String self;

    /**
     * Constructs AllStudentsIO
     * @param user a user
     * @param courseManager an instance of CourseDataManager
     * @param userManager an instance of UserDataManager
     */
    public AllStudentsIO(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess) {
        this.allStudentsManager = new AllStudentsManager(courseDataAccess, userDataAccess, tagDataAccess);
        this.self = userID;
    }

    /**
     * get all students
     * @return a list of all students
     */
    public ArrayList<String> getAllStudents() {
        return allStudentsManager.getAllStudents();
    }
    public ArrayList<String> getAdminIDs() {
        return allStudentsManager.getAdminIDs();
    }
}
