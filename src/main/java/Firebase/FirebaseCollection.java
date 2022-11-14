package Firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseCollection{
    private String projectID = "group-106-project";
    private String serviceAccountFile = "service-account-file.json";
    private String collectionName;
    private Map<String, Object> data;
    private DocumentReference docRef;
    private Firestore db;
    public FirebaseCollection(String collectionName) throws IOException{
        this.collectionName = collectionName;
        db = FirestoreClient.getFirestore();
    }
    public List<QueryDocumentSnapshot> getDocumentList(){
        ApiFuture<QuerySnapshot> query = db.collection(collectionName).get();
        try{
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            return documents;
        }
        catch (InterruptedException e){
            System.out.println("InterruptionException");
        }
        catch (ExecutionException e){
            System.out.println("ExecutionException");
        }
        return null;
    }
    public boolean addEntry(String documentName, String key, String value) throws IOException{
        List<QueryDocumentSnapshot> currentDocuments = this.getDocumentList();
        DocumentReference docRef = db.collection(collectionName).document(documentName);
        Map<String, Object> data = new HashMap<>();
        if(!currentDocuments.contains(documentName)){
            data.put(key, value);
            ApiFuture<WriteResult> result = docRef.set(data);
            try{
                System.out.println("Update time : " + result.get().getUpdateTime());
                return true;
            }
            catch (InterruptedException e){
                System.out.println("Write failed: InterruptedException");
                return false;
            }
            catch (ExecutionException e2){
                System.out.println("Write failed: ExecutionException");
                return false;
            }
        }
        for (QueryDocumentSnapshot document : currentDocuments) {
            System.out.println(document.getId());
            System.out.println(document.getData());
            if(document.getId() == documentName){
                data = document.getData();
                data.put(key,value);
                ApiFuture<WriteResult> result = docRef.set(data);
                try{
                    System.out.println("Update time : " + result.get().getUpdateTime());
                    return true;
                }
                catch (InterruptedException e){
                    System.out.println("Write failed: InterruptedException");
                    return false;
                }
                catch (ExecutionException e2){
                    System.out.println("Write failed: ExecutionException");
                    return false;
                }
            }
        }
        return false;
    }



}
