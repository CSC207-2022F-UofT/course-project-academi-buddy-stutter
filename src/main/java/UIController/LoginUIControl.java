package UIController;

import UseCases.CourseDataManager;
import UseCases.LoginUIManager;
import UseCases.UserDataManager;
import Entities.User;

import java.io.IOException;

public class LoginUIControl{

    private LoginUIManager loginManager;
    public User self;
    public LoginUIControl(CourseDataManager courseDatabase, UserDataManager userDatabase){

        this.loginManager = new LoginUIManager(courseDatabase, userDatabase);
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
