package UIController;

import UseCases.CourseDataCloud;
import UseCases.UserDataCloud;
import UseCases.RegisterManager;

import java.io.IOException;
import java.util.List;

public class RegisterUIControl {
    private RegisterManager registerManager;

    public RegisterUIControl(CourseDataCloud courseDatabase, UserDataCloud userDatabase) {
        this.registerManager = new RegisterManager(courseDatabase, userDatabase);
    }

    public boolean attemptRegister(String fullName, String id, String password, String confirm) throws IOException {

        return registerManager.register(fullName, id, password, confirm);

    }

    public void updateEmailAndInfo(String email, String info){
        registerManager.updateEmailAndInfo(email, info);
    }

    public String getWarningString(String password){
        StringBuilder warningString= new StringBuilder();
        List<String> warnings = registerManager.getWarnings(password);
        warningString.append("<html><pre>");
        for(int i = 0; i < 3 && i < warnings.size() ; i++) {
            warningString.append(warnings.get(i));
            warningString.append("\n");
        }
        warningString.deleteCharAt(warningString.length() - 1);
        warningString.append("</pre></html>");
        return warningString.toString();
    }

}
