package presenters;

import database.accessinterfaces.CourseDataAccess;
import model.usecases.LabelSelectManager;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import java.io.IOException;

/**
 * Implements LabelSelectUIPresenter
 */
public class LabelSelectUIPresenter {
    private final String selfID;
    private final LabelSelectManager labelSelectManager;

    /**
     * Constructs LabelSelectUIPresenter
     * @param userID a user
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess an instance of UserDataManager
     */
    public LabelSelectUIPresenter(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.selfID = userID;
        this.labelSelectManager = new LabelSelectManager(courseDataAccess, userDataAccess, tagDataAccess);
    }

    /**
     * Get user's label
     * @param labelName name of label
     * @return label selected by user
     */
    public boolean getStudentLabelState(String labelName)  {
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
