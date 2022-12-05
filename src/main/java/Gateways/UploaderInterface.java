package Gateways;

import java.io.File;
import java.io.IOException;

/**
 * Implements UploaderInterface
 */
public interface UploaderInterface {
    /**
     * @return whether file is successfully uploaded
     */
    boolean uploadFile();

    /**
     * Copies file path
     * @throws IOException fails to copy
     */
    void copyFileToPath() throws IOException;
}
