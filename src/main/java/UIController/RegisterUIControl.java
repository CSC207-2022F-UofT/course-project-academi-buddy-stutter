package UIController;

import UseCases.CourseDataManager;
import UseCases.UserDataManager;
import UseCases.RegisterUIManager;

import java.io.IOException;

public class RegisterUIControl {
    private RegisterUIManager registerManager;

    public RegisterUIControl(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        this.registerManager = new RegisterUIManager(courseDatabase, userDatabase);
    }

    public boolean attemptRegister(String fullName, String id, String password, String confirm) throws IOException {

        return registerManager.register(fullName, id, password, confirm);

    }

}
