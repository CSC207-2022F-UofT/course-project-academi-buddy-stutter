import Database.*;
import GUI.LoginFrame;
import GUI.TagMatchFrame;
import GUI.TagSelectFrame;
import GUI.UIController;
import Sessions.Course;
import Users.InterestTag;
import Users.Student;
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
        FirebaseCollection cbc = new FirebaseCollection();
        FirebaseCollection ubc = new FirebaseCollection();
        FirebaseCollection tbc = new FirebaseCollection();
        UserManager ub = new UserManager(ubc);
        CourseManager cb = new CourseManager(cbc, ub);
        TagManager tb = new TagManager(tbc, ub);

        Student student = new Student("12345", "qwerty", "John Doe", "A test subject");
        Student student2 = new Student("23456", "qwerty", "John Doe2", "A test subject");
        UIController uiController = new UIController(student2, cb, ub, tb);
        //TagSelectFrame tagSelectFrame = new TagSelectFrame(uiController);
        ub.addStudentUser(student);
        InterestTag tag = new InterestTag("Adventure");
        //tb.addTag(tag);
        tb.addStudent(tag, student);
        TagMatchFrame tagMatchFrame = new TagMatchFrame(uiController);


    }

}
