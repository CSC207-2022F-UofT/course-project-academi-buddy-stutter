package GUI;

import Database.CourseManager;
import Database.FirebaseCollection;
import Database.TagManager;
import Database.UserManager;
import Users.InterestTag;
import Users.Student;
import Users.User;
import useCases.LoginManager;
import useCases.TagMatchManager;
import useCases.TagSelectManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UIController{

    private User self;
    private CourseManager cb;
    private UserManager ub;
    private LoginManager loginManager;
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
        this.tagMatchManager = new TagMatchManager(courseDatabase, userDatabase, tb);
        this.tagSelectManager = new TagSelectManager(courseDatabase, userDatabase, tb);

    }

    public boolean attemptLogin(String id, String password) throws IOException {

        return loginManager.login(id, password);

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

    public boolean getStudentTagState(String tagName){
        updateSelf();
        return tagSelectManager.getStudentTagState((Student) self, tagName);
    }

    public void updateStudentTag(String tagName, boolean selected){
        tagSelectManager.updateStudentTag((Student) self, tagName, selected);
    }

    private void updateSelf(){
        try {
            this.self = ub.getUserByID(self.getUser_id());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
