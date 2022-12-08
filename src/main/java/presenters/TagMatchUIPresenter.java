package presenters;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.usecases.TagMatchManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements TagMatchUIPresenter for TagMatchFrame
 */
public class TagMatchUIPresenter {
    private final TagMatchManager tagMatchManager;
    private final String selfID;

    /**
     * Constructs TagMatchUIPresenter
     * @param userID a user
     * @param courseDataAccess an instance of CourseDataManager
     * @param userDataAccess an instance of UserDataManager
     * @param tagDataAccess an instance of TagDataManager
     */
    public TagMatchUIPresenter(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.selfID = userID;
        this.tagMatchManager = new TagMatchManager(courseDataAccess, userDataAccess, tagDataAccess);
    }

    /**
     * @return a list of matched users in DefaultListModel for GUI.
     */
    public DefaultListModel<String> getNameList() {
        List<String> nameList = new ArrayList<>(tagMatchManager.getStudentName(selfID));
        DefaultListModel<String> nameModel = new DefaultListModel<>();
        for (String s: nameList){
            nameModel.addElement(s);
        }
        return nameModel;
    }

    /**
     * Set selected tag
     * @param selected interest tags
     */
    public void setSelectedtag(String selected){
        tagMatchManager.setSelectedTag(selected);
    }

    /**
     * Get Selected user id
     * @param index the index of the selected user
     * @return selected user's id
     */
    public String getSelectedUserID(int index){
        return tagMatchManager.getStudentIDByIndex(index);
    }
}