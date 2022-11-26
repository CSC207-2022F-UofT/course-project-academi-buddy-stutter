package GUI;

import Database.CourseManager;
import Database.FirebaseCollection;
import Database.TagManager;
import Database.UserManager;
import Users.InterestTag;
import Users.Student;
import Users.User;
import useCases.LoginManager;
import useCases.RegisterManager;
import useCases.TagMatchManager;
import useCases.TagSelectManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UIController{

    //TODO: make UIController as parent class. Create seperate UIControl for each Frame, e.g. LoginUIControl, etc.

    private User self;
    private CourseManager cb;
    private UserManager ub;
    private LoginManager loginManager;
    private RegisterManager registerManager;
    private TagManager tb;

    private TagMatchManager tagMatchManager;
    private TagSelectManager tagSelectManager;

    public UIController(User self, CourseManager courseDatabase, UserManager userDatabase, TagManager tb){
        this.self = self;
        this.cb = courseDatabase;
        this.ub = userDatabase;
        this.tb = tb;


        // UseCases
        this.loginManager = new LoginManager(courseDatabase, userDatabase);
        this.registerManager = new RegisterManager(courseDatabase, userDatabase);
        this.tagMatchManager = new TagMatchManager(courseDatabase, userDatabase, tb);
        this.tagSelectManager = new TagSelectManager(courseDatabase, userDatabase, tb);

    }

    private void loadUser(User user){
        this.self = user;
    }

    public void unloadUser(){
        this.self = null;
    }


    public boolean attemptLogin(String id, String password) throws IOException {

        boolean loggedIn =  loginManager.login(id, password);
        if(loggedIn){
            loadUser(loginManager.getActiveUser());
        }

        return loggedIn;
    }

    public boolean attemptRegister(String fullName, String id, String password, String confirm) throws IOException {

        return registerManager.register(fullName, id, password, confirm);

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
