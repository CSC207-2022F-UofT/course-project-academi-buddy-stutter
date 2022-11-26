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
    private CourseManager courseManager;
    private UserManager userManager;
    private RegisterManager registerManager;
    private TagManager tagManager;

    private TagMatchManager tagMatchManager;
    private TagSelectManager tagSelectManager;

    protected LoginUIControl loginUIControl;
    protected RegisterUIControl registerUIControl;
    protected TagMatchUIControl tagMatchUIControl;
    protected TagSelectUIControl tagSelectUIControl;

    public UIController(User self, CourseManager courseManager, UserManager userManager, TagManager tagManager){
        this.self = self;
        this.courseManager = courseManager;
        this.userManager = userManager;
        this.tagManager = tagManager;


        // UseCases


        this.tagMatchManager = new TagMatchManager(courseManager, userManager, tagManager);
        this.tagSelectManager = new TagSelectManager(courseManager, userManager, tagManager);

        this.loginUIControl = new LoginUIControl(courseManager, userManager);
        this.registerUIControl = new RegisterUIControl(courseManager, userManager);
        this.tagMatchUIControl = new TagMatchUIControl(self, courseManager, userManager, tagManager);
        this.tagSelectUIControl = new TagSelectUIControl(self, courseManager, userManager, tagManager);


    }

    public void updateUser(){
        this.self = this.loginUIControl.getUser();
    }

    public void unloadUser() {
        this.self = null;
    }


    public void toLogin(){
        LoginFrame loginFrame = new LoginFrame(this);
        this.unloadUser();
    }

    public void toHome(){
        HomeFrame HomeFrame = new HomeFrame(this);
    }

    public void toRegister(){
        RegisterFrame registerFrame = new RegisterFrame(this);
    }

    public void toTagMatch(){
        TagMatchFrame tagMatchFrame = new TagMatchFrame(this);
    }

    public void toTagSelect(){
        TagSelectFrame tagSelectFrame = new TagSelectFrame(this);
    }

}
