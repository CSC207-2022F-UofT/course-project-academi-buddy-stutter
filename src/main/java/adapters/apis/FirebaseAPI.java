package adapters.apis;

import adapters.gateways.DatabaseInterface;
import utilities.ReadCounter;
import utilities.WriteCounter;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import com.google.firebase.cloud.FirestoreClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseAPI implements DatabaseInterface {
    private String collectionName;
    private final Firestore db;
    private List<QueryDocumentSnapshot> currentDocuments;
    public FirebaseAPI(){
        this.collectionName = "temp";
        db = FirestoreClient.getFirestore();
    }

    public void openCollection(String collectionName){
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

    /**
     * update document list.
     */
    public void updateDocuments(){
        currentDocuments = getDocumentList();
    }

    public ArrayList<String> getDocumentStringList(){
        ArrayList<String> documentList = new ArrayList<>();
        updateDocuments();
        for(QueryDocumentSnapshot document: currentDocuments){
            documentList.add(document.getId());
        }
        return documentList;
    }
    public void addEntry(String documentName, String key, Object value){
        DocumentReference docRef = getDocRef(documentName);
        Map<String, Object> currentData = getEntry(documentName);
        Map<String, Object> data;
        ApiFuture<WriteResult> result;
        if(currentData == null){
            data = new HashMap<>();
        }
        else {
            data = currentData;
            if(data.containsKey(key)){
                result = docRef.update(key, value);
                WriteCounter.addCount();
                printUpdateResult(result);
                return;
            }
        }
        data.put(key, value);
        result = docRef.set(data);
        WriteCounter.addCount();
        printUpdateResult(result);
    }




    public Map<String, Object> getEntry(String documentName){
        DocumentReference docRef = getDocRef(documentName);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        ReadCounter.addCount();
        DocumentSnapshot doc = null;
        try {
            doc = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return doc.getData();
    }


    private DocumentReference getDocRef(String documentName){
        DocumentReference docRef;
        docRef = db.collection(collectionName).document(documentName);
        return docRef;
    }

    public void removeEntry(String documentName){
        DocumentReference docRef = getDocRef(documentName);
        ApiFuture<WriteResult> result = docRef.delete();
        printUpdateResult(result);
    }

    public void removeDocField(String documentName, String key){
        DocumentReference docRef = getDocRef(documentName);
        Map<String, Object> data = getEntry(documentName);
        data.remove(key);
        ApiFuture<WriteResult> result = docRef.set(data);
        WriteCounter.addCount();
        printUpdateResult(result);
    }

    private void printUpdateResult(ApiFuture<WriteResult> result) {
        try{
            System.out.println("Update time : " + result.get().getUpdateTime());
        }
        catch (InterruptedException e){
            System.out.println("Write failed: InterruptedException");
        }
        catch (ExecutionException e2){
            System.out.println("Write failed: ExecutionException");
        }
    }


}
