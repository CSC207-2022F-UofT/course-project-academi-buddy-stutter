package UseCases;

import Entities.User;

import java.io.IOException;


public class LoginUIManager extends UseCase{

    User activeUser = null;

    public LoginUIManager(CourseDataManager courseDatabase, UserDataManager userDatabase){
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

    public User getActiveUser(){
        return activeUser;
    }

}
