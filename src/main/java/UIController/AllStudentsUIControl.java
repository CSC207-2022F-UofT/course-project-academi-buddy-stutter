package UIController;

import Entities.User;
import UseCases.AllStudentsManager;
import UseCases.CloudCourseData;
import UseCases.CloudUserData;

import java.util.ArrayList;

public class AllStudentsUIControl {
    private AllStudentsManager allStudentsManager;
    private User user;
    public AllStudentsUIControl(User user, CloudCourseData courseManager, CloudUserData userManager) {
        this.allStudentsManager = new AllStudentsManager(courseManager, userManager);
        this.user = user;
    }

    public ArrayList<String> getAllStudents() {
        return allStudentsManager.getAllStudents();
    }
}
