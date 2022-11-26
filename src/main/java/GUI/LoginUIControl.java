package GUI;

import Database.CourseManager;
import Database.UserManager;
import Users.User;

import java.io.IOException;

public class LoginUIControl extends UIController{

    public LoginUIControl(User self, CourseManager courseDatabase, UserManager userDatabase) {
        super(self, courseDatabase, userDatabase);
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

}
