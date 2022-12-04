package UIController;

import UseCases.CourseDataCloud;
import UseCases.TagDataCloud;
import UseCases.UserDataCloud;
import Entities.User;
import UseCases.TagMatchManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TagMatchUIControl{
    private TagMatchManager tagMatchManager;
    private User self;
    private TagDataCloud tagManager;

    public TagMatchUIControl(User self, CourseDataCloud courseManager, UserDataCloud userManager, TagDataCloud tagManager){
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