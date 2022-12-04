package TestDataFactory;

import Entities.InterestTag;
import Entities.Label;
import Entities.Student;
import UseCases.CourseDataCloud;
import UseCases.TagDataCloud;
import UseCases.UserDataCloud;

import java.io.IOException;
import java.util.List;

public class UserDataFactory extends DataFactory{
    public UserDataFactory(CourseDataCloud courseDataManager, UserDataCloud userDataManager, TagDataCloud tagDataManager) {
        super(courseDataManager, userDataManager, tagDataManager);
    }
    public Student createStudent(String userID, String password, String fullName, String info, String email, List<InterestTag> tags, List<Label> labels) throws IOException {
        Student student = new Student(userID, password, fullName, info);
        student.setEmail(email);
        for(InterestTag tag: tags){
            student.updateStudentTOI(tag, true);
            tagDataManager.addStudent(tag, student);
        }
        for(Label label: labels){
            student.updateLabel(label, true);
        }
        try {
            userDataManager.addStudentUser(student);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return student;
    }
}
