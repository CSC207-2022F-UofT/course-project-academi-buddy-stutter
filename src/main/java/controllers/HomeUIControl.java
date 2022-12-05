package controllers;

import externaladapters.BiweeklyAPI;
import externaladapters.JavaxAPI;
import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import usecases.UploadManager;

import java.io.IOException;

/**
 * Implements HomeUIControl for HomeFrame
 */
public class HomeUIControl {
    private UploadManager uploadManager;
    private String self;

    /**
     * Constructs HomeUIControl
     * @param self a user
     * @param courseDatabase an instance of CourseDataManager
     * @param userDatabase an instance of UserDataManager
     */
    public HomeUIControl(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.self = userID;
        JavaxAPI javaxAPI = new JavaxAPI();
        BiweeklyAPI biweeklyAPI = new BiweeklyAPI();
        this.uploadManager = new UploadManager(courseDataAccess, userDataAccess, tagDataAccess, javaxAPI, biweeklyAPI);

    }

    /**
     * @return user's full name
     */
    public String getName() throws IOException {
        return uploadManager.getUserByID(self).getFullName();
    }
}
