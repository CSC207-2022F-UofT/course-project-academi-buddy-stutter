package GUI;

import Database.CourseManager;
import Database.UserManager;
import Users.User;
import useCases.RegisterManager;

import java.io.IOException;

public class RegisterUIControl extends UIController{
    private RegisterManager registerManager;
    public RegisterUIControl(User self, CourseManager courseDatabase, UserManager userDatabase) {
        super(self, courseDatabase, userDatabase);
    }

    public boolean attemptRegister(String fullname, String id, String password, String confirm) throws IOException{
        return registerManager.register(fullname, id, password, confirm);
    }
}
