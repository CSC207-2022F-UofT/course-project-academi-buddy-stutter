package GUI;

import Database.CourseManager;
import Database.UserManager;
import useCases.LoginManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class UIController{

    private CourseManager cb;
    private UserManager ub;
    private LoginManager loginManager;

    public UIController(CourseManager courseDatabase, UserManager userDatabase){
        this.cb = courseDatabase;
        this.ub = userDatabase;


        // UseCases
        this.loginManager = new LoginManager(courseDatabase, userDatabase);


    }

    public boolean attemptLogin(String id, String password) throws IOException {

        return loginManager.login(id, password);

    }


}
