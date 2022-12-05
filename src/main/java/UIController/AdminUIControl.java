package UIController;

import Entities.User;
import UseCases.AdminActionsManager;
import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;

import java.io.IOException;

/**
 * Implements AdminUIControl for AdminFrame
 */
public class AdminUIControl {
    private String self;
    private AdminActionsManager adminActionsManager;

    /**
     * Constructs AdminUIControl
     * @param self a user
     * @param courseDataManager instance of CourseDataManager
     * @param userDataManager instance of UserDataManager
     * @param tagDataManager instance of TagDataManager
     */
    public AdminUIControl(String userID, CourseDataManager courseDataManager, UserDataManager userDataManager, TagDataManager tagDataManager){
        this.self = userID;
        this.adminActionsManager = new AdminActionsManager(courseDataManager, userDataManager, tagDataManager);

    }

    /**
     * Removes user from database
     * @param userID user to be removed
     * @return whether the user is successfully removed from database
     */
    public boolean removeUser(String userID){
        if(!self.equals(userID)){
            return adminActionsManager.removeUser(userID);
        }
        return false;
    }

    /**
     * gets user's full name
     * @return user's full name
     */
    public String getName() throws IOException {
        return adminActionsManager.getUserByID(self).getFullName();
    }
}
