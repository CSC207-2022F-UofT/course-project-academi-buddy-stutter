package apis;

import gateways.UploaderInterface;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class JavaxAPI implements UploaderInterface {

    File filePath;
    File currentFile;

    @Override
    public boolean uploadFile() {
        JFileChooser fileChooser = new JFileChooser();

        //ics calender filter
        FileNameExtensionFilter filter = new FileNameExtensionFilter("coursesCalendar", "ics");
        fileChooser.setFileFilter(filter);

        //current file path
        this.currentFile = new File("coursesCalendar.ics");

        //selected file path
        int response = fileChooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            this.filePath = new File(fileChooser.getSelectedFile().getAbsolutePath());
            return true;
        }
        return false;
    }

    @Override
    public void copyFileToPath() throws IOException {
        Files.copy(filePath.toPath(), currentFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }



}
