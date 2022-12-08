package presenters;

import model.usecases.AllStudentsManager;
import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import java.util.ArrayList;

/**
 * Implements AllStudentsUIPresenter for AllStudentsFrame
 */
public class AllStudentsUIPresenter {
    private AllStudentsManager allStudentsManager;
    private String self;

    /**
     * Constructs AllStudentsUIPresenter
     * @param userID a user
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess an instance of UserDataManager
     */
    public AllStudentsUIPresenter(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess) {
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
