package UIController;

import UseCases.CourseDataManager;
import UseCases.TagDataManager;
import UseCases.UserDataManager;
import Entities.User;
import UseCases.TagMatchUIManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TagMatchUIControl{
    private TagMatchUIManager tagMatchManager;
    private User self;
    private TagDataManager tagManager;

    public TagMatchUIControl(User self, CourseDataManager courseManager, UserDataManager userManager, TagDataManager tagManager){
        this.self = self;
        this.tagMatchManager = new TagMatchUIManager(courseManager, userManager, tagManager);
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

    public String getSelectedIndex(int index){
        return tagMatchManager.getStudentByIndex(index).getUser_id();
    }
}
