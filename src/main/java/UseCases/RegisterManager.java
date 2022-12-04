package UseCases;

import Entities.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterManager extends UseCase{
    Student student;
    public RegisterManager(CourseDataManager courseDatabase, UserDataManager userDatabase){
        super(courseDatabase, userDatabase);
    }

    private boolean userAlreadyExists(String id){
        return this.ub.existByID(id);
    }

    private boolean passwordConfirmation(String password, String confirm){
        return password.equals(confirm);
    }

    public List<String> getWarnings(String password){
        StrengthChecker strengthChecker = new StrengthChecker();
        return strengthChecker.checkStrength(password);
    }

    public boolean register(String fullName, String id, String password, String confirm) throws IOException {

        if(userAlreadyExists(id)){
            return false;
        }else {
            List<String> warnings = getWarnings(password);
            if (warnings.size() != 0) {
                return false;
            }
            if(passwordConfirmation(password, confirm)) {
                Student newStudent = new Student(id, password, fullName, "");
                ub.addStudentUser(newStudent);
                student = newStudent;
                return true;
            }
        }
        return false;
    }


    public void updateEmailAndInfo(String email, String info){
        student.setEmail(email);
        student.setUserInfo(info);
        try {
            ub.addStudentUser(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}