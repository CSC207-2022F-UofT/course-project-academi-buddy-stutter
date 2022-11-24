package useCases;

import Database.CourseManager;
import Users.User;
import Database.UserManager;

import java.io.IOException;


public class LoginManager extends UseCase{

    User activeUser;

    public LoginManager(CourseManager courseDatabase, UserManager userDatabase){
        super(courseDatabase, userDatabase);
    }


    private boolean userExists(String id){
        return this.ub.existByID(id);
    }

    public boolean login(String id, String password) throws IOException {

        if(!userExists(id)){
            return false;
        }else {
            this.activeUser = this.ub.getUserByID(id);
            return this.activeUser.GetUserPassword().equals(password);
        }
    }

}
