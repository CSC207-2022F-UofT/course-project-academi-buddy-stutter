package UIController;

import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;
import Entities.User;
import UseCases.TagMatchManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TagMatchUIControl{
    private TagMatchManager tagMatchManager;
    private User self;
    private TagDataManager tagManager;

    public TagMatchUIControl(User self, CourseDataManager courseManager, UserDataManager userManager, TagDataManager tagManager){
        this.self = self;
        this.tagMatchManager = new TagMatchManager(courseManager, userManager, tagManager);
    }

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

    //TagSelectFrame

    public void setSelectedtag(String selected){
        tagMatchManager.setSelectedTag(selected);
    }

    public String getSelectedUserID(int index){
        return tagMatchManager.getStudentIDByIndex(index);
    }
}
