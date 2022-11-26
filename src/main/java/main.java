import Database.*;
import Sessions.Course;
import Users.Student;
import GUI.*;
import clojure.lang.IFn;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.checkerframework.checker.guieffect.qual.UI;


import java.io.FileInputStream;
import java.io.IOException;
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


        //test
        List<String> CSCInfo = CalendarInterpreter.getSummary(CalendarInterpreter.readCalendar("coursesCalendar.ics")).get(0);
        Course CSC = new Course(CSCInfo.get(0), CSCInfo.get(1), CSCInfo.get(2), CSCInfo.get(3), CSCInfo.get(4), CSCInfo.get(5), CSCInfo.get(6));
        Student s1 = new Student("789", "password", "s1", "none");
        Student s2 = new Student("124", "password", "s2", "none");
        CSC.addStudent(s1);
        CSC.addStudent(s2);
        cb.addCourse(CSC);
        cb.removeStudent(CSC, s1);
        cb.getCourse("CSC300H1");
        ub.addUser(s1);
        Integer i = Integer.valueOf(1);
        System.out.println(i+1);


        // intialize UI Controller
        UIController uiController = new UIController(null, cb, ub);
        uiController.toLogin();



    }

}
