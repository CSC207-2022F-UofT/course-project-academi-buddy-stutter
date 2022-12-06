package usecaseio;

import database.accessinterfaces.CourseDataAccess;
import usecases.LabelSelectManager;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import java.io.IOException;

/**
 * Implements LabelSelectIO
 */
public class LabelSelectIO {
    private String selfID;
    private LabelSelectManager labelSelectManager;

    /**
     * Constructs LabelSelectIO
     * @param selfID a user
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess an instance of UserDataManager
     */
    public LabelSelectIO(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.selfID = userID;
        this.labelSelectManager = new LabelSelectManager(courseDataAccess, userDataAccess, tagDataAccess);
    }

    /**
     * Get user's label
     * @param labelName name of label
     * @return label selected by user
     */
    public boolean getStudentLabelState(String labelName) throws IOException {
        return labelSelectManager.getStudentLabelState(selfID, labelName);
    }

    /**
     * Updates user label
     * @param labelName name of label
     * @param selected determines whether label is selected
     */
    public void updateStudentLabel(String labelName, boolean selected) throws IOException {
        labelSelectManager.updateStudentLabel(selfID, labelName, selected);
    }
}
