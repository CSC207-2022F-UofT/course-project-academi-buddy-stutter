package UIController;

import Entities.Student;
import Entities.User;
import External.BiweeklyAPI;
import External.JavaxAPI;
import UseCases.CourseDataManager;
import UseCases.UploadManager;
import UseCases.UserDataManager;

import java.io.IOException;

public class FileUploadUIControl {

    private UploadManager uploadManager;
    private Student self;

    public FileUploadUIControl(User self, CourseDataManager courseDatabase, UserDataManager userDatabase){
        this.self = (Student) self;
        JavaxAPI javaxAPI = new JavaxAPI();
        BiweeklyAPI biweeklyAPI = new BiweeklyAPI();
        this.uploadManager = new UploadManager(courseDatabase, userDatabase, javaxAPI, biweeklyAPI);

    }


    public boolean upload(){
        return this.uploadManager.upload();
    }

    public void updateDatabase() throws IOException {
        this.uploadManager.updateDatabase(this.self);
    }

    public void reuploadCalendar() throws IOException{
        this.uploadManager.reuploadUpdateDatabase(this.self);
    }

    public void copyFileToPath() throws IOException {
        this.uploadManager.copyFileToPath();
    }


}
