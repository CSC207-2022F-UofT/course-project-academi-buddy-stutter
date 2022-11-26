package GUI;

import Database.CourseManager;
import Database.UserManager;
import useCases.RegisterManager;

import java.io.IOException;

public class RegisterUIControl {
    private RegisterManager registerManager;

    public RegisterUIControl(CourseManager courseDatabase, UserManager userDatabase) {
        this.registerManager = new RegisterManager(courseDatabase, userDatabase);
    }

    public boolean attemptRegister(String fullName, String id, String password, String confirm) throws IOException {

        return registerManager.register(fullName, id, password, confirm);

    }

}
