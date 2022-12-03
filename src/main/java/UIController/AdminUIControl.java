package UIController;

import UseCases.AdminActionsManager;
import UseCases.CourseDataManager;
import UseCases.UserDataManager;

public class AdminUIControl {
    private AdminActionsManager adminActionsManager;


    public AdminUIControl(CourseDataManager courseDataManager, UserDataManager userDataManager){
        this.adminActionsManager = new AdminActionsManager(courseDataManager, userDataManager);

    }

    public boolean removeUser(String userID){
        return adminActionsManager.removeUser(userID);
    }
}
