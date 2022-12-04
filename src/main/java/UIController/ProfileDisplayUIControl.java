package UIController;

import UseCases.CourseDataCloud;
import UseCases.ProfileManager;
import UseCases.UserDataCloud;

public class ProfileDisplayUIControl {
    private ProfileManager profileManager;
    public ProfileDisplayUIControl(CourseDataCloud courseDataManager, UserDataCloud userDataManager){
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
