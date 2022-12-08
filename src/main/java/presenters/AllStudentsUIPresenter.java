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
    private final AllStudentsManager allStudentsManager;

    /**
     * Constructs AllStudentsUIPresenter
     *
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess   an instance of UserDataManager
     */
    public AllStudentsUIPresenter(CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess) {
        this.allStudentsManager = new AllStudentsManager(courseDataAccess, userDataAccess, tagDataAccess);
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
