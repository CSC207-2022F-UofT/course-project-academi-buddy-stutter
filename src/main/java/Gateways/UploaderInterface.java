package Gateways;

import java.io.File;
import java.io.IOException;

public interface UploaderInterface {

    boolean uploadFile();

    void copyFileToPath() throws IOException;
}
