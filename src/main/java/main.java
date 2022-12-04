import Entities.Student;
import External.FirebaseAPI;
import GUI.*;
import UIController.UIController;
import UseCases.CloudCourseData;
import UseCases.CloudTagData;
import UseCases.CloudUserData;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

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
        CloudUserData ub = new CloudUserData(ubc);
        CloudCourseData cb = new CloudCourseData(cbc, ub);
        CloudTagData tb = new CloudTagData(tbc, ub);

        Student student = new Student("567789", "qwerty", "John Doe", "A test subject");
//        Student student2 = new Student("23456", "qwerty", "John Doe2", "A test subject");
        UIController uiController = new UIController(null, cb, ub, tb);
        LoginFrame loginFrame = new LoginFrame(uiController);
        //FriendListFrame friendListFrame = new FriendListFrame(uiController);

    }

}
