package presenters;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.usecases.TagMatchManager;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements TagMatchUIPresenter for TagMatchFrame
 */
public class TagMatchUIPresenter {
    private TagMatchManager tagMatchManager;
    private String selfID;
    private TagDataAccess tagManager;

    /**
     * Constructs TagMatchUIPresenter
     * @param self a user
     * @param courseManager an instance of CourseDataManager
     * @param userManager an instance of UserDataManager
     * @param tagManager an instance of TagDataManager
     */
    public TagMatchUIPresenter(String userID, CourseDataAccess courseDataAccess, UserDataAccess userDataAccess, TagDataAccess tagDataAccess){
        this.selfID = userID;
        this.tagMatchManager = new TagMatchManager(courseDataAccess, userDataAccess, tagDataAccess);
    }

    /**
     * @return a list of matched users in DefaultListModel for GUI.
     */
    public DefaultListModel<String> getNameList() throws IOException {
        List<String> nameList = new ArrayList<>();
        for(String s: tagMatchManager.getStudentName(selfID)){
            nameList.add(s);
        }
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