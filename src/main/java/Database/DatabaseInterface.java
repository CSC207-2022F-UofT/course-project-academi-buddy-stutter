package Database;

import com.google.cloud.firestore.*;

import java.util.List;
import java.util.Map;

public interface DatabaseInterface {
    String collectionName = null;
    Firestore db = null;
    void initialize(String collectionName);

    List<QueryDocumentSnapshot> getDocumentList();

    boolean addEntry(String documentName, String key, Object value);

    Map<String, Object> getEntry(String documentName);

    boolean removeEntry(String documentName);


}
