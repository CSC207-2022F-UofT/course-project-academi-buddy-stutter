package model.usecases;

import database.accessinterfaces.CourseDataAccess;
import database.accessinterfaces.TagDataAccess;
import database.accessinterfaces.UserDataAccess;
import model.entities.Student;

import java.io.IOException;
import java.util.List;

/**
 * Use case operations for registration.
 */
public class RegisterManager extends UseCase{
    Student student;

    /**
     * Initializer.
     * @param courseDatabase The course database.
     * @param userDatabase The user database.
     */
    public RegisterManager(CourseDataAccess courseDatabase, UserDataAccess userDatabase, TagDataAccess tagDatabase){
        super(courseDatabase, userDatabase, tagDatabase);
    }

    /**
     * Check if user exists in the user database.
     * @param id The id of the user to check.
     * @return True if the user exists in the user database, false otherwise.
     */
    private boolean userAlreadyExists(String id){
        return this.ub.existByID(id);
    }

    /**
     * Check if the passwords match between passwords.
     * @param password The first password inputted.
     * @param confirm The confirmation password inputted.
     * @return True if password is the same as confirm, false otherwise.
     */
    private boolean passwordConfirmation(String password, String confirm){
        return password.equals(confirm);
    }

    /**
     * To check if password is strong enough, and provide user with reasonings for why it isn't.
     * @param password The inputted password.
     * @return The list of reasons for why password is not strong enough.
     */
    public List<String> getWarnings(String password){
        StrengthChecker strengthChecker = new StrengthChecker();
        return strengthChecker.checkStrength(password);
    }

    public String getWarningString(String password){
        List<String> warnings = getWarnings(password);
        StringBuilder warningString= new StringBuilder();
        warningString.append("<html><pre>");
        for(int i = 0; i < 3 && i < warnings.size() ; i++) {
            warningString.append(warnings.get(i));
            warningString.append("\n");
        }
        warningString.deleteCharAt(warningString.length() - 1);
        warningString.append("</pre></html>");
        return warningString.toString();
    }

    /**
     * Register and add a new user to the user database.
     * @param fullName The full name String of the student.
     * @param id The id String of the student.
     * @param password The password String of the student.
     * @param confirm The password confirmation String of the student.
     * @return True if the user is successfully registered and uploaded to user database, false otherwise.
     */
    public boolean register(String fullName, String id, String password, String confirm) throws IOException {

        if(userAlreadyExists(id)){
            return false;
        }else {
            List<String> warnings = getWarnings(password);
            if (!warnings.isEmpty()) {
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

    /**
     * Updating the student's email and info.
     * @param email The email String that is going to be updated.
     * @param info The info String that is going to be updated.
     */
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