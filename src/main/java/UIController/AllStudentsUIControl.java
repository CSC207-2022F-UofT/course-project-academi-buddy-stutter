package UIController;

import Entities.User;
import UseCases.AllStudentsManager;
import UseCases.CourseDataManager;
import UseCases.UserDataManager;

import java.util.ArrayList;

/**
 * Implements AllStudentsUIControl for AllStudentsFrame
 */
public class AllStudentsUIControl {
    private AllStudentsManager allStudentsManager;
    private User user;

    /**
     * Constructs AllStudentsUIControl
     * @param user a user
     * @param courseManager an instance of CourseDataManager
     * @param userManager an instance of UserDataManager
     */
    public AllStudentsUIControl(User user, CourseDataManager courseManager, UserDataManager userManager) {
        this.allStudentsManager = new AllStudentsManager(courseManager, userManager);
        this.user = user;
    }

    /**
     * get all students
     * @return a list of all students
     */
    public ArrayList<String> getAllStudents() {
        return allStudentsManager.getAllStudents();
    }
}
