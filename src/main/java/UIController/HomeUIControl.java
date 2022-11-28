package UIController;

import External.JavaxAPI;
import Gateways.UploaderInterface;
import UseCases.CourseDataManager;
import UseCases.UserDataManager;
import UseCases.UploadManager;

import java.io.IOException;


public class HomeUIControl {



    private UploadManager uploadManager;

    public HomeUIControl(CourseDataManager courseDatabase, UserDataManager userDatabase){

        JavaxAPI javaxAPI = new JavaxAPI();
        this.uploadManager = new UploadManager(courseDatabase, userDatabase, javaxAPI);

    }

    public boolean upload(){
        return this.uploadManager.upload();
    }

    public void copyFileToPath() throws IOException {
        this.uploadManager.copyFileToPath();
    }


}
