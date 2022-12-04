package UIController;

import UseCases.CloudCourseData;
import UseCases.ProfileManager;
import UseCases.CloudUserData;

public class ProfileDisplayUIControl {
    private ProfileManager profileManager;
    public ProfileDisplayUIControl(CloudCourseData courseDataManager, CloudUserData userDataManager){
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
