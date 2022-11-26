import Database.*;
import Entities.Course;
import GUI.TagMatchFrame;
import UIController.UIController;
import Entities.Student;
import UseCases.CalendarInterpreter;
import UseCases.CourseManager;
import UseCases.TagManager;
import UseCases.UserManager;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

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

        CalendarAPI capi = new CalendarAPI();
        CalendarInterpreter ci = new CalendarInterpreter(capi);
        ArrayList<Course> courses = ci.getCourses(ci.readCalendar("TestCalendar"));
        for(int i = 0; i < courses.size(); i++){
            System.out.println(courses.get(i).getCourseCode());
            System.out.println(courses.get(i).getCourseName());
            System.out.println(courses.get(i).getCourseType());
            System.out.println(courses.get(i).getYear());
            System.out.println(courses.get(i).getSessionNumber());
            System.out.println(courses.get(i).getDayOfWeek());
        }

    }

}
