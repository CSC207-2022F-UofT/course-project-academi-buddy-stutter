package UseCases;

import Entities.Student;

import java.io.IOException;

public class RegisterManager extends UseCase{

    public RegisterManager(CourseManager courseDatabase, UserManager userDatabase){
        super(courseDatabase, userDatabase);
    }

    private boolean userAlreadyExists(String id){
        return this.ub.existByID(id);
    }

    private boolean passwordConfirmation(String password, String confirm){
        return password.equals(confirm);
    }

    public boolean register(String fullName, String id, String password, String confirm) throws IOException {

        if(userAlreadyExists(id)){
            return false;
        }else {
            if(passwordConfirmation(password, confirm)){
                Student newStudent = new Student(id, password, fullName, "");
                ub.addStudentUser(newStudent);
                return true;
            }
        }
        return false;
    }

}
