package UIController;

import Entities.Student;
import UseCases.CourseDataManager;
import UseCases.UserDataManager;
import UseCases.RegisterManager;

import java.io.IOException;

public class RegisterUIControl {
    private RegisterManager registerManager;

    public RegisterUIControl(CourseDataManager courseDatabase, UserDataManager userDatabase) {
        this.registerManager = new RegisterManager(courseDatabase, userDatabase);
    }

    public boolean attemptRegister(String fullName, String id, String password, String confirm) throws IOException {

        return registerManager.register(fullName, id, password, confirm);

    }

    public void updateEmailAndInfo(String email, String info){
        registerManager.updateEmailAndInfo(email, info);
    }

}
