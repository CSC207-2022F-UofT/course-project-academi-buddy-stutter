package UIController;

import UseCases.CourseDataCloud;
import UseCases.LoginManager;
import UseCases.UserDataCloud;
import Entities.User;

import java.io.IOException;

public class LoginUIControl{

    private LoginManager loginManager;
    public User self;
    public LoginUIControl(CourseDataCloud courseDatabase, UserDataCloud userDatabase){

        this.loginManager = new LoginManager(courseDatabase, userDatabase);
    }

    public boolean attemptLogin(String id, String password) throws IOException {

        boolean loggedIn =  loginManager.login(id, password);
        if(loggedIn){
            loadUser(loginManager.getActiveUser());
        }

        return loggedIn;
    }

    private void loadUser(User user){
        this.self = user;
    }

    public User getUser(){
        return this.self;
    }
}
