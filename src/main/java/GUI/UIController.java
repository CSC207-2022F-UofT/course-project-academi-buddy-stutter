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

    protected User self;
    protected CourseManager cb;
    protected UserManager ub;
    protected LoginManager loginManager;
    protected RegisterManager registerManager;

    private LoginUIControl loginUIControl;



    private TagSelectManager tagSelectManager;

    public UIController(User self, CourseManager courseDatabase, UserManager userDatabase){
        this.self = self;
        this.cb = courseDatabase;
        this.ub = userDatabase;


        // UseCases
        this.loginManager = new LoginManager(courseDatabase, userDatabase);
        this.registerManager = new RegisterManager(courseDatabase, userDatabase);


    }

    public void toLogin(){
        this.loginUIControl = new LoginUIControl(null, cb, ub);
        LoginFrame loginFrame = new LoginFrame(loginUIControl);
        loginUIControl.unloadUser();
    }

    public void toHome(UIController uiController){
        HomeFrame HomeFrame = new HomeFrame(uiController);
    }

    public void toRegister(UIController uiController){
        RegisterFrame registerFrame = new RegisterFrame(uiController);
    }






    //TagSelectFrame




}
