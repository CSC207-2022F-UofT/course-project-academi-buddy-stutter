import database.local.LocalTempDataFactory;
import database.local.LocalCourseData;
import database.local.LocalTagData;
import database.local.LocalUserData;
import adapters.apis.FirebaseAPI;
import views.FrameNavigator;
import database.cloud.CloudCourseData;
import database.cloud.CloudTagData;
import database.cloud.CloudUserData;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class Launcher {

    private static final int CLOUD = 0;
    private static final int LOCAL = 1;

    private static void initializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("service-account-file.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setProjectId("group-106-project")
                .build();
        FirebaseApp.initializeApp(options);
    }

    private static void launch(int databaseLocation) throws IOException{
        FrameNavigator frameNavigator;
        if (databaseLocation == CLOUD){
            initializeFirebase();
            FirebaseAPI cbc = new FirebaseAPI();
            FirebaseAPI ubc = new FirebaseAPI();
            FirebaseAPI tbc = new FirebaseAPI();
            CloudUserData ub = new CloudUserData(ubc);
            CloudCourseData cb = new CloudCourseData(cbc, ub);
            CloudTagData tb = new CloudTagData(tbc);
            frameNavigator = new FrameNavigator(null, cb, ub, tb);
            frameNavigator.toLogin();
        } 
        else if (databaseLocation == LOCAL){
            LocalTempDataFactory localTempDataFactory = new LocalTempDataFactory();
            LocalUserData ub = (LocalUserData) localTempDataFactory.getLocalDatabase().get(0);
            LocalCourseData cb = (LocalCourseData) localTempDataFactory.getLocalDatabase().get(1);
            LocalTagData tb = (LocalTagData) localTempDataFactory.getLocalDatabase().get(2);
            frameNavigator = new FrameNavigator(null, cb, ub, tb);
            frameNavigator.toLogin();
        }
    }

    public static void main(String[] args) throws IOException{
        launch(CLOUD);
    }

}
