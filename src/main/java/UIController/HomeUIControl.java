package UIController;

import Entities.Student;
import Entities.User;
import External.BiweeklyAPI;
import External.JavaxAPI;
import UseCases.CourseDataCloud;
import UseCases.UserDataCloud;
import UseCases.UploadManager;


public class HomeUIControl {



    private UploadManager uploadManager;
    private Student self;

    public HomeUIControl(User self, CourseDataCloud courseDatabase, UserDataCloud userDatabase){
        this.self = (Student) self;
        JavaxAPI javaxAPI = new JavaxAPI();
        BiweeklyAPI biweeklyAPI = new BiweeklyAPI();
        this.uploadManager = new UploadManager(courseDatabase, userDatabase, javaxAPI, biweeklyAPI);

    }


    public String getName(){
        return self.getFullName();
    }


}
