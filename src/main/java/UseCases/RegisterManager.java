package UseCases;

import Entities.Student;

import java.io.IOException;

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

    public boolean register(String fullName, String id, String password, String confirm) throws IOException {

        if(userAlreadyExists(id)){
            return false;
        }else {
            if(passwordConfirmation(password, confirm)){
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
