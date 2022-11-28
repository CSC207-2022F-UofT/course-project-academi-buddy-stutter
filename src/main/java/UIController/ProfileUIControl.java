package UIController;

import Entities.User;
import UseCases.CourseDataManager;
import UseCases.ProfileManager;
import UseCases.UserDataManager;

public class ProfileUIControl {
    private ProfileManager profileManager;
    private User user;
    public ProfileUIControl(User user, CourseDataManager courseDataManager, UserDataManager userDataManager){
        this.user = user;
        this.profileManager = new ProfileManager(courseDataManager, userDataManager);
    }
    public String getName(){
        String userID = user.getUserID();
        return profileManager.getName(userID);
    }
    public String getEmail(){
        String userID = user.getUserID();
        return profileManager.getUserEmail(userID);
    }

    public String getCourse(){
        String userID = user.getUserID();
        return profileManager.getCourseString(userID);
    }
    public String getInfo(){
        String userID = user.getUserID();
        return profileManager.getUserInfo(userID);
    }
}
