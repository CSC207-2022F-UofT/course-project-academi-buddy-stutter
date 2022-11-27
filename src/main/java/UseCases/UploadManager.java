package UseCases;
import Gateways.UploaderInterface;

import java.io.IOException;


public class UploadManager extends UseCase {
    UploaderInterface uploaderInterface;
    public UploadManager(CourseDataManager courseDatabase, UserDataManager userDatabase,
                         UploaderInterface uploaderInterface){
        super(courseDatabase, userDatabase);
        this.uploaderInterface = uploaderInterface;
    }

    public boolean upload(){
        return uploaderInterface.uploadFile();
    }

    public void copyFileToPath() throws IOException {
        this.uploaderInterface.copyFileToPath();
    }

}
