package UIController;

import Entities.User;
import UseCases.AllStudentsManager;
import UseCases.CourseDataManager;
import UseCases.UserDataManager;

import java.util.ArrayList;

public class AllStudentsUIControl {
    private AllStudentsManager allStudentsManager;
    private User user;
    public AllStudentsUIControl(User user, CourseDataManager courseManager, UserDataManager userManager) {
        this.allStudentsManager = new AllStudentsManager(courseManager, userManager);
        this.user = user;
    }

    public ArrayList<String> getAllStudents() {
        return allStudentsManager.getAllStudents();
    }
    public ArrayList<String> getAdminIDs() {
        return allStudentsManager.getAdminIDs();
    }
}
