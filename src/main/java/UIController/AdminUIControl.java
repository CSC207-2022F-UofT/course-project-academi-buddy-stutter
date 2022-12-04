package UIController;

import Entities.User;
import UseCases.AdminActionsManager;
import UseCases.CloudCourseData;
import UseCases.CloudTagData;
import UseCases.CloudUserData;

/**
 * Implements AdminUIControl for AdminFrame
 */
public class AdminUIControl {
    private User self;
    private AdminActionsManager adminActionsManager;

    /**
     * Constructs AdminUIControl
     * @param self a user
     * @param courseDataManager instance of CourseDataManager
     * @param userDataManager instance of UserDataManager
     * @param tagDataManager instance of TagDataManager
     */
    public AdminUIControl(User self, CloudCourseData courseDataManager, CloudUserData userDataManager, CloudTagData tagDataManager){
        this.self = self;
        this.adminActionsManager = new AdminActionsManager(courseDataManager, userDataManager, tagDataManager);

    }

    /**
     * Removes user from database
     * @param userID user to be removed
     * @return whether the user is successfully removed from database
     */
    public boolean removeUser(String userID){
        if(!self.getUserID().equals(userID)){
            return adminActionsManager.removeUser(userID);
        }
        return false;
    }

    /**
     * gets user's full name
     * @return user's full name
     */
    public String getName(){
        return self.getFullName();
    }
}
