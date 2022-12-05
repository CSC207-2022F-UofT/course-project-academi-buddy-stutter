package Gateways;

import com.google.cloud.firestore.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implements DatabaseInterface
 */
public interface DatabaseInterface {
    /**
     * Initializes a collection
     * @param collectionName Name of collection
     */
    void initialize(String collectionName);

    /**
     * @return a list of documents
     */
    List<?> getDocumentList();

    /**
     * Add entries
     * @param documentName name of document
     * @param key key
     * @param value value
     * @return whether entry is successfully added
     */
    boolean addEntry(String documentName, String key, Object value);

    /**
     * Creates a hashmap
     * @param documentName name of document
     * @return a hashmap
     */
    Map<String, Object> getEntry(String documentName);

    /**
     * @param documentName name of document
     * @return whether document is successfully is removed from entries
     */
    boolean removeEntry(String documentName);

    /**
     * Updates documents
     */
    void updateDocuments();

    /**
     * @return a list of document names
     */
    ArrayList<String> getDocumentStringList();

    /**
     * Removes document fields
     * @param documentName name of document
     * @param key key
     * @return whether document is removed from map
     */
    boolean removeDocField(String documentName, String key);
}
