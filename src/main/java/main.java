import Database.*;
import Sessions.Course;
import Users.Student;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

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
        initializeFirebase();
        FirebaseCollection cbc = new FirebaseCollection();
        FirebaseCollection ubc = new FirebaseCollection();
        cbc.initialize("course");
        ubc.initialize("users");
        CourseDatabase cb = new CourseDatabase(cbc);
        UserDatabase ub = new UserDatabase(ubc);
        List<String> CSCInfo = CalendarInterpreter.getSummary(CalendarInterpreter.readCalendar("coursesCalendar.ics")).get(0);
        Course CSC = new Course(CSCInfo.get(0), CSCInfo.get(1), CSCInfo.get(2), CSCInfo.get(3), CSCInfo.get(4), CSCInfo.get(5), CSCInfo.get(6));
        Student s1 = new Student("123", "password", "s1", "none");
        Student s2 = new Student("124", "password", "s2", "none");
        CSC.addStudent(s1);
        CSC.addStudent(s2);
        cb.addCourse(CSC);
        cb.removeStudent(CSC, s1);
        cb.getCourse(ub, "CSC207H1");
        Integer i = Integer.valueOf(1);
        System.out.println(i+1);



    }

}

/*
public class main {

    public static void main(String[] args) throws IOException{
        FileInputStream serviceAccount = new FileInputStream("service-account-file.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("group-106-project")
                .build();
        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection("courses").document("CSC207");
        Map<String, Object> data = new HashMap<>();
        data.put("CSC207", "Mon");
        ApiFuture<WriteResult> result = docRef.set(data);
        try{
            System.out.println("Update time : " + result.get().getUpdateTime());
        }
        catch (InterruptedException e){
            System.out.println("failed");
        }
        catch (ExecutionException e2){
            System.out.println("failed");
        }
    }

}

 */