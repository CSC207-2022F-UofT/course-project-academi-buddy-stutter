package UIController;

import Entities.User;
import UseCases.CommonSessionManager;
import UseCases.CourseDataCloud;
import UseCases.UserDataCloud;

public class CommonSessionUIControl {

    private CommonSessionManager commonSessionManager;


    private User self;

    public CommonSessionUIControl(User self, CourseDataCloud courseDataManager, UserDataCloud userDataManager){
        this.self = self;
        this.commonSessionManager = new CommonSessionManager(courseDataManager, userDataManager);
    }

    public String getCommonSessions(String targetUserID){
        return commonSessionManager.getCommonSessions(self.getUserID(), targetUserID);
    }

    public String getName(String targetUserID){
        return commonSessionManager.getName(targetUserID);
    }

    public int getNumberOfCommonSessions(){
        return commonSessionManager.getNumberOfCommonSessions();
    }
}
