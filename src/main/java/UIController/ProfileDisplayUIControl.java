package UIController;

import UseCases.CourseDataManager;
import UseCases.ProfileManager;
import UseCases.UserDataManager;

public class ProfileDisplayUIControl {
    private ProfileManager profileManager;
    public ProfileDisplayUIControl(CourseDataManager courseDataManager, UserDataManager userDataManager){
        this.profileManager = new ProfileManager(courseDataManager, userDataManager);
    }

    public String getName(String userID){
        return profileManager.getName(userID);
    }
    public String getEmail(String userID){
        return profileManager.getUserEmail(userID);
    }
    public String getInfo(String userID){
        return profileManager.getUserInfo(userID);
    }
}
