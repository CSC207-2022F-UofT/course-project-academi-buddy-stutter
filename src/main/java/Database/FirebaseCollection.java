package Database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import com.google.firebase.cloud.FirestoreClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseCollection implements iFirebaseCollection {
    private String collectionName;
    private Firestore db;
    public FirebaseCollection(){
        this.collectionName = "temp";
        db = FirestoreClient.getFirestore();
    }

    public void initialize(String collectionName){
        this.collectionName = collectionName;
    }
    public List<QueryDocumentSnapshot> getDocumentList(){
        ApiFuture<QuerySnapshot> query = db.collection(collectionName).get();
        try{
            QuerySnapshot querySnapshot = query.get();
            return querySnapshot.getDocuments();
        }
        catch (InterruptedException e){
            System.out.println("InterruptionException");
        }
        catch (ExecutionException e){
            System.out.println("ExecutionException");
        }
        return null;
    }

    public boolean addEntry(String documentName, String key, Object value){
        List<QueryDocumentSnapshot> currentDocuments = this.getDocumentList();
        DocumentReference docRef = db.collection(collectionName).document(documentName);
        Map<String, Object> data = new HashMap<>();
        for (QueryDocumentSnapshot document : currentDocuments) {
            System.out.println(document.getId());
            if(document.getId().equals(documentName)){
                Map<String, Object> currentData = document.getData();
                for(String k: currentData.keySet()){
                    data.put(k, currentData.get(k));
                }
                data.put(key, value);
            }
        }
        ApiFuture<WriteResult> result = docRef.set(data);
        return printUpdateResult(result);
    }

    public Map<String, Object> getEntry(String documentName){
        HashMap<String, Object> entry = new HashMap<>();
        List<QueryDocumentSnapshot> currentDocuments = this.getDocumentList();
        for (QueryDocumentSnapshot document : currentDocuments) {
            if(document.getId().equals(documentName)){
                return document.getData();
            }
        }
        return entry;
    }

    public boolean removeEntry(String documentName){
        DocumentReference docRef = db.collection(collectionName).document(documentName);

        ApiFuture<WriteResult> result = docRef.delete();
        return printUpdateResult(result);
    }

    private boolean printUpdateResult(ApiFuture<WriteResult> result) {
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
