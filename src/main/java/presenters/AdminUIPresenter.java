package presenters;

import model.usecases.AdminActionsManager;
import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import java.io.IOException;

/**
 * Implements AdminUIPresenter for AdminFrame
 */
public class AdminUIPresenter {
    private String self;
    private AdminActionsManager adminActionsManager;

    /**
     * Constructs AdminUIPresenter
     * @param self a user
     * @param courseDataAccess instance of CourseDataManager
     * @param userDataAccess instance of UserDataManager
     * @param tagDataAccess instance of TagDataManager
     */
    public AdminUIPresenter(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.self = userID;
        this.adminActionsManager = new AdminActionsManager(courseDataAccess, userDataAccess, tagDataAccess);

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
