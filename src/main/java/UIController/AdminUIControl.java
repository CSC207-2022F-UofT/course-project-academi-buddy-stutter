package UIController;

import Entities.User;
import UseCases.AdminActionsManager;
import UseCases.CloudCourseData;
import UseCases.CloudTagData;
import UseCases.CloudUserData;

public class AdminUIControl {
    private User self;
    private AdminActionsManager adminActionsManager;


    public AdminUIControl(User self, CloudCourseData courseDataManager, CloudUserData userDataManager, CloudTagData tagDataManager){
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
