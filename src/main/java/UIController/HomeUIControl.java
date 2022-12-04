package UIController;

import Entities.Student;
import Entities.User;
import External.BiweeklyAPI;
import External.JavaxAPI;
import UseCases.CloudCourseData;
import UseCases.CloudUserData;
import UseCases.UploadManager;

import java.io.IOException;

/**
 * Implements HomeUIControl for HomeFrame
 */
public class HomeUIControl {
    private UploadManager uploadManager;
    private Student self;

    /**
     * Constructs HomeUIControl
     * @param self a user
     * @param courseDatabase an instance of CourseDataManager
     * @param userDatabase an instance of UserDataManager
     */
    public HomeUIControl(User self, CloudCourseData courseDatabase, CloudUserData userDatabase){
        this.self = (Student) self;
        JavaxAPI javaxAPI = new JavaxAPI();
        BiweeklyAPI biweeklyAPI = new BiweeklyAPI();
        this.uploadManager = new UploadManager(courseDatabase, userDatabase, javaxAPI, biweeklyAPI);

    }

    /**
     * @return user's full name
     */
    public String getName(){
        return self.getFullName();
    }
}
