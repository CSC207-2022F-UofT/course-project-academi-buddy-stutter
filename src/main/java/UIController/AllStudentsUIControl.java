package UIController;

import Entities.User;
import UseCases.AllStudentsManager;
import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;

import java.util.ArrayList;

/**
 * Implements AllStudentsUIControl for AllStudentsFrame
 */
public class AllStudentsUIControl {
    private AllStudentsManager allStudentsManager;
    private String self;

    /**
     * Constructs AllStudentsUIControl
     * @param user a user
     * @param courseManager an instance of CourseDataManager
     * @param userManager an instance of UserDataManager
     */
    public AllStudentsUIControl(String userID, CourseDataManager courseDataManager, UserDataManager userDataManager, TagDataManager tagDataManager) {
        this.allStudentsManager = new AllStudentsManager(courseDataManager, userDataManager, tagDataManager);
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
