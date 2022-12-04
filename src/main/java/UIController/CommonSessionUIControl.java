package UIController;

import Entities.Student;
import Entities.User;
import GUI.CommonSessionFrame;
import UseCases.CommonSessionManager;
import UseCases.CourseDataManager;
import UseCases.UserDataManager;

import java.io.IOException;
import java.util.ArrayList;

public class CommonSessionUIControl {

    private CommonSessionManager commonSessionManager;


    private User self;

    public CommonSessionUIControl(User self, CourseDataManager courseDataManager, UserDataManager userDataManager){
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
