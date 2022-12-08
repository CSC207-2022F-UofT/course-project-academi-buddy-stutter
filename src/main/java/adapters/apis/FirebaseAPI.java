package adapters.apis;

import adapters.gateways.DatabaseInterface;
import utilities.ReadCounter;
import utilities.WriteCounter;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import com.google.firebase.cloud.FirestoreClient;

import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseAPI implements DatabaseInterface {
    private String collectionName;
    private Firestore db;
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

    public void updateDocuments(){
        /**
         * update document list.
         */
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
    public boolean addEntry(String documentName, String key, Object value){
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
                return printUpdateResult(result);
            }
        }
        data.put(key, value);
        result = docRef.set(data);
        WriteCounter.addCount();
        return printUpdateResult(result);
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

    public boolean removeEntry(String documentName){
        DocumentReference docRef = getDocRef(documentName);
        ApiFuture<WriteResult> result = docRef.delete();
        return printUpdateResult(result);
    }

    public boolean removeDocField(String documentName, String key){
        DocumentReference docRef = getDocRef(documentName);
        Map<String, Object> data = getEntry(documentName);
        data.remove(key);
        ApiFuture<WriteResult> result = docRef.set(data);
        WriteCounter.addCount();
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
