package presenters;

import adapters.apis.BiweeklyAPI;
import adapters.apis.JavaxAPI;
import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import model.usecases.UploadManager;
import database.accessinterfaces.UserDataAccess;

import java.io.IOException;

/**
 * Implements FileUploadUIPresenter for FileUploadFrame
 */
public class FileUploadUIPresenter {
    private UploadManager uploadManager;
    private String selfID;

    /**
     * Constructs FileUploadUIPresenter
     * @param userID a user
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess an instance of UserDataManager
     */
    public FileUploadUIPresenter(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.selfID = userID;
        JavaxAPI javaxAPI = new JavaxAPI();
        BiweeklyAPI biweeklyAPI = new BiweeklyAPI();
        this.uploadManager = new UploadManager(courseDataAccess, userDataAccess, tagDataAccess, javaxAPI, biweeklyAPI);

    }

    /**
     * @return whether the file is successfully updated or not
     */
    public boolean upload(){
        return this.uploadManager.upload();
    }

    /**
     * updates database with imported calendar (updates courses)
     * @throws IOException that it fails to update database
     */
    public void updateDatabase() throws IOException {
        this.uploadManager.updateDatabase(selfID);
    }

    /**
     * allows registered users to re-upload their calendars
     * @throws IOException that it fails to upload calendar
     */
    public void reuploadCalendar() throws IOException{
        this.uploadManager.reUploadUpdateDatabase(selfID);
    }

    /**
     * Copies filepath of the calendar
     * @throws IOException it fails to copy filepath
     */
    public void copyFileToPath() throws IOException {
        this.uploadManager.copyFileToPath();
    }
}
