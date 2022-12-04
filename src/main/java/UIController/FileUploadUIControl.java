package UIController;

import Entities.Student;
import Entities.User;
import External.BiweeklyAPI;
import External.JavaxAPI;
import UseCases.CloudCourseData;
import UseCases.UploadManager;
import UseCases.CloudUserData;

import java.io.IOException;

/**
 * Implements FileUploadUIControl for FileUploadFrame
 */
public class FileUploadUIControl {
    private UploadManager uploadManager;
    private Student self;

    /**
     * Constructs FileUploadUIControl
     * @param self a user
     * @param courseDatabase an instance of CourseDataManager
     * @param userDatabase an instance of UserDataManager
     */
    public FileUploadUIControl(User self, CloudCourseData courseDatabase, CloudUserData userDatabase){
        this.self = (Student) self;
        JavaxAPI javaxAPI = new JavaxAPI();
        BiweeklyAPI biweeklyAPI = new BiweeklyAPI();
        this.uploadManager = new UploadManager(courseDatabase, userDatabase, javaxAPI, biweeklyAPI);

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
        this.uploadManager.updateDatabase(this.self);
    }

    /**
     * allows registered users to re-upload their calendars
     * @throws IOException that it fails to upload calendar
     */
    public void reuploadCalendar() throws IOException{
        this.uploadManager.reuploadUpdateDatabase(this.self);
    }

    /**
     * Copies filepath of the calendar
     * @throws IOException it fails to copy filepath
     */
    public void copyFileToPath() throws IOException {
        this.uploadManager.copyFileToPath();
    }
}
