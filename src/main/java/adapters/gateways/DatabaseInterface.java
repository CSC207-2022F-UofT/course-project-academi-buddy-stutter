package adapters.gateways;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface DatabaseInterface {
    void openCollection(String collectionName);

    List<?> getDocumentList();

    void addEntry(String documentName, String key, Object value);

    Map<String, Object> getEntry(String documentName);

    void removeEntry(String documentName);

    void updateDocuments();

    ArrayList<String> getDocumentStringList();

    void removeDocField(String documentName, String key);


}
