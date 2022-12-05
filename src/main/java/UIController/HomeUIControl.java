package UIController;

import Entities.Student;
import External.BiweeklyAPI;
import External.JavaxAPI;
import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;
import UseCases.UploadManager;

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
    public HomeUIControl(String userID, CourseDataManager courseDataManager, UserDataManager userDataManager, TagDataManager tagDataManager){
        this.self = userID;
        JavaxAPI javaxAPI = new JavaxAPI();
        BiweeklyAPI biweeklyAPI = new BiweeklyAPI();
        this.uploadManager = new UploadManager(courseDataManager, userDataManager, tagDataManager, javaxAPI, biweeklyAPI);

    }

    /**
     * @return user's full name
     */
    public String getName() throws IOException {
        return uploadManager.getUserByID(self).getFullName();
    }
}
