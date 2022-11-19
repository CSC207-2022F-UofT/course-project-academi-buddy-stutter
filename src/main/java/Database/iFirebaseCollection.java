package Database;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface iFirebaseCollection {
    String collectionName = null;
    Firestore db = null;
    void initialize(String collectionName);

    List<QueryDocumentSnapshot> getDocumentList();

    boolean addEntry(String documentName, String key, Object value);

    Map<String, Object> getEntry(String documentName);

    boolean removeEntry(String documentName);


}
