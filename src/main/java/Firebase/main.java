package Firebase;

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
        FirebaseCollection fb = new FirebaseCollection("courses");
        fb.addEntry("CSC207", "Week", "Mon");
        fb.addEntry("CSC209", "Week", "Tue");
        FirebaseCollection us = new FirebaseCollection("users");
        us.addEntry("SX", "Name", "Anon");

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