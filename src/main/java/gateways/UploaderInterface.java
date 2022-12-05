package gateways;

import java.io.IOException;

public interface UploaderInterface {

    boolean uploadFile();

    void copyFileToPath() throws IOException;
}
