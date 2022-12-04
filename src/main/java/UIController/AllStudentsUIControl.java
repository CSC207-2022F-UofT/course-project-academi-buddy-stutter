package UIController;

import Entities.User;
import UseCases.AllStudentsManager;
import UseCases.CourseDataCloud;
import UseCases.UserDataCloud;

import java.util.ArrayList;

public class AllStudentsUIControl {
    private AllStudentsManager allStudentsManager;
    private User user;
    public AllStudentsUIControl(User user, CourseDataCloud courseManager, UserDataCloud userManager) {
        this.allStudentsManager = new AllStudentsManager(courseManager, userManager);
        this.user = user;
    }

    public ArrayList<String> getAllStudents() {
        return allStudentsManager.getAllStudents();
    }
}
