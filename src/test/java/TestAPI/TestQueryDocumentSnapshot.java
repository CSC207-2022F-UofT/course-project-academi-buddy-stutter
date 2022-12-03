package TestAPI;

import java.util.HashMap;

public class TestQueryDocumentSnapshot {

    private String documentName;
    private HashMap<String, ?> document;

    TestQueryDocumentSnapshot(String documentName, HashMap<String, ?> document){
        this.documentName = documentName;
        this.document = document;
    }

    public HashMap<String, ?> getData(){
        return document;
    }

    public String getDocumentName(){
        return documentName;
    }

}
