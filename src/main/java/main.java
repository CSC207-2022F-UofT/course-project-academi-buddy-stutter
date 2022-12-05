import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import adapters.apis.FirebaseAPI;
import controllers.UIController;
import database.cloud.CloudCourseData;
import database.cloud.CloudTagData;
import database.cloud.CloudUserData;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class main {

    private static int CLOUD = 0;
    private static int LOCAL = 1;
    
    private static void initializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("service-account-file.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("group-106-project")
                .build();
        FirebaseApp.initializeApp(options);
    }

    private static void launch(int databaseLocation) throws IOException{
        UIController uiController = null;
        if (databaseLocation == CLOUD){
            initializeFirebase();
            FirebaseAPI cbc = new FirebaseAPI();
            FirebaseAPI ubc = new FirebaseAPI();
            FirebaseAPI tbc = new FirebaseAPI();
            CloudUserData ub = new CloudUserData(ubc);
            CloudCourseData cb = new CloudCourseData(cbc, ub);
            CloudTagData tb = new CloudTagData(tbc, ub);
            uiController = new UIController(null, cb, ub, tb);
        } 
        else if (databaseLocation == LOCAL){
            LocalTempDataFactory localTempDataFactory = new LocalTempDataFactory();
            LocalUserData ub = (LocalUserData) localTempDataFactory.getLocalDatabase().get(0);
            LocalCourseData cb = (LocalCourseData) localTempDataFactory.getLocalDatabase().get(1);
            LocalTagData tb = (LocalTagData) localTempDataFactory.getLocalDatabase().get(2);
            uiController = new UIController(null, cb, ub, tb);
        }
        uiController.toLogin();
    }

    public static void main(String[] args) throws IOException{
        launch(CLOUD);
    }

}
