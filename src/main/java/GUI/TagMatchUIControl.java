package GUI;

import Database.CourseManager;
import Database.TagManager;
import Database.UserManager;
import Users.User;
import useCases.TagMatchManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TagMatchUIControl extends UIController{
    private TagManager tb;
    private TagMatchManager tagMatchManager;

    public TagMatchUIControl(User self, CourseManager courseDatabase, UserManager userDatabase, TagManager tb) {
        super(self, courseDatabase, userDatabase);
        this.tb = tb;
        this.tagMatchManager = new TagMatchManager(courseDatabase, userDatabase, tb);
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

    public void setSelectedtag(String selected){
        tagMatchManager.setSelectedTag(selected);
    }

    public String getSelectedIndex(int index){
        return tagMatchManager.getStudentByIndex(index).getUser_id();
    }

}
