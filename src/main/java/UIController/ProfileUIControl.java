package UIController;

import Entities.User;
import UseCases.CloudCourseData;
import UseCases.ProfileManager;
import UseCases.CloudUserData;

public class ProfileUIControl {
    private ProfileManager profileManager;
    private User user;
    public ProfileUIControl(User user, CloudCourseData courseDataManager, CloudUserData userDataManager){
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

    public void updateEmail(String email){
        String userID = user.getUserID();
        profileManager.updateEmail(userID, email);
    }
    public void updateInfo(String info){
        String userID = user.getUserID();
        profileManager.updateInfo(userID, info);
    }

    public String getUserID() {
        return user.getUserID();
    }
}
