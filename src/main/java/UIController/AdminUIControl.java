package UIController;

import Entities.User;
import UseCases.AdminActionsManager;
import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;

public class AdminUIControl {
    private User self;
    private AdminActionsManager adminActionsManager;


    public AdminUIControl(User self, CourseDataManager courseDataManager, UserDataManager userDataManager, TagDataManager tagDataManager){
        this.self = self;
        this.adminActionsManager = new AdminActionsManager(courseDataManager, userDataManager, tagDataManager);

    }

    public boolean removeUser(String userID){
        if(!self.getUserID().equals(userID)){
            return adminActionsManager.removeUser(userID);
        }
        return false;
    }

    public String getName(){
        return self.getFullName();
    }
}
