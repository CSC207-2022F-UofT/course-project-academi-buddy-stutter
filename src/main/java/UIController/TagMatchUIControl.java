package UIController;

import UseCases.CloudCourseData;
import UseCases.CloudTagData;
import UseCases.CloudUserData;
import Entities.User;
import UseCases.TagMatchManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements TagMatchUIControl for TagMatchFrame
 */
public class TagMatchUIControl{
    private TagMatchManager tagMatchManager;
    private User self;
    private CloudTagData tagManager;

    /**
     * Constructs TagMatchUIControl
     * @param self a user
     * @param courseManager an instance of CourseDataManager
     * @param userManager an instance of UserDataManager
     * @param tagManager an instance of TagDataManager
     */
    public TagMatchUIControl(User self, CloudCourseData courseManager, CloudUserData userManager, CloudTagData tagManager){
        this.self = self;
        this.tagMatchManager = new TagMatchManager(courseManager, userManager, tagManager);
    }

    /**
     * @return a list of matched users
     */
    public DefaultListModel<String> getNameList(){
        List<String> nameList = new ArrayList<>();
        for(String s: tagMatchManager.getStudentName(self)){
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