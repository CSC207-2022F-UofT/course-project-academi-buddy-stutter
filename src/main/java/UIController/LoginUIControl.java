package UIController;

import UseCases.CourseDataManager;
import UseCases.UserDataManager;
import Entities.User;
import UseCases.LoginManager;

import java.io.IOException;

public class LoginUIControl{

    private LoginManager loginManager;
    public User self;
    public LoginUIControl(CourseDataManager courseDatabase, UserDataManager userDatabase){

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
