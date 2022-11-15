package Firebase;

import Sessions.Course;
import Users.Student;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class main {
    public static void initializeFirebase() throws FileNotFoundException, IOException {
        FileInputStream serviceAccount = new FileInputStream("service-account-file.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("group-106-project")
                .build();
        FirebaseApp.initializeApp(options);
    }
    public static void main(String[] args) throws IOException{
        initializeFirebase();
        courseDatabase cb = new courseDatabase();
        List<String> CSCInfo = Import.getSummary(Import.readCalendar("coursesCalendar.ics")).get(0);
        Course CSC = new Course(CSCInfo.get(0), CSCInfo.get(1), CSCInfo.get(2), CSCInfo.get(3), CSCInfo.get(4), CSCInfo.get(5), CSCInfo.get(6));
        Student s1 = new Student("123", "password", "s1", "none");
        Student s2 = new Student("124", "password", "s2", "none");
        CSC.addStudent(s1);
        CSC.addStudent(s2);
        cb.addCourse(CSC);
        cb.removeStudent(CSC, s1);
        String a = CSC.getEnrolledStudents().toString();



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