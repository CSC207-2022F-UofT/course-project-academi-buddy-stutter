package usecaseio;

import adapters.apis.BiweeklyAPI;
import adapters.apis.JavaxAPI;
import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import usecases.UploadManager;

import java.io.IOException;

/**
 * Implements HomeIO for HomeFrame
 */
public class HomeIO {
    private UploadManager uploadManager;
    private String self;

    /**
     * Constructs HomeIO
     * @param self a user
     * @param courseDatabase an instance of CourseDataManager
     * @param userDatabase an instance of UserDataManager
     */
    public HomeIO(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
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
