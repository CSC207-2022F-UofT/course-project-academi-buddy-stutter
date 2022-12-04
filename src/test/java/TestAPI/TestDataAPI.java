package TestAPI;

import Gateways.DatabaseInterface;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataAPI implements DatabaseInterface {

    private String collectionName;
    private List<TestQueryDocumentSnapshot> documents = new ArrayList<>();;

    public TestDataAPI(){
        this.collectionName = "";

    }

    @Override
    public void initialize(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public List<TestQueryDocumentSnapshot> getDocumentList() {
        return this.documents;
    }

    @Override
    public boolean addEntry(String documentName, String key, Object value) {

        Map<String, Object> currentData = getEntry(documentName);


        if(currentData == null){
            currentData = new HashMap<>();
            currentData.put(key, value);
        }
        else {
            if(currentData.containsKey(key)){
                documents.remove(new TestQueryDocumentSnapshot(documentName, (HashMap<String, ?>) currentData));
                currentData.put(key, value);
            }
        }
        documents.add(new TestQueryDocumentSnapshot(documentName, (HashMap<String, ?>) currentData));

        return true;
    }

    @Override
    public Map<String, Object> getEntry(String documentName) {
        for(TestQueryDocumentSnapshot t: this.documents){
            if(t.getDocumentName().equals(documentName)){
                return (Map<String, Object>) t.getData();
            }
        }

        return null;

    }

    @Override
    public boolean removeEntry(String documentName) {
        Map<String, Object> targetDelete = getEntry(documentName);
        this.documents.remove(targetDelete);
        return true;
    }

    @Override
    public void updateDocuments() {}

    @Override
    public ArrayList<String> getDocumentStringList() {
        ArrayList<String> documentList = new ArrayList<>();
        updateDocuments();
        for(TestQueryDocumentSnapshot document: this.documents){
            documentList.add(document.getDocumentName());
        }
        return documentList;
    }

    @Override
    public boolean removeDocField(String documentName, String key) {
        Map<String, Object> targetDoc = getEntry(documentName);
        targetDoc.remove(key);
        return true;
    }
}
