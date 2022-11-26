package GUI;

import Database.CourseManager;
import Database.TagManager;
import Database.UserManager;
import Users.Student;
import Users.User;
import useCases.LoginManager;
import useCases.RegisterManager;
import useCases.TagMatchManager;
import useCases.TagSelectManager;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UIController{

    //TODO: make UIController as parent class. Create seperate UIControl for each Frame, e.g. LoginUIControl, etc.

    private User self;
    private CourseManager cb;
    private UserManager ub;
    private TagManager tb;
    private TagMatchManager tagMatchManager;
    private TagSelectManager tagSelectManager;

    protected LoginUIControl loginUIControl;
    protected RegisterUIControl registerUIControl;

    public UIController(User self, CourseManager courseDatabase, UserManager userDatabase, TagManager tb){
        this.self = self;
        this.cb = courseDatabase;
        this.ub = userDatabase;
        this.tb = tb;


        // UseCases


        this.tagMatchManager = new TagMatchManager(courseDatabase, userDatabase, tb);
        this.tagSelectManager = new TagSelectManager(courseDatabase, userDatabase, tb);

        this.loginUIControl = new LoginUIControl(courseDatabase, userDatabase);
        this.registerUIControl = new RegisterUIControl(courseDatabase, userDatabase);


    }

    public void updateUser(){
        this.self = this.loginUIControl.getUser();
    }

    public void unloadUser(){
        this.self = null;
    }



    //TagMatchFrame

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

    public boolean getStudentTagState(String tagName){
        return tagSelectManager.getStudentTagState((Student) self, tagName);
    }

    public void updateStudentTag(String tagName, boolean selected){
        tagSelectManager.updateStudentTag((Student) self, tagName, selected);
    }


}
