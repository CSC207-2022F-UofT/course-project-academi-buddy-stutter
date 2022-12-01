package Gateways;

import com.google.cloud.firestore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DatabaseInterface {
    void initialize(String collectionName);

    List<QueryDocumentSnapshot> getDocumentList();

    boolean addEntry(String documentName, String key, Object value);

    Map<String, Object> getEntry(String documentName);

    boolean removeEntry(String documentName);

    void updateDocuments();

    ArrayList<String> getDocumentStringList();

    boolean removeDocField(String documentName, String key);


}
