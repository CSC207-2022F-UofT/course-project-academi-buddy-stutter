package presenters;

import model.entities.Student;
import database.accessinterfaces.CourseDataAccess;
import model.usecases.CourseMatchManager;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Implements CourseMatchUIPresenter for CourseMatchFrame
 */
public class CourseMatchUIPresenter {
    private CourseMatchManager courseMatchManager;

    public String self;
    private ArrayList<String> matchedID = new ArrayList();
    public CourseMatchUIPresenter(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.self = userID;
        this.courseMatchManager = new CourseMatchManager(courseDataAccess, userDataAccess, tagDataAccess);
    }



    /**
     * Get select student ID by index
     * @param index which user id we want to get
     * @return user id of target index
     */
    public String getSelectedUserID(int index){
        return matchedID.get(index);
    }





    /**
     * Match students by labels
     * @param label labels that users should have common with
     * @return a list of users that share the same labels
     */

    public DefaultListModel<String> createModelByLabel(String label, int numCommon) throws IOException {

        return courseMatchManager.createModelByLabel(self, label, numCommon);
    }
}
