import Entities.Student;
import External.BiweeklyAPI;
import External.FirebaseAPI;
import GUI.*;
import UIController.UIController;
import UseCases.CalendarInterpreter;
import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void initializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("service-account-file.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("group-106-project")
                .build();
        FirebaseApp.initializeApp(options);
    }
    public static void main(String[] args) throws IOException{
        //essential for database classes

        initializeFirebase();
        FirebaseAPI cbc = new FirebaseAPI();
        FirebaseAPI ubc = new FirebaseAPI();
        FirebaseAPI tbc = new FirebaseAPI();
        UserDataManager ub = new UserDataManager(ubc);
        CourseDataManager cb = new CourseDataManager(cbc, ub);
        TagDataManager tb = new TagDataManager(tbc, ub);

<<<<<<< HEAD
//        Student student = new Student("567789", "qwerty", "John Doe", "A test subject");
//        Student student2 = new Student("23456", "qwerty", "John Doe2", "A test subject");
        UIController uiController = new UIController(null, cb, ub, tb);
        LoginFrame loginFrame = new LoginFrame(uiController);
=======
        Student student = new Student("567789", "qwerty", "John Doe", "A test subject");
        Student student2 = new Student("23456", "qwerty", "John Doe2", "A test subject");
        UIController uiController = new UIController(student, cb, ub, tb);
        //TagSelectFrame tagSelectFrame = new TagSelectFrame(uiController);
        //TagMatchFrame tm = new TagMatchFrame(uiController);
        //LabelSelectFrame lb = new LabelSelectFrame(uiController);
        ProfileFrame pf = new ProfileFrame(uiController);
>>>>>>> cf8a37a (Fixed an infinite loop in database)
    }

}
