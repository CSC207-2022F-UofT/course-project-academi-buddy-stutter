package presenters;

import adapters.apis.BiweeklyAPI;
import adapters.apis.JavaxAPI;
import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.usecases.UploadManager;

/**
 * Implements HomeUIPresenter for HomeFrame
 */
public class HomeUIPresenter {
    private final UploadManager uploadManager;
    private final String self;

    /**
     * Constructs HomeUIPresenter
     * @param userID a user
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess an instance of UserDataManager
     */
    public HomeUIPresenter(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.self = userID;
        JavaxAPI javaxAPI = new JavaxAPI();
        BiweeklyAPI biweeklyAPI = new BiweeklyAPI();
        this.uploadManager = new UploadManager(courseDataAccess, userDataAccess, tagDataAccess, javaxAPI, biweeklyAPI);

    }

    /**
     * @return user's full name
     */
    public String getName() {
        return uploadManager.getName(self);
    }
}
