package Firebase;

import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException{
        FirebaseCollection fb = new FirebaseCollection("courses");
        fb.addEntry("CSC207", "Week", "Mon");
        fb.addEntry("CSC209", "Week", "Tue");

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